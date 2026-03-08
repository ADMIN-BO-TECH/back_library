package co.com.botech.util.excelutils;

import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ExcelSheetData {
    private String sheetName;
    private Class<?> sheetClass;
    private List<?> rows;
}