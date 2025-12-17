package co.com.botech.constants;

public enum NotificationTypesConstants {
    SCHOOL("Por Colegio"),
    STUDENTS("Por Id's de Estudiantes"),
    GRADES("Por Cursos"),
    ATTENDANCE("Por Socket de Asistencia");
    private final String name;

    NotificationTypesConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
