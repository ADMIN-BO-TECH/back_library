package co.com.botech.constants;

import co.com.botech.dto.attendance.AttendanceResponseProjection;
import co.com.botech.entity.*;
import lombok.Getter;

@Getter
public enum UserAttendanceTypeConstants {
    STUDENT("Estudiante") {
        @Override
        public void attendanceLogic(AttendanceResponseProjection response, Attendance attendance) {
            Student student = attendance.getStudent();
            response.setSchoolIdentifier(student.getStudentId().toString());
            response.setUserName(student.getFirstName() + " " + student.getLastName());
            response.setCategory(student.getGradeLevel());
        }
    },
    EMPLOYEE("Empleado") {
        @Override
        public void attendanceLogic(AttendanceResponseProjection response, Attendance attendance) {
            SchoolEmployee schoolEmployee = attendance.getSchoolEmployee();
            response.setSchoolIdentifier(schoolEmployee.getDocumentNumber());
            response.setUserName(schoolEmployee.getFirstName() + " " + schoolEmployee.getLastName());
            response.setCategory(this.getName());
        }
    },
    PARENT("Acudiente") {
        @Override
        public void attendanceLogic(AttendanceResponseProjection response, Attendance attendance) {
            Parent parent = attendance.getParent();
            response.setSchoolIdentifier(parent.getDocumentNumber());
            response.setUserName(parent.getFirstName() + " " + parent.getLastName());
            response.setCategory(this.name());
        }
    },
    AUTHORIZED_PERSON("Persona Autorizada") {
        @Override
        public void attendanceLogic(AttendanceResponseProjection response, Attendance attendance) {
            AuthorizedPerson authorizedPerson = attendance.getAuthorizedPerson();
            response.setSchoolIdentifier(authorizedPerson.getDocumentNumber());
            response.setUserName(authorizedPerson.getFirstName() + " " + authorizedPerson.getLastName());
            response.setCategory(this.getName());
        }
    };
    private final String name;

    UserAttendanceTypeConstants(String name) {
        this.name = name;
    }

    public abstract void attendanceLogic(AttendanceResponseProjection response, Attendance attendance);
}