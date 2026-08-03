package co.com.botech.constants;

import lombok.Getter;

@Getter
public enum AttendanceScanType {
    BOARDING("boarding", AttendanceTypeConstants.SUBIDA_BUS),
    DESCENT("descent", AttendanceTypeConstants.BAJADA_BUS);

    private final String wireValue;
    private final AttendanceTypeConstants attendanceType;

    AttendanceScanType(String wireValue, AttendanceTypeConstants attendanceType) {
        this.wireValue = wireValue;
        this.attendanceType = attendanceType;
    }

    public static AttendanceScanType fromWire(String value) {
        if (value == null) return null;
        for (AttendanceScanType type : values()) {
            if (type.wireValue.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
