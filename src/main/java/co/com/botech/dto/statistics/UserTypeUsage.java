package co.com.botech.dto.statistics;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User type usage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTypeUsage {
    @Schema(description = "User type")
    private String userType;
    @Schema(description = "Entry registers")
    private Long entryRegisters;
    @Schema(description = "Exit registers")
    private Long exitRegisters;
    @Schema(description = "Unique users")
    private Long uniqueUsers;
}
