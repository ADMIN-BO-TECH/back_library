package co.com.botech.customDto;

/** Una fila por (itemKey, status): usada por externa, kit y documentos. */
public interface PreoperationItemBreakdownProjection {
    String getItemKey();
    String getStatus();
    Long getTotal();
}
