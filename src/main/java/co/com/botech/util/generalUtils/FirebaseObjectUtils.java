package co.com.botech.util.generalUtils;

public class FirebaseObjectUtils {
    public static Double toDouble(Object v) {
        if (v instanceof Number n) return n.doubleValue();
        if (v instanceof String s) try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ignored) {
        }
        return null;
    }

    public record LatLng(double lat, double lon) {
    }

}
