package co.com.botech.util.preoperation;

import co.com.botech.constants.*;
import co.com.botech.dto.preoperation.*;
import co.com.botech.entity.Preoperation;
import co.com.botech.entity.PreoperationItem;
import co.com.botech.entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Convierte un PreoperationSubmitRequest en la cabecera + items a persistir, y deriva
 * el estado operativo del vehiculo. Es la unica fuente de verdad sobre que cuenta como
 * novedad o condicion critica: seguimiento-api (analitica) y el microservicio que
 * recibe el submit dependen de esta misma logica para no desincronizarse.
 *
 * Regla: DANADO (externa) y NO_VIGENTE (documento) son criticos. Critico implica
 * novedad: nunca hay un item critico que no cuente tambien como novedad.
 *
 * estadoDeBaterias invierte la convencion de SI/NO de los demas campos internos: aqui
 * SI significa baterias en buen estado, no una novedad.
 */
public final class PreoperationEvaluator {

    private PreoperationEvaluator() {}

    public static PreoperationEvaluationResult evaluate(PreoperationSubmitRequest request, Vehicle vehicle) {

        List<PreoperationItem> items = new ArrayList<>();
        items.addAll(externalItems(request.getInspeccionExterna()));
        items.addAll(internalItems(request.getInspeccionInterna()));
        items.addAll(kitItems(request.getKitDeCarretera()));
        items.addAll(documentItems(request.getDocumentos()));

        boolean hasCritical = items.stream().anyMatch(PreoperationItem::getIsCritical);
        boolean hasIssues = items.stream().anyMatch(PreoperationItem::getIsIssue);
        int issueCount = (int) items.stream().filter(PreoperationItem::getIsIssue).count();

        Preoperation preoperation = Preoperation.builder()
                .vehicle(vehicle)
                .plateNumber(request.getPlaca())
                .fleetNumber(request.getMovil())
                .driverId(request.getDriverId())
                .operatorName(request.getOperador())
                .operatorDocument(request.getCedula())
                .mobileNumber(request.getMovil())
                .preopDate(request.getFecha())
                .preopHour(request.getHora())
                .mileage(request.getKilometraje())
                .acceptedVeracity(request.getAceptoVeracidad())
                .gpsLat(request.getGpsLocation() != null ? request.getGpsLocation().getLat() : null)
                .gpsLng(request.getGpsLocation() != null ? request.getGpsLocation().getLng() : null)
                .internalNotes(request.getInspeccionInterna().getObservaciones())
                .externalNotes(request.getInspeccionExterna().getObservaciones())
                .kitNotes(request.getKitDeCarretera().getObservaciones())
                .documentsNotes(request.getDocumentos().getObservaciones())
                .hasIssues(hasIssues)
                .hasCritical(hasCritical)
                .issueCount(issueCount)
                .build();

        items.forEach(item -> item.setPreoperation(preoperation));

        VehicleOperationalStatus status = hasCritical ? VehicleOperationalStatus.NO_OPERATIVO
                : hasIssues ? VehicleOperationalStatus.REQUIERE_ATENCION
                : VehicleOperationalStatus.OPERATIVO;

        return new PreoperationEvaluationResult(preoperation, items, status);
    }

    private static List<PreoperationItem> externalItems(ExternalInspectionInput input) {
        List<PreoperationItem> items = new ArrayList<>();
        addExternal(items, ExternalComponent.LUCES_DELANTERAS, input.getLucesDelanteras());
        addExternal(items, ExternalComponent.LLANTAS_DELANTERAS, input.getLlantasDelanteras());
        addExternal(items, ExternalComponent.PANORAMICO_DELANTERO, input.getPanoramicoDelantero());
        addExternal(items, ExternalComponent.ESPEJO_DERECHO_DELANTERO, input.getEspejoDelanteroDerecho());
        addExternal(items, ExternalComponent.ESPEJO_IZQUIERDO_DELANTERO, input.getEspejoDelanteroIzquierdo());
        addExternal(items, ExternalComponent.ESPEJO_RETROVISOR, input.getEspejoRetrovisor());
        addExternal(items, ExternalComponent.PANORAMICO_TRASERO, input.getPanoramicoTrasero());
        addExternal(items, ExternalComponent.LUCES_TRASERAS, input.getLucesTraseras());
        addExternal(items, ExternalComponent.LLANTAS_TRASERAS, input.getLlantasTraseras());
        addExternal(items, ExternalComponent.FRENOS, input.getFrenos());
        addExternal(items, ExternalComponent.PITOS, input.getPitos());
        addExternal(items, ExternalComponent.LLANTA_REPUESTO, input.getLlantaDeRepuesto());
        addExternal(items, ExternalComponent.SUSPENSION, input.getSuspension());
        addExternal(items, ExternalComponent.TESTIGOS_ENCENDIDOS, input.getTestigosEncendidos());
        return items;
    }

    private static void addExternal(List<PreoperationItem> items, ExternalComponent component, String raw) {
        ExternalCondition condition = ExternalCondition.fromInput(raw);
        boolean critical = condition == ExternalCondition.DANADO;
        boolean issue = critical || condition == ExternalCondition.MALO;
        items.add(PreoperationItem.builder()
                .category(PreoperationCategory.EXTERNA.name())
                .itemKey(component.getKey())
                .status(condition.getWireValue())
                .isIssue(issue)
                .isCritical(critical)
                .build());
    }

    // fugaRadiador SI comparte nivel con nivelAguaRefrigerante: el radiador contiene
    // el agua refrigerante, es el mismo fluido visto desde dos preguntas del formulario.
    private static List<PreoperationItem> internalItems(InternalInspectionInput input) {
        List<PreoperationItem> items = new ArrayList<>();
        addLeak(items, InternalField.FUGA_ACEITE, input.getFugaDeAceite(), input.getNivelDeAceite());
        addLevel(items, InternalField.NIVEL_ACEITE_APROPIADO, input.getNivelDeAceite());
        addLeak(items, InternalField.FUGA_LIQUIDO_FRENO, input.getFugaLiquidoFrenos(), input.getNivelLiquidoFrenos());
        addLevel(items, InternalField.NIVEL_LIQUIDO_FRENO_APROPIADO, input.getNivelLiquidoFrenos());
        addLeak(items, InternalField.FUGA_RADIADOR, input.getFugasDeRadiador(), input.getNivelAguaRefrigerante());
        addLevel(items, InternalField.NIVEL_AGUA_REFRIGERANTE_APROPIADO, input.getNivelAguaRefrigerante());
        addBattery(items, input.getEstadoDeBaterias());
        return items;
    }

    private static void addLeak(List<PreoperationItem> items, InternalField field, String rawLeak, String rawLevel) {
        YesNo leak = YesNo.valueOf(normalize(rawLeak, "fuga"));
        boolean issue = leak == YesNo.SI;
        FluidLevel level = issue && rawLevel != null ? FluidLevel.valueOf(normalize(rawLevel, "nivel")) : null;
        items.add(PreoperationItem.builder()
                .category(PreoperationCategory.INTERNA.name())
                .itemKey(field.getKey())
                .status(leak.name())
                .fluidLevel(level != null ? level.name() : null)
                .isIssue(issue)
                .isCritical(false)
                .build());
    }

    private static void addLevel(List<PreoperationItem> items, InternalField field, String rawLevel) {
        if (rawLevel == null) return;
        boolean appropriate = "NORMAL".equals(normalize(rawLevel, "nivel"));
        items.add(PreoperationItem.builder()
                .category(PreoperationCategory.INTERNA.name())
                .itemKey(field.getKey())
                .status(appropriate ? YesNo.SI.name() : YesNo.NO.name())
                .isIssue(!appropriate)
                .isCritical(false)
                .build());
    }

    private static void addBattery(List<PreoperationItem> items, String raw) {
        YesNo ok = YesNo.valueOf(normalize(raw, "estado de baterias"));
        boolean issue = ok == YesNo.NO;
        items.add(PreoperationItem.builder()
                .category(PreoperationCategory.INTERNA.name())
                .itemKey(InternalField.ESTADO_BATERIAS.getKey())
                .status(ok.name())
                .isIssue(issue)
                .isCritical(false)
                .build());
    }

    private static List<PreoperationItem> kitItems(RoadKitInput input) {
        List<PreoperationItem> items = new ArrayList<>();
        addKit(items, KitItem.EXTINTOR_VIGENCIA, input.getExtintorVigente());
        addKit(items, KitItem.GATO_HIDRAULICO, input.getGatoHidraulico());
        addKit(items, KitItem.CONOS_BANDEROLAS, input.getConosBanderolas());
        addKit(items, KitItem.KIT_HERRAMIENTAS, input.getKitDeHerramienta());
        addKit(items, KitItem.LINTERNA, input.getLinterna());
        addKit(items, KitItem.COPA, input.getCopa());
        addKit(items, KitItem.BOTIQUIN_VIGENTE, input.getBotiquinPrimerosAuxilios());
        return items;
    }

    private static void addKit(List<PreoperationItem> items, KitItem kitItem, String raw) {
        KitStatus status = KitStatus.fromInput(raw);
        boolean issue = status != KitStatus.SI;
        items.add(PreoperationItem.builder()
                .category(PreoperationCategory.KIT.name())
                .itemKey(kitItem.getKey())
                .status(status.getWireValue())
                .isIssue(issue)
                .isCritical(false)
                .build());
    }

    private static List<PreoperationItem> documentItems(DocumentsInput input) {
        List<PreoperationItem> items = new ArrayList<>();
        addDocument(items, VehicleDocument.TARJETA_OPERACION, input.getTarjetaDeOperacion());
        addDocument(items, VehicleDocument.TARJETA_PROPIEDAD, input.getTarjetaDePropiedad());
        addDocument(items, VehicleDocument.SOAT, input.getSoat());
        addDocument(items, VehicleDocument.TECNICO_MECANICA, input.getTecnomecanica());
        addDocument(items, VehicleDocument.POLIZAS_RCC_RCE, input.getPolizasRccRce());
        addDocument(items, VehicleDocument.LICENCIA_CONDUCCION, input.getLicenciaDeConduccion());
        return items;
    }

    private static void addDocument(List<PreoperationItem> items, VehicleDocument document, String raw) {
        DocumentValidity validity = DocumentValidity.fromInput(raw);
        boolean critical = validity == DocumentValidity.NO_VIGENTE;
        items.add(PreoperationItem.builder()
                .category(PreoperationCategory.DOCUMENTO.name())
                .itemKey(document.getKey())
                .status(validity.getWireValue())
                .isIssue(critical)
                .isCritical(critical)
                .build());
    }

    private static String normalize(String raw, String fieldLabel) {
        if (raw == null) throw new IllegalArgumentException("Valor de " + fieldLabel + " requerido");
        return raw.trim().toUpperCase();
    }
}
