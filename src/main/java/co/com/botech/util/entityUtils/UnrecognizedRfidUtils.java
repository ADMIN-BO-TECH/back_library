package co.com.botech.util.entityUtils;

import co.com.botech.constants.FirebaseCollectionsConstants;
import co.com.botech.entity.RfidRegister;
import co.com.botech.util.firebase.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class UnrecognizedRfidUtils {
    private final FirebaseService firebaseService;

    public void saveUnrecognizedRfid(String rfid, RfidRegister rfidRegister, java.sql.Timestamp timestamp) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("date", timestamp);
            data.put("readerDescription", rfidRegister.getDescription());
            data.put("readerRfidTag", rfidRegister.getRfidTag());
            data.put("rfid", rfid);
            firebaseService.upsertDocumentBasic(
                    FirebaseCollectionsConstants.UNRECOGNIZED_RFID.getName(), rfid, data);
            log.info("RFID no reconocido guardado en Firebase: {}", rfid);
        } catch (Exception e) {
            log.error("Error guardando RFID no reconocido {}: {}", rfid, e.getMessage());
        }
    }
}
