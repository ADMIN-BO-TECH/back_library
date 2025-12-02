package co.com.botech.util.serviceUtils.attendanceUtils;

import co.com.botech.entity.Attendance;
import co.com.botech.repository.AttendanceRepository;
import co.com.botech.util.DateTimeUtils;
import co.com.botech.utilObjects.AttendanceSearchObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class AttendanceUtils {
    private final AttendanceRepository attedanceRepository;

    public AttendanceSearchObject findAttendanceByCustomQuery(List<String> values, List<String> varNames, List<Integer> limitAndOffset,
                                                              String orderVariable, Map<String, List<String>> filters) {

        AttendanceSearchObject objectReturned = new AttendanceSearchObject();

        setVarsQuery(values, objectReturned, varNames, limitAndOffset, orderVariable, filters);
        return objectReturned;
    }

    private void setVarsQuery(List<String> values, AttendanceSearchObject objectReturned,
                              List<String> varNames, List<Integer> limitAndOffset,
                              String orderVariable, Map<String, List<String>> filters) {

        String variableFilterDeclaration = setVariableFilterDeclaration(varNames);
        String query = "";
        query = setSpecificVariableFilter(values, variableFilterDeclaration, query);

        if (null != filters && !filters.isEmpty()) {
            query = query.concat(" AND ");
            query = setFilters(query, filters);
        }
        setQuerySpecifications(query, limitAndOffset, orderVariable, objectReturned);
    }

    private void setQuerySpecifications(String query, List<Integer> limitAndOffset, String orderVariable,
                                        AttendanceSearchObject objectReturned) {
        if (null != orderVariable) {
            query = query.concat(" ORDER BY " + orderVariable + " DESC ");
        }

        Integer attendanceCount = attedanceRepository.countAttendanceByCustomQuery(query);
        objectReturned.setAttendanceCount(attendanceCount);

        if (null != limitAndOffset) {
            query = setLimitAndOffset(query, limitAndOffset);
        }

        List<Attendance> attendanceList = attedanceRepository.getAttendanceByCustomQuery(query);
        objectReturned.setAttendanceList(attendanceList);
    }

    private String setVariableFilterDeclaration(List<String> varNames) {
        String filter = "LOWER(CONCAT(";
        int varCounter = 1;
        for (String var : varNames) {
            filter = filter.concat(var);
            if (varCounter < varNames.size()) {
                filter = filter.concat(",' ',");
            }
            varCounter++;
        }
        filter = filter.concat("))");
        return filter;
    }

    private String setSpecificVariableFilter(List<String> values, String filter, String query) {
        int valueCounter = 1;
        for (String value : values) {
            query = query.concat("(" + filter + " LIKE '%" + value + "%')");
            if (valueCounter < values.size()) {
                query = query.concat(" AND ");
            }
            valueCounter++;
        }
        return query;
    }


    private String setFilters(String query, Map<String, List<String>> filters) {
        int filterNumber = 0;
        int filterSize = filters.size();
        for (Map.Entry<String, List<String>> filter : filters.entrySet()) {
            int valueNumber = 0;
            int valueSize = filter.getValue().size();
            if (filter.getValue().isEmpty()) {
                query = query.concat(filter.getKey());
            } else {
                if ("attendance_time".equals(filter.getKey()) && 2 == filter.getValue().size()) {
                    String initialDate = DateTimeUtils.dateTimePreparation(filter.getValue().get(0), true).toString();
                    String finalDate = DateTimeUtils.dateTimePreparation(filter.getValue().get(1), false).toString();
                    query = query.concat(" (attendance_time BETWEEN '" + initialDate + "' AND '" + finalDate + "')");
                } else {
                    query = query.concat(" (");
                    for (String value : filter.getValue()) {
                        query = query.concat(filter.getKey() + " LIKE '%" + value + "%'");
                        valueNumber++;
                        if (valueNumber < valueSize) {
                            query = query.concat(" OR ");
                        }
                    }
                    query = query.concat(")");
                }
            }
            filterNumber++;
            if (filterNumber < filterSize) {
                query = query.concat(" AND ");
            }
        }
        return query;
    }

    private String setLimitAndOffset(String query, List<Integer> limitAndOffset) {
        query = query.concat(" LIMIT " + limitAndOffset.get(0) + " OFFSET " + limitAndOffset.get(1));
        return query;
    }

}
