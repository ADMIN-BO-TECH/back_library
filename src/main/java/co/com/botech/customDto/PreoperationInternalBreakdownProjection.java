package co.com.botech.customDto;

/** Como PreoperationItemBreakdownProjection pero con el nivel de fluido, para interna. */
public interface PreoperationInternalBreakdownProjection {
    String getItemKey();
    String getStatus();
    String getFluidLevel();
    Long getTotal();
}
