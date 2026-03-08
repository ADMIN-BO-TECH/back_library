package co.com.botech.util.excelutils.returnedObjects;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadManipulationRestrictions {
    private boolean canCreate;
    private boolean canUpdate;
    private boolean canDelete;

}
