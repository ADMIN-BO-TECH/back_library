package co.com.botech.dto.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LastCardReadDTO {
    private String dateTime;
    private String registerType;
    private String readerDescription;
    private boolean readOnReferenceDate;
}
