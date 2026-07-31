package co.com.botech.util.firebase;

import co.com.botech.constants.FirebaseCollectionsConstants;
import co.com.botech.constants.StorageConstants;
import co.com.botech.util.generalUtils.BucketStorageUtils;
import co.com.botech.util.generalUtils.FirebaseObjectUtils;
import co.com.botech.util.utilObjects.StorageImageObject;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static co.com.botech.util.generalUtils.FirebaseObjectUtils.toDouble;

@Getter
@Slf4j
@RequiredArgsConstructor
@Service
public class FirebaseService {

    private final Firestore firestore;
    private final Storage storage;
    private static final String AUTO_GENERATED_ID = "Autogenerado";

    public boolean existsInCollectionBasic(String id, String collection) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore.collection(collection)
                    .document(id)
                    .get();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error leyendo {} : {}", collection, t.getMessage());
                        }

                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            log.info("[Firebase] Documento {} recuperado correctamente  {}", id, collection);
                        }
                    },
                    MoreExecutors.directExecutor()
            );

            return future.get().exists();
        } catch (Exception e) {
            log.error("Error al validar existencia en Firebase", e);
            throw new FirebaseException("Error al validar en Firebase");
        }
    }

    public boolean existsInCollection(String clientName, String tenantName, String collectionName, String id) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName)
                    .document(id)
                    .get();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error leyendo {} para {}/{}: {}",
                                    collectionName, clientName, tenantName, t.getMessage());
                        }

                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                log.info("[Firebase] Documento {} existe en {} para {}/{}",
                                        id, collectionName, clientName, tenantName);
                            } else {
                                log.info("[Firebase] Documento {} NO existe en {} para {}/{}",
                                        id, collectionName, clientName, tenantName);
                            }
                        }
                    },
                    MoreExecutors.directExecutor()
            );

            return future.get().exists();
        } catch (Exception e) {
            log.error("[Firebase] Error al validar existencia en Firebase", e);
            throw new FirebaseException("Error al validar existencia en Firebase");
        }
    }

    public void createRegister(Map<String, Object> registerMap, String clientName, String tenantName, String collectionName, String id) {
        try {
            CollectionReference collectionRef = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName);

            DocumentReference docRef = (id == null || id.isBlank() || AUTO_GENERATED_ID.equals(id))
                    ? collectionRef.document()
                    : collectionRef.document(id);

            ApiFuture<WriteResult> future = docRef.set(registerMap);

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error guardando en {} de {}/{}: {}",
                                    collectionName, clientName, tenantName, t.getMessage());
                        }

                        @Override
                        public void onSuccess(WriteResult result) {
                            log.info("[Firebase] Registro guardado o actualizado en {}/{}/{} correctamente: {}",
                                    clientName, tenantName, collectionName, registerMap);
                        }
                    },
                    MoreExecutors.directExecutor()
            );

            future.get();
        } catch (Exception e) {
            log.error("Error al registrar en Firebase", e);
            throw new FirebaseException("Error al registrar en Firebase");
        }
    }

    public void deleteRegisterById(String clientName, String tenantName, String collectionName, String id) {
        try {
            ApiFuture<WriteResult> future = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName)
                    .document(id)
                    .delete();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error eliminando registro {} en {}/{}/{}: {}",
                                    id, clientName, tenantName, collectionName, t.getMessage());
                        }

                        @Override
                        public void onSuccess(WriteResult result) {
                            log.info("[Firebase] Registro {} eliminado correctamente en {}/{}/{}",
                                    id, clientName, tenantName, collectionName);
                        }
                    },
                    MoreExecutors.directExecutor()
            );
        } catch (Exception e) {
            log.error("Error al eliminar en Firebase", e);
            throw new FirebaseException("Error al eliminar en Firebase");
        }
    }

    public void deleteRegisterByCondition(String clientName, String tenantName, String collectionName, String variable, String value) {
        try {
            CollectionReference col = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName);

            QuerySnapshot snapshot = col.whereEqualTo(variable, value).get().get();
            snapshot.forEach(document -> {
                ApiFuture<WriteResult> deleteFuture = col
                        .document(document.getId())
                        .delete();

                ApiFutures.addCallback(
                        deleteFuture,
                        new ApiFutureCallback<>() {
                            @Override
                            public void onFailure(Throwable t) {
                                log.error("[Firebase] Error eliminando {}={} en {}/{}/{}: {}",
                                        variable, value, clientName, tenantName, collectionName, t.getMessage());
                            }

                            @Override
                            public void onSuccess(WriteResult result) {
                                log.info("[Firebase] Registro {}={} eliminado correctamente de {}/{}/{}",
                                        variable, value, clientName, tenantName, collectionName);
                            }
                        },
                        MoreExecutors.directExecutor()
                );
            });
        } catch (Exception e) {
            log.error("Error al eliminar registros por condición en Firebase", e);
            throw new FirebaseException("Error al eliminar registros por condición");
        }
    }

    public Map<String, Long> countLiveListRegisters(String clientName, String tenantName, String collectionName, String variable) {
        try {
            ApiFuture<QuerySnapshot> future = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName)
                    .get();

            return getCountOfVariableFromFirebaseRegisters(future, variable);
        } catch (Exception e) {
            log.error("Error al contar registros en Firebase", e);
            throw new FirebaseException("Error al contar registros en Firebase");
        }
    }

    private Map<String, Long> getCountOfVariableFromFirebaseRegisters(ApiFuture<QuerySnapshot> registers, String variable) {
        try {
            List<QueryDocumentSnapshot> documents = registers.get().getDocuments();
            Map<String, Long> firebaseRegisters = documents.stream()
                    .filter(doc -> doc.contains(variable))
                    .map(doc -> doc.getString(variable))
                    .filter(Objects::nonNull)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            firebaseRegisters.put("Total", (long) documents.size());
            return firebaseRegisters;
        } catch (Exception e) {
            log.error("Error al preparar mapa de conteo en Firebase", e);
            throw new FirebaseException("Error al preparar mapa de Firebase");
        }
    }

    public List<Map<String, Object>> getRegisterByCondition(String clientName, String tenantName, String collectionName, String variable, String value) {
        try {
            CollectionReference collectionRef = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName);

            ApiFuture<QuerySnapshot> query = collectionRef
                    .whereEqualTo(variable, value)
                    .get();

            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            List<Map<String, Object>> results = documents.stream()
                    .map(QueryDocumentSnapshot::getData)
                    .collect(Collectors.toList());

            log.info("[Firebase] Se obtuvieron {} registros de {}/{}/{} donde {} = {}",
                    results.size(), clientName, tenantName, collectionName, variable, value);

            return results;

        } catch (Exception e) {
            log.error("Error obteniendo registros por condición en Firebase para {}/{}/{}: {}",
                    clientName, tenantName, collectionName, e.getMessage(), e);
            throw new FirebaseException("Error al obtener registros por condición en Firebase");
        }
    }

    public void deleteRegistersByCollection(String clientName, String tenantName, String collectionName) {
        try {
            CollectionReference firestoreCollection = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName);

            int deletedRegisters = 0;

            while (true) {
                ApiFuture<QuerySnapshot> future = firestoreCollection.limit(100).get();
                QuerySnapshot documents = future.get();

                if (documents.isEmpty()) break;

                WriteBatch deleteBatch = firestore.batch();
                for (DocumentSnapshot snapshot : documents.getDocuments()) {
                    deleteBatch.delete(snapshot.getReference());
                    deletedRegisters++;
                }

                deleteBatch.commit().get();
            }

            log.info("[Firebase] Se eliminaron {} registros de {}/{}/{}",
                    deletedRegisters, clientName, tenantName, collectionName);

        } catch (Exception e) {
            log.error("Error al eliminar registros por colección en Firebase", e);
            throw new FirebaseException("Error al eliminar registros de Firebase");
        }
    }

    public Optional<FirebaseObjectUtils.LatLng> getLatLngByDocId(String clientName, String docId) {
        try {
            DocumentSnapshot doc = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(FirebaseCollectionsConstants.GLOBAL_TENANT.getName())
                    .collection(FirebaseCollectionsConstants.UBICACION.getName())
                    .document(docId.trim())
                    .get()
                    .get();

            if (!doc.exists() || doc.getData() == null) return Optional.empty();

            Double lat = toDouble(doc.get("latitude"));
            Double lon = toDouble(doc.get("longitude"));
            if (lat == null || lon == null) return Optional.empty();

            return Optional.of(new FirebaseObjectUtils.LatLng(lat, lon));
        } catch (Exception e) {
            log.error("Error leyendo ubicación por ID en Firebase (client={}, col={}, id={})",
                    clientName, FirebaseCollectionsConstants.UBICACION.getName(), docId, e);
            return Optional.empty();
        }
    }

    public Optional<FirebaseObjectUtils.LocationInfo> getLocationInfo(String clientName, String docId) {
        try {
            DocumentSnapshot doc = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(FirebaseCollectionsConstants.GLOBAL_TENANT.getName())
                    .collection(FirebaseCollectionsConstants.UBICACION.getName())
                    .document(docId.trim())
                    .get()
                    .get();

            if (!doc.exists() || doc.getData() == null) return Optional.empty();

            Double lat = toDouble(doc.get("latitude"));
            Double lon = toDouble(doc.get("longitude"));
            String status = doc.getString("status");

            Timestamp ts = doc.getTimestamp("dateTime");

            String timestamp = null;
            Long secondsAge = null;

            if (ts != null) {
                Instant instant = Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos());
                timestamp = instant.toString();
                secondsAge = Duration.between(instant, Instant.now()).getSeconds();
            }

            if (lat == null || lon == null) return Optional.empty();

            return Optional.of(new FirebaseObjectUtils.LocationInfo(
                    lat,
                    lon,
                    timestamp,
                    status,
                    secondsAge
            ));

        } catch (Exception e) {
            log.error("Error leyendo ubicación por ID en Firebase (client={}, col={}, id={})",
                    clientName, FirebaseCollectionsConstants.UBICACION.getName(), docId, e);
            return Optional.empty();
        }
    }

    public void saveVariableValue(String clientName, String tenantName, String collectionName, String docRef, String varName, Object varValue) {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put(varName, varValue);
        try {
            firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName)
                    .document(docRef)
                    .set(valueMap, SetOptions.merge());
            log.info("[Firebase] Valor guardado en {}/{}/{}/{} - {}: {}",
                    clientName, tenantName, collectionName, docRef, varName, varValue);
        } catch (Exception e) {
            log.error("[Firebase] Error al guardar variable en Firebase: {}", e.getMessage(), e);
        }
    }

    public List<String> getDocRefListFromCollection(String clientName, String tenantName, String collectionName) {
        List<String> docRefs = new ArrayList<>();
        try {
            Iterable<DocumentReference> references = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName)
                    .listDocuments();

            for (DocumentReference document : references) {
                docRefs.add(document.getId());
            }
            return docRefs;
        } catch (Exception e) {
            log.error("[Firebase] Error al recuperar DocRef de {}/{}/{}: {}",
                    clientName, tenantName, collectionName, e.getMessage(), e);
            throw new FirebaseException("Error al recuperar DocRef de Firebase");
        }
    }

    public Map<String, Object> findDocRef(String clientName, String tenantName, String collectionName, String docRef) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName)
                    .document(docRef)
                    .get();

            return future.get().getData();
        } catch (Exception e) {
            log.error("[Firebase] Error al recuperar DocRef {} de {}/{}/{}: {}",
                    docRef, clientName, tenantName, collectionName, e.getMessage(), e);
            throw new FirebaseException("Error al recuperar DocRef de Firebase");
        }
    }

    public List<Map<String, Object>> findLastSpeedingExcessRegister(String clientName, String tenantName, String collectionName, String subcollection,
                                                                    String id, double minSpeed, int limit, int offset) {
        try {
            ApiFuture<QuerySnapshot> future = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(collectionName)
                    .document(id)
                    .collection(subcollection)
                    .whereGreaterThan("speedInt", minSpeed)
                    .orderBy("dateTime", Query.Direction.DESCENDING)
                    .limit(limit)
                    .offset(offset)
                    .get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            return documents.stream()
                    .map(QueryDocumentSnapshot::getData)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("[Firebase] Error recuperando excesos de velocidad en {}/{}/{}/{}/{}: {}",
                    clientName, tenantName, collectionName, id, subcollection, e.getMessage(), e);
            throw new FirebaseException("Error al recuperar de Firebase");
        }
    }

    public List<Map<String, Object>> findVehicleHistory(String clientName, String tenantName, String rootCollection, String collection, String rootId,
                                                        double minSpeed, Timestamp initialDateTime, Timestamp finalDateTime) {
        try {
            ApiFuture<QuerySnapshot> future = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection)
                    .whereGreaterThan("speedInt", minSpeed)
                    .whereGreaterThan("dateTime", initialDateTime)
                    .whereLessThan("dateTime", finalDateTime)
                    .orderBy("dateTime")
                    .get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            return documents.stream()
                    .map(QueryDocumentSnapshot::getData)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("[Firebase] Error recuperando historial de vehículo en {}/{}/{}/{}/{}: {}",
                    clientName, tenantName, rootCollection, rootId, collection, e.getMessage(), e);
            throw new FirebaseException("Error al recuperar de Firebase");
        }
    }

    public List<Map<String, Object>> getHistoryByDateRange(String clientName, String tenantName, String rootCollection, String docId,
                                                           String subCollection, String dateTimeField, Timestamp start, Timestamp end) {
        try {
            ApiFuture<QuerySnapshot> future = firestore
                    .collection(FirebaseCollectionsConstants.ROOT_COLLECTION.getName())
                    .document(clientName)
                    .collection(FirebaseCollectionsConstants.ROOT_SUBCOLLECTION.getName())
                    .document(tenantName)
                    .collection(rootCollection)
                    .document(docId)
                    .collection(subCollection)
                    .whereGreaterThanOrEqualTo(dateTimeField, start)
                    .whereLessThanOrEqualTo(dateTimeField, end)
                    .orderBy(dateTimeField)
                    .get();

            return future.get().getDocuments().stream()
                    .map(QueryDocumentSnapshot::getData)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("[Firebase] Error obteniendo historial de {}/{}/{}/{}/{} entre {} y {}",
                    clientName, tenantName, rootCollection, docId, subCollection, start, end, e);
            return Collections.emptyList();
        }
    }

    public boolean determineBooleanStatus(String clientName, String tenantName, String collectionName, String docRef, String booleanVar) {
        try {
            Map<String, Object> doc = findDocRef(clientName, tenantName, collectionName, docRef);
            return Boolean.TRUE.equals(doc.get(booleanVar));
        } catch (Exception e) {
            log.error("[Firebase] Error determinando estado booleano '{}' en {}/{}/{}/{}: {}",
                    booleanVar, clientName, tenantName, collectionName, docRef, e.getMessage(), e);
            throw new FirebaseException("Error al determinar estado de Schedule en Firebase");
        }
    }

    public void upsertDocumentBasic(String collection, String id, Map<String, Object> data) {
        try {
            ApiFuture<WriteResult> future = firestore
                    .collection(collection)
                    .document(id)
                    .set(data, SetOptions.merge());

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error upsert {}/{}: {}", collection, id, t.getMessage());
                        }

                        @Override
                        public void onSuccess(WriteResult result) {
                            log.info("[Firebase] Upsert OK {}/{}", collection, id);
                        }
                    },
                    MoreExecutors.directExecutor()
            );

            future.get();
        } catch (Exception e) {
            log.error("[Firebase] Error upsertDocumentBasic", e);
            throw new FirebaseException("Error al upsert en Firebase");
        }
    }

    public String uploadAnnouncementImage(StorageImageObject file, Long schoolId, Long announcementId, String clientName
    ) {

        validateImage(file);

        try {
            String objectPath = BucketStorageUtils.buildPathAnnouncementName(
                    file.getOriginalFilename(),
                    schoolId,
                    announcementId,
                    clientName
            );

            String bucketName = StorageConstants.BUCKET_NAME;

            BlobId blobId = BlobId.of(bucketName, objectPath);

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();

            storage.create(blobInfo, file.getBytes());

            log.info("[Firebase] Imagen subida correctamente a {}", objectPath);

            return String.format(
                    "https://storage.googleapis.com/%s/%s",
                    bucketName,
                    objectPath
            );

        } catch (Exception e) {
            log.error("[Firebase] Error subiendo imagen de anuncio", e);
            throw new FirebaseException("Error al subir imagen a Firebase Storage");
        }
    }

    public void deleteAnnouncementImage(String objectPath) {
        try {
            String bucketName = StorageConstants.BUCKET_NAME;
            BlobId blobId = BlobId.of(bucketName, objectPath);
            boolean deleted = storage.delete(blobId);

            if (!deleted) {
                log.warn("[Firebase] No se encontró imagen para eliminar: {}", objectPath);
            } else {
                log.info("[Firebase] Imagen eliminada correctamente: {}", objectPath);
            }

        } catch (Exception e) {
            log.error("[Firebase] Error eliminando imagen de anuncio", e);
            throw new FirebaseException("Error al eliminar imagen en Firebase Storage");
        }
    }

    private void validateImage(StorageImageObject file) {

        if (file == null) {
            throw new IllegalArgumentException("Archivo requerido");
        }

        if (file.getContentType() == null ||
                !StorageConstants.ALLOWED_IMAGE_TYPES.contains(file.getContentType())) {

            throw new IllegalArgumentException(
                    "Archivo no es una imagen válida. Tipos permitidos: JPEG, PNG, WebP"
            );
        }

        if (file.getSize() > StorageConstants.MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException(
                    "Archivo excede el tamaño máximo permitido (5MB)"
            );
        }
    }
}


