package co.com.botech.util.excelutils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DownloadUtils<T> {
    public ByteArrayInputStream dataToExcel(Map<String, List<T>> mappedData, Class<?> selectedClass) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            List<String> fieldNames = GeneralUtils.getColumnNames(selectedClass);
            List<String> headers = GeneralUtils.getHeaders(selectedClass);
            CellStyle headerStyle = setHeaderStyle(workbook);

            for (Map.Entry<String, List<T>> entry : mappedData.entrySet()) {
                String sheetName = entry.getKey();
                Sheet sheet = workbook.createSheet(sheetName);
                List<T> data = entry.getValue();
                List<String> getMethods = GeneralUtils.setGetMethods(headers);

                int rowIndex = 0;
                Row row = sheet.createRow(rowIndex);
                for (int i = 0; i < fieldNames.size(); i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellStyle(headerStyle);
                    cell.setCellValue(fieldNames.get(i));
                }
                rowIndex++;
                for (T entity : data) {
                    Row dataRow = sheet.createRow(rowIndex);
                    List<Object> values = getMethods.stream().map(method -> {
                        try {
                            return entity.getClass().getMethod(method).invoke(entity);
                        } catch (Exception e) {
                            throw new RuntimeException("Error al generar obtener datos del ExcelResponse - DownloadUtils: " + e);
                        }

                    }).toList();
                    for (int i = 0; i < headers.size(); i++) {
                        Cell cell = dataRow.createCell(i);
                        if (values.get(i) == null) {
                            cell.setCellValue("null");
                        } else {
                            String type = values.get(i).getClass().getSimpleName();
                            if ("Integer".equals(type)) {
                                cell.setCellValue((Integer) values.get(i));
                            } else if ("Long".equals(type)) {
                                cell.setCellValue((Long) values.get(i));
                            } else if ("Double".equals(type)) {
                                cell.setCellValue((Double) values.get(i));
                            } else {
                                cell.setCellValue(values.get(i).toString());
                            }
                        }
                    }
                    rowIndex++;
                }
                formatTable(sheet, data.size(), headers.size() - 1);
            }
            workbook.write(byteArrayOutputStream);
            workbook.close();
            byteArrayOutputStream.close();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error exportando datos a excel - DownloadUtils: " + e);
        }
    }

    public ByteArrayInputStream dataToExcelSheets(List<ExcelSheetData> sheets) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            CellStyle headerStyle = setHeaderStyle(workbook);

            for (ExcelSheetData sheetData : sheets) {
                String sheetName = safeSheetName(sheetData.getSheetName(), workbook);
                Sheet sheet = workbook.createSheet(sheetName);
                Class<?> clazz = sheetData.getSheetClass();
                List<?> data = sheetData.getRows();
                List<String> fieldNames = GeneralUtils.getColumnNames(clazz);
                List<String> headers = GeneralUtils.getHeaders(clazz);
                List<String> getMethods = GeneralUtils.setGetMethods(headers);
                int rowIndex = 0;
                Row headerRow = sheet.createRow(rowIndex);
                for (int i = 0; i < fieldNames.size(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellStyle(headerStyle);
                    cell.setCellValue(fieldNames.get(i));
                }
                rowIndex++;
                for (Object entity : data) {
                    Row dataRow = sheet.createRow(rowIndex);
                    List<Object> values = getMethods.stream().map(method -> {
                        try {
                            return entity.getClass().getMethod(method).invoke(entity);
                        } catch (Exception e) {
                            throw new RuntimeException("Error obteniendo datos Excel - DownloadUtils: " + e.getMessage(), e);
                        }
                    }).toList();

                    for (int i = 0; i < headers.size(); i++) {
                        Cell cell = dataRow.createCell(i);
                        Object v = values.get(i);
                        if (v == null) {
                            cell.setCellValue("");
                        } else {
                            String type = v.getClass().getSimpleName();
                            if ("Integer".equals(type)) cell.setCellValue((Integer) v);
                            else if ("Long".equals(type)) cell.setCellValue((Long) v);
                            else if ("Double".equals(type)) cell.setCellValue((Double) v);
                            else if ("Boolean".equals(type)) cell.setCellValue((Boolean) v);
                            else cell.setCellValue(v.toString());
                        }
                    }
                    rowIndex++;
                }

                formatTable(sheet, data.size(), headers.size() - 1);
            }
            workbook.write(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error exportando datos a excel - DownloadUtils: " + e.getMessage(), e);
        }
    }

    private void formatTable(Sheet sheet, int largeSize, int widthSize) {
        CellRangeAddress filterRange = new CellRangeAddress(0, largeSize, 0, widthSize);
        ((XSSFSheet) sheet).setAutoFilter(filterRange);
        for (int i = 0; i <= widthSize; i++) {
            sheet.autoSizeColumn(i);
        }
        sheet.createFreezePane(0, 1);
    }

    private CellStyle setHeaderStyle(Workbook workbook) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerStyle;
    }

    private String safeSheetName(String desired, Workbook workbook) {
        String base = (desired == null || desired.isBlank()) ? "Hoja" : desired;
        base = base.replaceAll("[:\\\\/?*\\[\\]]", " ").trim();
        if (base.length() > 31) base = base.substring(0, 31);
        String candidate = base;
        int i = 2;
        while (workbook.getSheet(candidate) != null) {
            String suffix = " (" + i + ")";
            int maxBase = Math.min(base.length(), 31 - suffix.length());
            candidate = base.substring(0, maxBase) + suffix;
            i++;
        }
        return candidate;
    }

}
