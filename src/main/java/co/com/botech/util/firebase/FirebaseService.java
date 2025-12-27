package co.com.botech.util.firebase;

import co.com.botech.constants.FirebaseCollectionsConstants;
import co.com.botech.util.generalUtils.FirebaseObjectUtils;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


    public boolean existsInCollection(String rootCollection, String rootId, String collection, String id) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection)
                    .document(id)
                    .get();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error leyendo {} para {}: {}", collection, rootId, t.getMessage());
                        }

                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                log.info("[Firebase] Documento {} existe en {} para {}", id, collection, rootId);
                            } else {
                                log.info("[Firebase] Documento {} NO existe en {} para {}", id, collection, rootId);
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


    public void createRegister(
            Map<String, Object> registerMap,
            String rootCollection,
            String rootId,
            String collection,
            String id
    ) {
        try {
            CollectionReference collectionRef = firestore
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection);

            DocumentReference docRef = ("Autogenerado".equals(id) || id == null)
                    ? collectionRef.document()
                    : collectionRef.document(id);

            ApiFuture<WriteResult> future = docRef.set(registerMap);

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error guardando en {} de {}: {}", collection, rootId, t.getMessage());
                        }

                        @Override
                        public void onSuccess(WriteResult result) {
                            log.info(
                                    "[Firebase] Registro guardado o actualizado en {}/{} correctamente: {}",
                                    rootId, collection, registerMap
                            );
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


    public void deleteRegisterById(
            String rootCollection,
            String rootId,
            String collection,
            String id
    ) {
        try {
            ApiFuture<WriteResult> future = firestore
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection)
                    .document(id)
                    .delete();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error eliminando registro {} en {}: {}", id, rootId, t.getMessage());
                        }

                        @Override
                        public void onSuccess(WriteResult result) {
                            log.info("[Firebase] Registro {} eliminado correctamente en {}/{}", id, rootId, collection);
                        }
                    },
                    MoreExecutors.directExecutor()
            );
        } catch (Exception e) {
            log.error("Error al eliminar en Firebase", e);
            throw new FirebaseException("Error al eliminar en Firebase");
        }
    }


    public void deleteRegisterByCondition(
            String rootCollection,
            String rootId,
            String collection,
            String variable,
            String value
    ) {
        try {
            CollectionReference col = firestore
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection);

            col.whereEqualTo(variable, value)
                    .get()
                    .get()
                    .forEach(document -> {
                        ApiFuture<WriteResult> deleteFuture = col
                                .document(document.getId())
                                .delete();

                        ApiFutures.addCallback(
                                deleteFuture,
                                new ApiFutureCallback<>() {
                                    @Override
                                    public void onFailure(Throwable t) {
                                        log.error("[Firebase] Error eliminando {}={} en {}: {}", variable, value, rootId, t.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(WriteResult result) {
                                        log.info("[Firebase] Registro {}={} eliminado correctamente de {}/{}", variable, value, rootId, collection);
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


    public Map<String, Long> countLiveListRegisters(
            String rootCollection,
            String rootId,
            String collection,
            String variable
    ) {
        try {
            ApiFuture<QuerySnapshot> future = firestore
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection)
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

    public List<Map<String, Object>> getRegisterByCondition(
            String rootCollection,
            String rootId,
            String collection,
            String variable,
            String value
    ) {
        try {
            CollectionReference collectionRef = firestore
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection);

            ApiFuture<QuerySnapshot> query = collectionRef
                    .whereEqualTo(variable, value)
                    .get();

            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            List<Map<String, Object>> results = documents.stream()
                    .map(QueryDocumentSnapshot::getData)
                    .collect(Collectors.toList());

            log.info(
                    "[Firebase] Se obtuvieron {} registros de {}/{} donde {} = {}",
                    results.size(), rootId, collection, variable, value
            );

            return results;

        } catch (Exception e) {
            log.error(
                    "Error obteniendo registros por condición en Firebase para cliente {} y colección {}: {}",
                    rootId, collection, e.getMessage(), e
            );
            throw new RuntimeException("Error al obtener registros por condición en Firebase", e);
        }
    }


    public void deleteRegistersByCollection(
            String rootCollection,
            String rootId,
            String collection
    ) {
        try {
            CollectionReference firestoreCollection = firestore
                    .collection(rootCollection)
                    .document(rootId)
                    .collection(collection);

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

            log.info("[Firebase] Se eliminaron {} registros de {}/{}", deletedRegisters, rootId, collection);

        } catch (Exception e) {
            log.error("Error al eliminar registros por colección en Firebase", e);
            throw new FirebaseException("Error al eliminar registros de Firebase");
        }
    }


    public Optional<FirebaseObjectUtils.LatLng> getLatLngByDocId(String docId) {
        try {
            DocumentSnapshot doc = firestore.collection(FirebaseCollectionsConstants.UBICACION.getName())
                    .document(docId.trim())
                    .get()
                    .get();

            if (!doc.exists() || doc.getData() == null) return Optional.empty();

            Double lat = toDouble(doc.get("latitude"));
            Double lon = toDouble(doc.get("longitude"));
            if (lat == null || lon == null) return Optional.empty();

            return Optional.of(new FirebaseObjectUtils.LatLng(lat, lon));
        } catch (Exception e) {
            log.error("Error leyendo ubicación por ID en Firebase (col={}, id={})", FirebaseCollectionsConstants.UBICACION.getName(), docId, e);
            return Optional.empty();
        }
    }

    public void saveVariableValue(String collection, String docRef, String varName, Object varValue) {
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put(varName, varValue);
        try {
            firestore.collection(collection)
                    .document(docRef)
                    .set(valueMap, SetOptions.merge());
            log.info("Valor Guardado en Firebase  {}/{}- {}: {}", collection, docRef, varName, varValue);
        } catch (Exception e) {
            log.error("Error al guardar varaiable en Firebase: " + e.getMessage());
        }
    }

    public List<String> getDocRefListFromCollection(String collection) {
        List<String> docRefs = new ArrayList<>();
        try {
            Iterable<DocumentReference> references = firestore.collection(collection).listDocuments();
            for (DocumentReference document : references) {
                docRefs.add(document.getId());
            }
            return docRefs;
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar DocRef de Firebase", e);
        }
    }

    public Map<String, Object> findDocRef(String collection, String docRef) {
        try {
            ApiFuture<DocumentSnapshot> future = firestore.collection(collection)
                    .document(docRef)
                    .get();

            return future.get().getData();
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar DocRef deFirebase", e);
        }
    }

    public List<Map<String, Object>> findVehicleHistory(String rootCollection, String collection, String rootId, double minSpeed, Timestamp initialDateTime, Timestamp finalDateTime) {
        try {
            ApiFuture<QuerySnapshot> future = firestore.collection(rootCollection)
                    .document(rootId)
                    .collection(collection)
                    .whereGreaterThan("speedInt", minSpeed)
                    .whereGreaterThan("dateTime", initialDateTime)
                    .whereLessThan("dateTime", finalDateTime)
                    .orderBy("dateTime")
                    .get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            return documents.stream()
                    .map(QueryDocumentSnapshot::getData).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar de Firebase", e);
        }
    }

    public boolean determineBooleanStatus(String collection, String docRef, String booleanVar) {
        try {
            Map<String, Object> doc = findDocRef(collection, docRef);
            return Boolean.TRUE.equals(doc.get(booleanVar));
        } catch (Exception e) {
            throw new RuntimeException("Error al determinar estado de Schedule en Firebase", e);
        }
    }
}