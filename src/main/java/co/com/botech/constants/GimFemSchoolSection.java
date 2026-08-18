package co.com.botech.constants;

import lombok.Getter;
import java.util.Set;

public enum GimFemSchoolSection {

    PRESCOLAR(
            Set.of("TRANSICION", "PREMONTESSORI", "MONTESSORI"),
            "Paula Valentina Villacres Fonseca",
            "paula.vf@gimnasiofemenino.edu.co"
    ),
    PRIMARIA(
            Set.of("PRIMERO", "SEGUNDO", "TERCERO", "CUARTO"),
            "Ingrid Pedraza Ortegon",
            "ingrid.po@gimnasiofemenino.edu.co"
    ),
    MEDIA(
            Set.of("QUINTO", "SEXTO", "SEPTIMO", "OCTAVO", "NOVENO"),
            "Indira Elena Diaz Penaloza",
            "indira.dp@gimnasiofemenino.edu.co"
    ),
    ALTA(
            Set.of("DECIMO", "ONCE"),
            "Angela Valbuena Mesa",
            "angela.vm@gimnasiofemenino.edu.co"
    );

    private final Set<String> gradePrefixes;
    @Getter
    private final String directoraName;
    @Getter
    private final String email;

    GimFemSchoolSection(Set<String> gradePrefixes, String directoraName, String email) {
        this.gradePrefixes = gradePrefixes;
        this.directoraName = directoraName;
        this.email = email;
    }

    public static GimFemSchoolSection fromGradeLevel(String gradeLevel) {
        if (gradeLevel == null) return PRESCOLAR;
        String upper = gradeLevel.toUpperCase().trim();
        for (GimFemSchoolSection section : values()) {
            for (String prefix : section.gradePrefixes) {
                if (upper.startsWith(prefix)) return section;
            }
        }
        return PRESCOLAR;
    }
}