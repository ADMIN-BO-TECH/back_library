package co.com.botech.util.preoperation;

import co.com.botech.constants.VehicleOperationalStatus;
import co.com.botech.dto.preoperation.*;
import co.com.botech.entity.Preoperation;
import co.com.botech.entity.PreoperationItem;
import co.com.botech.entity.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PreoperationEvaluator")
class PreoperationEvaluatorTest {

    private Vehicle vehicle() {
        return Vehicle.builder().id(5L).plateNumber("MNP-654").fleetNumber("005").build();
    }

    private PreoperationSubmitRequest.PreoperationSubmitRequestBuilder cleanRequest() {
        return PreoperationSubmitRequest.builder()
                .driverId(17L).idVehicle(5L).fecha(LocalDate.of(2026, 8, 11)).hora(LocalTime.of(6, 12))
                .operador("Carlos Mendoza").cedula("1020345678").colegio("Colegio El Jardín")
                .movil("005").placa("MNP-654").kilometraje(187430).aceptoVeracidad(true)
                .inspeccionInterna(InternalInspectionInput.builder()
                        .fugaDeAceite("NO").nivelDeAceite("NORMAL")
                        .fugaLiquidoFrenos("NO").nivelLiquidoFrenos("NORMAL")
                        .fugasDeRadiador("NO").nivelAguaRefrigerante("NORMAL")
                        .estadoDeBaterias("SI").build())
                .inspeccionExterna(ExternalInspectionInput.builder()
                        .lucesDelanteras("BUENO").llantasDelanteras("BUENO").panoramicoDelantero("BUENO")
                        .espejoDelanteroDerecho("BUENO").espejoDelanteroIzquierdo("BUENO")
                        .espejoRetrovisor("BUENO").panoramicoTrasero("BUENO").lucesTraseras("BUENO")
                        .llantasTraseras("BUENO").frenos("BUENO").pitos("BUENO")
                        .llantaDeRepuesto("BUENO").suspension("BUENO").testigosEncendidos("BUENO").build())
                .kitDeCarretera(RoadKitInput.builder()
                        .extintorVigente("SI").gatoHidraulico("SI").conosBanderolas("SI")
                        .kitDeHerramienta("SI").linterna("SI").copa("SI").botiquinPrimerosAuxilios("SI").build())
                .documentos(DocumentsInput.builder()
                        .tarjetaDeOperacion("VIGENTE").tarjetaDePropiedad("VIGENTE").soat("VIGENTE")
                        .tecnomecanica("VIGENTE").polizasRccRce("VIGENTE").licenciaDeConduccion("VIGENTE").build());
    }

    @Nested
    @DisplayName("Estado del vehiculo")
    class VehicleStatus {

        @Test
        void Given_a_clean_preop_When_evaluate_Then_status_is_OPERATIVO() {
            var result = PreoperationEvaluator.evaluate(cleanRequest().build(), vehicle());

            assertThat(result.vehicleStatus()).isEqualTo(VehicleOperationalStatus.OPERATIVO);
            assertThat(result.preoperation().getHasIssues()).isFalse();
            assertThat(result.preoperation().getHasCritical()).isFalse();
        }

        @Test
        void Given_a_MALO_external_field_When_evaluate_Then_status_is_REQUIERE_ATENCION() {
            var request = cleanRequest()
                    .inspeccionExterna(cleanRequest().build().getInspeccionExterna().toBuilder()
                            .llantasTraseras("MALO").build())
                    .build();

            var result = PreoperationEvaluator.evaluate(request, vehicle());

            assertThat(result.vehicleStatus()).isEqualTo(VehicleOperationalStatus.REQUIERE_ATENCION);
        }

        @Test
        void Given_a_DANADO_external_field_When_evaluate_Then_status_is_NO_OPERATIVO_and_also_counts_as_issue() {
            var request = cleanRequest()
                    .inspeccionExterna(cleanRequest().build().getInspeccionExterna().toBuilder()
                            .llantasTraseras("DAÑADO").build())
                    .build();

            var result = PreoperationEvaluator.evaluate(request, vehicle());

            assertThat(result.vehicleStatus()).isEqualTo(VehicleOperationalStatus.NO_OPERATIVO);
            assertThat(result.preoperation().getHasCritical()).isTrue();
            assertThat(result.preoperation().getHasIssues()).isTrue();
        }

        @Test
        void Given_an_expired_document_When_evaluate_Then_status_is_NO_OPERATIVO() {
            var request = cleanRequest()
                    .documentos(cleanRequest().build().getDocumentos().toBuilder()
                            .soat("NO VIGENTE").build())
                    .build();

            var result = PreoperationEvaluator.evaluate(request, vehicle());

            assertThat(result.vehicleStatus()).isEqualTo(VehicleOperationalStatus.NO_OPERATIVO);
        }
    }

    @Nested
    @DisplayName("estadoDeBaterias invierte la convencion SI/NO")
    class Battery {

        @Test
        void Given_estadoDeBaterias_SI_When_evaluate_Then_it_is_not_an_issue() {
            var result = PreoperationEvaluator.evaluate(cleanRequest().build(), vehicle());

            PreoperationItem battery = result.items().stream()
                    .filter(i -> i.getItemKey().equals("estado_baterias")).findFirst().orElseThrow();
            assertThat(battery.getIsIssue()).isFalse();
        }

        @Test
        void Given_estadoDeBaterias_NO_When_evaluate_Then_it_is_an_issue() {
            var request = cleanRequest()
                    .inspeccionInterna(cleanRequest().build().getInspeccionInterna().toBuilder()
                            .estadoDeBaterias("NO").build())
                    .build();

            var result = PreoperationEvaluator.evaluate(request, vehicle());

            PreoperationItem battery = result.items().stream()
                    .filter(i -> i.getItemKey().equals("estado_baterias")).findFirst().orElseThrow();
            assertThat(battery.getIsIssue()).isTrue();
        }
    }

    @Test
    void Given_a_leak_with_a_level_When_evaluate_Then_the_leak_item_carries_the_fluid_level() {
        var request = cleanRequest()
                .inspeccionInterna(cleanRequest().build().getInspeccionInterna().toBuilder()
                        .fugaDeAceite("SI").nivelDeAceite("BAJO").build())
                .build();

        var result = PreoperationEvaluator.evaluate(request, vehicle());

        PreoperationItem fuga = result.items().stream()
                .filter(i -> i.getItemKey().equals("fuga_aceite")).findFirst().orElseThrow();
        assertThat(fuga.getIsIssue()).isTrue();
        assertThat(fuga.getFluidLevel()).isEqualTo("BAJO");

        PreoperationItem nivel = result.items().stream()
                .filter(i -> i.getItemKey().equals("nivel_aceite_apropiado")).findFirst().orElseThrow();
        assertThat(nivel.getIsIssue()).isTrue();
    }

    @Test
    void Given_fugaRadiador_SI_When_evaluate_Then_it_carries_the_level_from_nivelAguaRefrigerante() {
        var request = cleanRequest()
                .inspeccionInterna(cleanRequest().build().getInspeccionInterna().toBuilder()
                        .fugasDeRadiador("SI").nivelAguaRefrigerante("BAJO").build())
                .build();

        var result = PreoperationEvaluator.evaluate(request, vehicle());

        PreoperationItem fuga = result.items().stream()
                .filter(i -> i.getItemKey().equals("fuga_radiador")).findFirst().orElseThrow();
        assertThat(fuga.getFluidLevel()).isEqualTo("BAJO");
    }

    @Test
    void Given_a_kit_item_with_novedad_When_evaluate_Then_it_is_an_issue_but_not_critical() {
        var request = cleanRequest()
                .kitDeCarretera(cleanRequest().build().getKitDeCarretera().toBuilder()
                        .conosBanderolas("NOVEDAD").build())
                .build();

        var result = PreoperationEvaluator.evaluate(request, vehicle());

        PreoperationItem conos = result.items().stream()
                .filter(i -> i.getItemKey().equals("conos_banderolas")).findFirst().orElseThrow();
        assertThat(conos.getIsIssue()).isTrue();
        assertThat(conos.getIsCritical()).isFalse();
    }

    @Test
    void Given_a_valid_request_When_evaluate_Then_it_produces_exactly_34_items() {
        // 14 externos + 7 internos (6 del contrato + estado_baterias) + 7 de kit + 6 documentos = 34
        var result = PreoperationEvaluator.evaluate(cleanRequest().build(), vehicle());

        assertThat(result.items()).hasSize(34);
    }

    @Test
    void Given_a_request_When_evaluate_Then_each_item_is_linked_back_to_the_preoperation() {
        var result = PreoperationEvaluator.evaluate(cleanRequest().build(), vehicle());

        assertThat(result.items()).allMatch(i -> i.getPreoperation() == result.preoperation());
    }

    @Test
    void Given_plate_and_fleet_When_evaluate_Then_they_are_frozen_onto_the_header() {
        Preoperation preop = PreoperationEvaluator.evaluate(cleanRequest().build(), vehicle())
                .preoperation();

        assertThat(preop.getPlateNumber()).isEqualTo("MNP-654");
        assertThat(preop.getFleetNumber()).isEqualTo("005");
        assertThat(preop.getOperatorName()).isEqualTo("Carlos Mendoza");
    }
}
