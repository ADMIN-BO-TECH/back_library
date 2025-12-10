package co.com.botech.dto.student;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummarySectionResponse {
    private String id;
    private List<Object> items;
}
