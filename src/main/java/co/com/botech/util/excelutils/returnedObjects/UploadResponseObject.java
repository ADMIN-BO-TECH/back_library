package co.com.botech.util.excelutils.returnedObjects;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadResponseObject <T> {
    private List<T> uploadedTotalRegisters;
    private List<T> currentRegisters;
    private List<T> newRegisters;
    private List<T> updatedRegisters;
    private List<T> deletedRegisters;
}
