package co.com.botech.util.firebase;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Slf4j
@RequiredArgsConstructor
@Service
public class FirebaseService {

    private final Firestore firestore;

    @Value("${firebase.root-collection:BO-TECH-PASS}")
    private String rootCollection;

    private CollectionReference getSchoolCollection(String schoolName, String collection) {
        return firestore
                .collection(rootCollection)
                .document(schoolName)
                .collection(collection);
    }

    public boolean existsInCollection(String id, String collection, String schoolName) {
        try {
            ApiFuture<DocumentSnapshot> future = getSchoolCollection(schoolName, collection)
                    .document(id)
                    .get();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error leyendo {} para cliente {}: {}", collection, schoolName, t.getMessage());
                        }

                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            log.info("[Firebase] Documento {} recuperado correctamente para cliente {}", id, schoolName);
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

    public void createRegister(Map<String, Object> registerMap, String id, String collection, String schoolName) {
        try {
            CollectionReference collectionRef = getSchoolCollection(schoolName, collection);
            DocumentReference docRef = ("Autogenerado".equals(id) || id == null)
                    ? collectionRef.document()
                    : collectionRef.document(id);

            ApiFuture<WriteResult> future = docRef.set(registerMap);
            future.get();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error guardando en {} de {}: {}", collection, schoolName, t.getMessage());
                        }

                        @Override
                        public void onSuccess(WriteResult result) {
                            log.info("[Firebase] Registro guardado o actualizado en {}/{} correctamente: {}", schoolName, collection, registerMap);
                        }
                    },
                    MoreExecutors.directExecutor()
            );
        } catch (Exception e) {
            log.error("Error al registrar en Firebase", e);
            throw new FirebaseException("Error al registrar en Firebase");
        }
    }

    public boolean existInCollectionWithSchoolName(String id, String collection, String schoolName) {
        try {
            ApiFuture<DocumentSnapshot> future = getSchoolCollection(schoolName, collection)
                    .document(id)
                    .get();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error leyendo {} para cliente {}: {}", collection, schoolName, t.getMessage());
                        }

                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            log.info("[Firebase] Documento {} recuperado correctamente para cliente {}", id, schoolName);
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

    public boolean existsInSubCollection (String mainId, String subId, String collection, String subCollection){
        try {
            ApiFuture<DocumentSnapshot> future = firestore.collection(collection).document(mainId)
                    .collection(subCollection).document(subId)
                    .get();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("Error leyendo Firebase" + t);
                        }

                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            log.info("Documento de ID "+collection+"/"+mainId+"/"+subCollection+"/"+subId+" recuperado correctamente");
                        }
                    },
                    MoreExecutors.directExecutor()
            );

            return future.get().exists();
        } catch (Exception e) {
            throw new RuntimeException("Error al registrar en Firebase", e);
        }
    }

    public void deleteRegisterById(String id, String collection, String schoolName) {
        try {
            ApiFuture<WriteResult> future = getSchoolCollection(schoolName, collection)
                    .document(id)
                    .delete();

            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<>() {
                        @Override
                        public void onFailure(Throwable t) {
                            log.error("[Firebase] Error eliminando registro {} en {}: {}", id, schoolName, t.getMessage());
                        }

                        @Override
                        public void onSuccess(WriteResult result) {
                            log.info("[Firebase] Registro {} eliminado correctamente en {}/{}", id, schoolName, collection);
                        }
                    },
                    MoreExecutors.directExecutor()
            );
        } catch (Exception e) {
            log.error("Error al eliminar en Firebase", e);
            throw new FirebaseException("Error al eliminar en Firebase");
        }
    }

    public void deleteRegisterByCondition(String value, String variable, String collection, String schoolName) {
        try {
            CollectionReference col = getSchoolCollection(schoolName, collection);
            col.whereEqualTo(variable, value)
                    .get()
                    .get()
                    .forEach(document -> {
                        ApiFuture<WriteResult> deleteFuture = col.document(document.getId()).delete();
                        ApiFutures.addCallback(
                                deleteFuture,
                                new ApiFutureCallback<>() {
                                    @Override
                                    public void onFailure(Throwable t) {
                                        log.error("[Firebase] Error eliminando {}={} en {}: {}", variable, value, schoolName, t.getMessage());
                                    }

                                    @Override
                                    public void onSuccess(WriteResult result) {
                                        log.info("[Firebase] Registro {}={} eliminado correctamente de {}/{}", variable, value, schoolName, collection);
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

    public Map<String, Long> countLiveListRegisters(String collection, String variable, String schoolName) {
        try {
            ApiFuture<QuerySnapshot> future = getSchoolCollection(schoolName, collection).get();
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

    public void deleteRegistersByCollection(String collection, String schoolName) {
        try {
            CollectionReference firestoreCollection = getSchoolCollection(schoolName, collection);
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
            log.info("[Firebase] Se eliminaron {} registros de {}/{}", deletedRegisters, schoolName, collection);
        } catch (Exception e) {
            log.error("Error al eliminar registros por colección en Firebase", e);
            throw new FirebaseException("Error al eliminar registros de Firebase");
        }
    }
}