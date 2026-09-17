package co.com.botech.customDto;

/** Para el ranking de flota: el item mas problematico, por vehiculo. */
public interface PreoperationTopIssueProjection {
    Long getVehicleId();
    String getCategory();
    String getItemKey();
    Long getIssueCount();
}
