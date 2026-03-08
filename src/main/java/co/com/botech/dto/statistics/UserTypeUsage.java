package co.com.botech.dto.statistics;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTypeUsage {
    private String userType;
    private Long entryRegisters;
    private Long exitRegisters;
    private Long uniqueUsers;
}
