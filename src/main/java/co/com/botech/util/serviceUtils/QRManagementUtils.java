package co.com.botech.util.serviceUtils;

import co.com.botech.constants.FirebaseCollectionsConstants;
import co.com.botech.constants.RegisterTypeNameConstants;
import co.com.botech.entity.Authorization;
import co.com.botech.entity.RfidRegister;
import co.com.botech.util.firebase.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@AllArgsConstructor
@Slf4j
public class QRManagementUtils {
    private static final String EVENT_ENTRADA = "ENTRADA";
    private static final String EVENT_SALIDA  = "SALIDA";
    private static final String HISTORIAL_LISTA_EN_VIVO = FirebaseCollectionsConstants.HISTORIAL_LISTA_EN_VIVO.getName();
    private static final String HISTORIAL_LISTA_RECOGIDA = FirebaseCollectionsConstants.HISTORIAL_LISTA_RECOGIDA.getName();
    private static final ZoneId BOGOTA_ZONE = ZoneId.of("America/Bogota");

    private final FirebaseService firebaseService;

    private static LocalDateTime toBogota(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault())
                .withZoneSameInstant(BOGOTA_ZONE)
                .toLocalDateTime();
    }

    public void insertUserInLiveList(String clientName, String tenantName, String collectionName,
                                     String schoolIdentificator, String name, String category,
                                     LocalDateTime now) {
        Map<String, Object> mapRegister = new HashMap<>();
        mapRegister.put("id_persona", schoolIdentificator);
        mapRegister.put("nombre", name);
        mapRegister.put("categoria", category);
        mapRegister.put("fecha_registro", Timestamp.valueOf(toBogota(now)));
        firebaseService.createRegister(mapRegister, clientName, tenantName, collectionName, schoolIdentificator);
        log.info("Registro agregado a Firebase ({}): {}/{}/{}/{}",
                category, clientName, tenantName, collectionName, schoolIdentificator);
    }

    public void insertUserInBusLiveList(String clientName, String tenantName, String collectionName, String rfidId, String busCollection,
                                        String schoolIdentificator, String name, String category,
                                        LocalDateTime now) {
        Map<String, Object> mapRegister = new HashMap<>();
        mapRegister.put("id_persona", schoolIdentificator);
        mapRegister.put("nombre", name);
        mapRegister.put("categoria", category);
        mapRegister.put("fecha_registro", Timestamp.valueOf(toBogota(now)));
        firebaseService.createBusRegister(mapRegister, clientName, tenantName, collectionName, rfidId, busCollection, schoolIdentificator);
        log.info("Registro agregado a Firebase ({}): {}/{}/{}/{}/{}/{}",
                category, clientName, tenantName, collectionName, rfidId, busCollection, schoolIdentificator);
    }

    public void deleteUserInBusLiveList(String clientName, String tenantName, String collectionName,
                                        String rfidId, String busCollection, String schoolIdentificator, String userType) {
        firebaseService.deleteRegisterByIdOnBus(clientName, tenantName, collectionName, rfidId, busCollection, schoolIdentificator);
        log.info("Registro eliminado de Firebase ({}): {}/{}/{}/{}/{}/{}",
                userType, clientName, tenantName, collectionName, rfidId, busCollection, schoolIdentificator);
    }

    public void deleteUserInLiveList(String clientName, String tenantName, String collectionName,
                                     String schoolIdentificator, String userType) {
        firebaseService.deleteRegisterById(clientName, tenantName, collectionName, schoolIdentificator);
        log.info("Registro eliminado de Firebase ({}): {}/{}/{}/{}",
                userType, clientName, tenantName, collectionName, schoolIdentificator);
    }

    public AtomicInteger pickupListProcess(LocalDateTime now, List<Authorization> authorizationList,
                                           RfidRegister rfidRegister,
                                           String clientName, String tenantName) {
        AtomicInteger pickupValidRegistersCounter = new AtomicInteger();
        authorizationList.forEach(authorization -> {
            String studentId = authorization.getStudent().getStudentId().toString();
            if (firebaseService.existsInCollection(clientName, tenantName,
                    FirebaseCollectionsConstants.LISTA_EN_VIVO.getName(), studentId)) {
                Map<String, Object> pickupRegister = new HashMap<>();
                String studentName = authorization.getStudent().getFirstName() + " " + authorization.getStudent().getLastName();
                String authorizedPersonName = authorization.getAuthorizedPerson().getFirstName() + " " + authorization.getAuthorizedPerson().getLastName();
                String pickUpMode = RegisterTypeNameConstants.QR_REGISTER.getName() + " - " + rfidRegister.getDescription();
                pickupRegister.put("id_estudiante", studentId);
                pickupRegister.put("nombre_estudiante", studentName);
                pickupRegister.put("fecha_hora_recogida", Timestamp.valueOf(toBogota(now)));
                pickupRegister.put("documento_persona_autorizada", authorization.getAuthorizedPerson().getDocumentNumber());
                pickupRegister.put("nombre_completo_persona_autorizada", authorizedPersonName);
                pickupRegister.put("modo_recogida", pickUpMode);
                firebaseService.createRegister(pickupRegister, clientName, tenantName,
                        FirebaseCollectionsConstants.LISTA_RECOGIDA.getName(), studentId);
                log.info("Registro agregado a Firebase (Persona Autorizada): {}/{}/{}/{}",
                        clientName, tenantName, FirebaseCollectionsConstants.LISTA_RECOGIDA.getName(),
                        authorization.getAuthorizedPerson().getDocumentNumber());
                pickupValidRegistersCounter.getAndIncrement();
            } else {
                log.info("Estudiante con ID {} no se encuentra en lista en vivo", studentId);
            }
        });
        return pickupValidRegistersCounter;
    }

    public void insertUserInLiveListWithHistory(String clientName, String schoolName, String collection,
                                                String documentNumber, String name, String category,
                                                LocalDateTime now) {
        insertUserInLiveList(clientName, schoolName, collection, documentNumber, name, category, now);
        saveLiveListHistory(clientName, schoolName, HISTORIAL_LISTA_EN_VIVO,
                documentNumber, name, category, EVENT_ENTRADA, now);
    }

    public void deleteUserInLiveListWithHistory(String clientName, String schoolName, String collection,
                                                String documentNumber, String name, String category,
                                                LocalDateTime now) {
        deleteUserInLiveList(clientName, schoolName, collection, documentNumber, category);
        saveLiveListHistory(clientName, schoolName, HISTORIAL_LISTA_EN_VIVO,
                documentNumber, name, category, EVENT_SALIDA, now);
    }

    public void insertUserInPickupListWithHistory(String clientName, String schoolName, String collection,
                                                  String documentNumber, String name, String category,
                                                  LocalDateTime now) {
        insertUserInLiveList(clientName, schoolName, collection, documentNumber, name, category, now);
        saveLiveListHistory(clientName, schoolName, HISTORIAL_LISTA_RECOGIDA,
                documentNumber, name, category, EVENT_ENTRADA, now);
    }

    public void deleteUserInPickupListWithHistory(String clientName, String schoolName, String collection,
                                                  String documentNumber, String name, String category,
                                                  LocalDateTime now) {
        deleteUserInLiveList(clientName, schoolName, collection, documentNumber, category);
        saveLiveListHistory(clientName, schoolName, HISTORIAL_LISTA_RECOGIDA,
                documentNumber, name, category, EVENT_SALIDA, now);
    }

    private void saveLiveListHistory(String clientName, String schoolName, String historyCollection,
                                     String documentNumber, String name, String category,
                                     String event, LocalDateTime now) {
        Map<String, Object> history = new HashMap<>();
        history.put("id_persona", documentNumber);
        history.put("idOriginal", documentNumber);
        history.put("nombre", name);
        history.put("categoria", category);
        history.put("evento", event);
        history.put("fecha_registro", Timestamp.valueOf(toBogota(now)));
        history.put("timestamp", Timestamp.valueOf(toBogota(LocalDateTime.now())));

        firebaseService.createRegister(history, clientName, schoolName, historyCollection, null);
    }
}