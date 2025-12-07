package co.com.botech.dto.school;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SchoolDTO {
    private Long id;
    @NotNull
    private String name;
}
