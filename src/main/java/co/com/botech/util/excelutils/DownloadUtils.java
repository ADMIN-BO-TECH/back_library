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

    private static final short TITLE_FONT_SIZE = 14;

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
                            } else if ("Boolean".equals(type)) {
                                Boolean boolValue = (Boolean) values.get(i);
                                cell.setCellValue(boolValue ? "SI" : "NO");
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
                Sheet sheet = workbook.createSheet(safeSheetName(sheetData.getSheetName(), workbook));
                writeTable(sheet, 0, sheetData.getSheetClass(), sheetData.getRows(), headerStyle);
            }
            workbook.write(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error exportando datos a excel - DownloadUtils: " + e.getMessage(), e);
        }
    }

    /**
     * Igual que dataToExcelSheets, pero cada hoja lleva arriba un titulo, lineas
     * informativas y un bloque de totales (etiqueta/valor); la tabla va debajo.
     */
    public ByteArrayInputStream dataToExcelReport(List<ExcelReportSheet> sheets) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            CellStyle headerStyle = setHeaderStyle(workbook);
            CellStyle titleStyle = setTitleStyle(workbook);
            CellStyle labelStyle = setLabelStyle(workbook);

            for (ExcelReportSheet report : sheets) {
                Sheet sheet = workbook.createSheet(safeSheetName(report.getSheetName(), workbook));
                // Titulo e info se combinan a lo ancho de la tabla: asi no inflan el
                // autoSize de la primera columna (autoSizeColumn ignora celdas combinadas).
                int lastColumn = Math.max(GeneralUtils.getHeaders(report.getSheetClass()).size() - 1, 1);
                int rowIndex = 0;

                if (report.getTitle() != null && !report.getTitle().isBlank()) {
                    writeMergedRow(sheet, rowIndex++, report.getTitle(), titleStyle, lastColumn);
                }
                for (String line : nullSafe(report.getInfoLines())) {
                    writeMergedRow(sheet, rowIndex++, line, null, lastColumn);
                }

                List<ExcelSummaryItem> summary = nullSafe(report.getSummary());
                if (!summary.isEmpty()) {
                    rowIndex++;
                    for (ExcelSummaryItem item : summary) {
                        Row row = sheet.createRow(rowIndex++);
                        Cell label = row.createCell(0);
                        label.setCellStyle(labelStyle);
                        label.setCellValue(item.getLabel());
                        writeCellValue(row.createCell(1), item.getValue());
                    }
                }

                if (rowIndex > 0) rowIndex++;
                writeTable(sheet, rowIndex, report.getSheetClass(), nullSafe(report.getRows()), headerStyle);
            }
            workbook.write(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error exportando reporte a excel - DownloadUtils: " + e.getMessage(), e);
        }
    }

    /** Escribe encabezado + filas desde startRow y aplica filtro, ancho y panel congelado. */
    private void writeTable(Sheet sheet, int startRow, Class<?> clazz, List<?> data, CellStyle headerStyle) {
        List<String> fieldNames = GeneralUtils.getColumnNames(clazz);
        List<String> headers = GeneralUtils.getHeaders(clazz);
        List<String> getMethods = GeneralUtils.setGetMethods(headers);

        int rowIndex = startRow;
        Row headerRow = sheet.createRow(rowIndex++);
        for (int i = 0; i < fieldNames.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(fieldNames.get(i));
        }
        for (Object entity : data) {
            Row dataRow = sheet.createRow(rowIndex++);
            for (int i = 0; i < getMethods.size(); i++) {
                writeCellValue(dataRow.createCell(i), readValue(entity, getMethods.get(i)));
            }
        }
        formatTable(sheet, startRow, data.size(), headers.size() - 1);
    }

    private Object readValue(Object entity, String getMethod) {
        try {
            return entity.getClass().getMethod(getMethod).invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error obteniendo datos Excel - DownloadUtils: " + e.getMessage(), e);
        }
    }

    private void writeCellValue(Cell cell, Object v) {
        if (v == null) {
            cell.setCellValue("");
            return;
        }
        String type = v.getClass().getSimpleName();
        if ("Integer".equals(type)) cell.setCellValue((Integer) v);
        else if ("Long".equals(type)) cell.setCellValue((Long) v);
        else if ("Double".equals(type)) cell.setCellValue((Double) v);
        else if ("Boolean".equals(type)) cell.setCellValue((Boolean) v ? "SI" : "NO");
        else cell.setCellValue(v.toString());
    }

    private void writeMergedRow(Sheet sheet, int rowIndex, String text, CellStyle style, int lastColumn) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue(text);
        if (style != null) cell.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, lastColumn));
    }

    private void formatTable(Sheet sheet, int largeSize, int widthSize) {
        formatTable(sheet, 0, largeSize, widthSize);
    }

    private void formatTable(Sheet sheet, int headerRow, int dataSize, int lastColumn) {
        CellRangeAddress filterRange = new CellRangeAddress(headerRow, headerRow + dataSize, 0, lastColumn);
        ((XSSFSheet) sheet).setAutoFilter(filterRange);
        for (int i = 0; i <= lastColumn; i++) {
            sheet.autoSizeColumn(i);
        }
        sheet.createFreezePane(0, headerRow + 1);
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

    private CellStyle setTitleStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints(TITLE_FONT_SIZE);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private CellStyle setLabelStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static <E> List<E> nullSafe(List<E> list) {
        return list != null ? list : List.of();
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
