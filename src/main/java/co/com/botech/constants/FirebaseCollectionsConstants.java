package co.com.botech.constants;

public enum FirebaseCollectionsConstants {
    LISTA_EN_VIVO("ListaEnVivo"),
    LISTA_RECOGIDA("ListaRecogida"),
    REGISTRO_SALIDAS_MANUALES_PASS("RegistroSalidasManualesPass"),
    UBICACION("Ubicacion"),
    EXCEPCIONES_PARADAS_DINAMICAS("ExcepcionesParadasDinamicas"),
    BO_TECH_PASS("BO-TECH-PASS"),
    SCHEDULES("Schedules"),
    KILOMETRAJE("Kilometraje"),
    HISTORIAL("Historial"),
    HISTORIAL_UBICACION("HistorialUbicacion"),
    ROUTES("routes");

    private final String collectionName;

    FirebaseCollectionsConstants(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getName() {
        return collectionName;
    }
}
