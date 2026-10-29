package co.com.botech.util.excelutils;

import lombok.*;
import java.util.List;

/**
 * Hoja de reporte: titulo, lineas informativas y bloque de totales arriba, y debajo la
 * tabla de sheetClass/rows (mismas reglas que ExcelSheetData: cada campo con @JsonProperty
 * y sin campos static).
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ExcelReportSheet {
    private String sheetName;
    private String title;
    private List<String> infoLines;
    private List<ExcelSummaryItem> summary;
    private Class<?> sheetClass;
    private List<?> rows;
}
