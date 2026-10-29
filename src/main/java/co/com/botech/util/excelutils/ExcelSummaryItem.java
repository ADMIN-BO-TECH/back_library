package co.com.botech.util.excelutils;

import lombok.*;

/** Par etiqueta/valor del bloque de totales que va arriba de la tabla en un reporte. */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ExcelSummaryItem {
    private String label;
    private Object value;
}
