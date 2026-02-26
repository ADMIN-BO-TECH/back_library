package co.com.botech.util.excelutils;


import co.com.botech.util.excelutils.returnedObjects.ExcelDataException;
import co.com.botech.util.excelutils.returnedObjects.UploadManipulationRestrictions;
import co.com.botech.util.excelutils.returnedObjects.UploadResponseObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@Slf4j
public class UploadUtils<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    private Class<T> selectedClass;
    private List<String> columnNamesExpected;
    private List<String> getMethods;
    private List<String> setMethods;
    private List<? extends Class<?>> typeVariables;
    private DataFormatter formatter;

    @Getter
    private List<T> updatedRegisters;
    @Getter
    private List<T> newRegisters;
    @Getter
    private List<T> deletedRegisters;

    public void defaultConstructor(Class<T> selectedClass) {
        this.selectedClass = selectedClass;
        List<String> headers = GeneralUtils.getHeaders(selectedClass);
        this.getMethods = GeneralUtils.setGetMethods(headers);
        this.columnNamesExpected = GeneralUtils.getColumnNames(this.selectedClass);
        this.setMethods = GeneralUtils.setSetMethods(headers);
        this.typeVariables = GeneralUtils.getTypes(selectedClass);
        this.formatter = new DataFormatter();
    }

    public UploadResponseObject<T> uploadExcelRegisters(ByteArrayInputStream fileBytes, List<T> actualData, UploadManipulationRestrictions restrictions) {
        List<T> registersExcelUploaded = setExcelInformation(fileBytes);
        List<Long> idActualList = setIdList(actualData);
        List<Long> idReceivedList = setIdList(registersExcelUploaded);
        excelRepeatedValidations(idReceivedList);
        excelUploadDataPreparation(actualData, registersExcelUploaded, idActualList, idReceivedList);
        UploadResponseObject<T> response = new UploadResponseObject<T>(
                registersExcelUploaded,
                actualData,
                this.newRegisters,
                this.updatedRegisters,
                this.deletedRegisters);
        validateRestrictions(restrictions, response);
        return response;
    }


    //PREPARATION DATA FUNCTIONS
    private List<T> setExcelInformation(ByteArrayInputStream fileBytes) {
        List<T> excelDataList;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fileBytes);
            Sheet sheet = workbook.getSheetAt(0);
            Row headersRow = sheet.getRow(0);
            List<String> columnNamesReceived = setReceivedExcelHeaders(headersRow);

            if (this.columnNamesExpected.equals(columnNamesReceived)) {
                excelDataList = setObjectsList(sheet);
            } else {
                List<String> errorColumns = new ArrayList<>(columnNamesReceived);
                List<String> correctionColumns = new ArrayList<>(this.columnNamesExpected);

                errorColumns.removeAll(this.columnNamesExpected);
                correctionColumns.removeAll(columnNamesReceived);
                if (correctionColumns.isEmpty()) {
                    throw new ExcelDataException("Error estructura en las  Columnas: Los nombres de las columnas no son los esperados \n " +
                            "Orden Esperado: " + this.columnNamesExpected);
                } else {
                    throw new ExcelDataException("Error estructura en las  Columnas: Los nombres de las columnas no son los esperados \n " +
                            "Columnas Recibidas con errores : " + errorColumns + "\n " +
                            "Columnas Esperadas : " + correctionColumns);
                }
            }

        } catch (ExcelDataException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error leyendo datos de excel - UploadUtils: " + e);
            throw new ExcelDataException("Error leyendo datos de excel: Verificar Formato");
        }
        return excelDataList;
    }

    private List<String> setReceivedExcelHeaders(Row headersRow) {
        List<String> columnNamesReceived = new ArrayList<>();
        for (int i = 0; i < this.columnNamesExpected.size(); i++) {
            Cell cell = headersRow.getCell(i);
            String columnName = cell.getStringCellValue();
            columnNamesReceived.add(columnName);
        }
        return columnNamesReceived;
    }

    private List<T> setObjectsList(Sheet sheet) {
        return StreamSupport.stream(sheet.spliterator(), true)
                .skip(1)
                .map(this::mapRowToObject)
                .collect(Collectors.toList());
    }

    private T mapRowToObject(Row row) {
        try {
            T objectOfSelectedClass = this.selectedClass.getDeclaredConstructor().newInstance();
            for (int i = 0; i < this.setMethods.size(); i++) {
                if (validateNumericInputType(i)) {
                    Object value = getNumericObjectType(i, row.getCell(i));
                    objectOfSelectedClass.getClass().getMethod(this.setMethods.get(i), this.typeVariables.get(i)).invoke(objectOfSelectedClass, value);
                } else if (row.getCell(i).getCellType() == CellType.NUMERIC) {
                    objectOfSelectedClass.getClass().getMethod(this.setMethods.get(i), String.class).invoke(objectOfSelectedClass, formatter.formatCellValue(row.getCell(i)));
                } else if (validateDateTimeInputType(i)) {
                    String value = formatter.formatCellValue(row.getCell(i));
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
                    LocalDateTime parsedDate = LocalDateTime.parse(value, dateTimeFormatter);
                    objectOfSelectedClass.getClass()
                            .getMethod(this.setMethods.get(i), LocalDateTime.class)
                            .invoke(objectOfSelectedClass, parsedDate);
                } else {
                    objectOfSelectedClass.getClass().getMethod(this.setMethods.get(i), String.class).invoke(objectOfSelectedClass, row.getCell(i).getStringCellValue());
                }
            }
            dataFormatValidation(objectOfSelectedClass, row.getRowNum() + 1);
            return objectOfSelectedClass;
        } catch (ExcelDataException e) {
            throw e;
        } catch (Exception e) {
            int rowError = row.getRowNum() + 1;
            log.error("Error organizando datos de excel - UploadUtils: Inconsistencia en los datos: " + e);
            throw new ExcelDataException("Error organizando datos de excel: Inconsistencia en los datos en la fila " + rowError);
        }
    }

    private void excelUploadDataPreparation(List<T> actualData, List<T> receivedData, List<Long> actualIdList, List<Long> receivedIdList) {
        List<T> difference = getDifferenceOfObjects(actualData, receivedData);

        List<Long> differenceListIds = setIdList(difference);

        List<Long> newIds = getDifferenceOfIds(actualIdList, differenceListIds);
        List<Long> updatedIds = getIntersectionOfIds(actualIdList, differenceListIds);
        List<Long> removedIds = getDifferenceOfIds(receivedIdList, actualIdList);

        this.updatedRegisters = setObjectListFromIds(updatedIds, receivedData);
        this.newRegisters = setObjectListFromIds(newIds, receivedData);
        this.deletedRegisters = setObjectListFromIds(removedIds, actualData);
    }

    //VALIDATION FUNCTIONS
    private void validateRestrictions(UploadManipulationRestrictions restrictions, UploadResponseObject<T> response) {
        if (!restrictions.isCanCreate() && !response.getNewRegisters().isEmpty()) {
            throw new ExcelDataException("El excel recibido contiene nuevos registros. La configuración no permite crear nuevos registros");
        }
        if (!restrictions.isCanUpdate() && !response.getUpdatedRegisters().isEmpty()) {
            throw new ExcelDataException("El excel recibido contiene registros actualizados. La configuración no permite actualizar registros");
        }
        if (!restrictions.isCanDelete() && !response.getDeletedRegisters().isEmpty()) {
            throw new ExcelDataException("El excel recibido contiene registros eliminados. La configuración no permite eliminar registros");
        }
    }

    private boolean validateNumericInputType(Integer columnIndex) {
        return this.typeVariables.get(columnIndex).equals(Long.class) || this.typeVariables.get(columnIndex).equals(Integer.class) || this.typeVariables.get(columnIndex).equals(Double.class);
    }

    private boolean validateDateTimeInputType(Integer columnIndex) {
        return this.typeVariables.get(columnIndex).equals(LocalDateTime.class);
    }

    private void dataFormatValidation(T object, int rowError) {
        Set<ConstraintViolation<T>> formatValidation = validator.validate(object);
        if (!formatValidation.isEmpty()) {
            for (ConstraintViolation<T> violation : formatValidation) {
                throw new ExcelDataException("Error leyendo datos de excel: Se han ingresado caracteres invalidos en la fila " + rowError + " en " + violation.getPropertyPath());
            }
        }
    }

    private void excelRepeatedValidations(List<Long> receivedData) {
        Set<Long> idReceivedSet = new HashSet<>(receivedData);
        if (idReceivedSet.size() < receivedData.size()) {
            Set<Long> repeatedItems = getRepeatedValues(receivedData);
            throw new ExcelDataException("El excel recibido contiene id's repetidos. Verifique la información e intente nuevamente \n " +
                    "ID's Repetidos: " + repeatedItems);
        }
    }

    //GET AND SET DATA FUNCTIONS
    private List<T> getDifferenceOfObjects(List<T> actualData, List<T> receivedData) {
        List<T> differences = new ArrayList<>(receivedData);
        differences.removeAll(actualData);
        return differences;
    }

    private Object getNumericObjectType(int index, Cell cell) {
        return switch (this.typeVariables.get(index).getSimpleName()) {
            case "Integer" -> (int) cell.getNumericCellValue();
            case "Long" -> (long) cell.getNumericCellValue();
            default -> throw new IllegalArgumentException("Valor numérico inválido" + this.typeVariables.get(index));
        };
    }

    private List<Long> getDifferenceOfIds(List<Long> removeIdList, List<Long> basicIdList) {
        List<Long> newIds = new ArrayList<>(basicIdList);
        newIds.removeAll(removeIdList);
        return newIds;
    }

    private List<Long> getIntersectionOfIds(List<Long> actualIds, List<Long> differenceIds) {
        List<Long> updatedIds = new ArrayList<>(differenceIds);
        updatedIds.retainAll(actualIds);
        return updatedIds;
    }

    private Set<Long> getRepeatedValues(List<Long> list) {
        Set<Long> unique = new HashSet<>();
        Set<Long> repeated = new HashSet<>();
        for (Long item : list) {
            if (!unique.add(item)) {
                repeated.add(item);
            }
        }
        return repeated;
    }

    private List<Long> setIdList(List<T> data) {
        return data.stream().map(studentResponse ->
        {
            try {
                return (Long) studentResponse.getClass().getMethod(this.getMethods.get(0)).invoke(studentResponse);
            } catch (Exception e) {
                log.error("Error organizando IdList para validación - UploadUtils: ");
                throw new ExcelDataException(("Error organizando IdList para validación"));
            }
        }).toList();
    }

    private List<T> setObjectListFromIds(List<Long> idList, List<T> receivedData) {
        List<T> temporalObjects = new ArrayList<>(receivedData);
        return temporalObjects.stream().filter(register -> {
            try {
                return idList.contains(register.getClass().getMethod(this.getMethods.get(0)).invoke(register));
            } catch (Exception e) {
                log.error("Error preparando lista de objetos - UploadUtils: ", e);
                throw new ExcelDataException(("Error preparando lista de objetos"));
            }
        }).toList();
    }
}

