
package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

import com.shapesecurity.salvation2.Values.Hash;
import org.eclipse.jdt.core.dom.Assignment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.time.*;

class AutoAssignerTest {

//    @Test
//    void oneWorkshopOneStudentAvailable(){
//        List<Student> students = createStudents(new String[]{"Massimo"});
//        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 3);
//        List<Workshop> workshops = createWorkshops(spots);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getErrors()).isEmpty();
//        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30");
//    }
//
//    @Test
//    void oneWorkshopOneStudentUnavailable(){
//        List<Student> students = createStudents(new String[]{"Massimo"});
//        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 0);
//        List<Workshop> workshops = createWorkshops(spots);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getAssignments()).isEmpty();
//        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo");
//    }
//
//    @Test
//    void oneWorkshopManyStudentAvailable(){
//        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
//        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 3);
//        List<Workshop> workshops = createWorkshops(spots);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getErrors()).isEmpty();
//        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
//                                                                              "Workshop1,Mauro,07/12/2023 10:30");
//    }
//
//    @Test
//    void oneWorkshopManyStudentUnavailable(){
//        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
//        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 0);
//        List<Workshop> workshops = createWorkshops(spots);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getAssignments()).isEmpty();
//        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo",
//                                                                              "Workshop1,Mauro");
//    }
//
//    @Test
//    void manyWorkshopOneStudentAvailable(){
//        List<Student> students = createStudents(new String[]{"Massimo"});
//        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
//        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 3);
//        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 3);
//        List<Workshop> workshops = createWorkshops(spots1, spots2);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getErrors()).isEmpty();
//        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
//                                                                              "Workshop2,Massimo,08/12/2023 10:30");
//    }
//
//    @Test
//    void manyWorkshopOneStudentUnavailable(){
//        List<Student> students = createStudents(new String[]{"Massimo"});
//        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
//        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 0);
//        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 0);
//        List<Workshop> workshops = createWorkshops(spots1, spots2);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getAssignments()).isEmpty();
//        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo",
//                                                                              "Workshop2,Massimo");
//    }
//
//    @Test
//    void manyWorkshopManyStudentAvailable(){
//        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
//        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
//        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 3);
//        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 3);
//        List<Workshop> workshops = createWorkshops(spots1, spots2);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getErrors()).isEmpty();
//        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
//                                                                              "Workshop1,Mauro,07/12/2023 10:30",
//                                                                              "Workshop2,Massimo,08/12/2023 10:30",
//                                                                              "Workshop2,Mauro,08/12/2023 10:30");
//    }
//
//    @Test
//    void manyWorkshopManyStudentUnavailable(){
//        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
//        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
//        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
//        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 0);
//        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 0);
//        List<Workshop> workshops = createWorkshops(spots1, spots2);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getAssignments()).isEmpty();
//        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo",
//                                                                              "Workshop1,Mauro",
//                                                                              "Workshop2,Massimo",
//                                                                              "Workshop2,Mauro");
//    }
//
//    @Test
//    void manyWorkshopManyStudentSplit(){
//        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
//        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30),
//                                                     date(2023,12,8,10,30)};
//        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,9,10,30),
//                                                     date(2023,12,10,10,30)};
//        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 1);
//        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 1);
//        List<Workshop> workshops = createWorkshops(spots1, spots2);
//        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
//        assertThat(result.getErrors()).isEmpty();
//        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
//                                                                              "Workshop1,Mauro,08/12/2023 10:30",
//                                                                              "Workshop2,Massimo,09/12/2023 10:30",
//                                                                              "Workshop2,Mauro,10/12/2023 10:30");
//    }
    // helper method to create a ZonedDateTime
    private static ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }
//
//    private List<Student> createStudents(String[] names){
//        List<Student> students = new ArrayList<>();
//        int i = 1;
//        for(String name:names){
//            students.add(new Student(i, name, name.toLowerCase()+"@gmail.com"));
//            i++;
//        }
//        return students;
//    }
//
//    private Map<ZonedDateTime, Integer> createSpots(ZonedDateTime[] dates, Integer number){
//        Map<ZonedDateTime, Integer> spots = new HashMap<>();
//        for(ZonedDateTime date:dates){
//            spots.put(date, number);
//        }
//        return spots;
//    }
//
//    private List<Workshop> createWorkshops(Map<ZonedDateTime, Integer>... spots){
//        List<Workshop> workshops = new ArrayList<>();
//        int i = 1;
//        for(Map<ZonedDateTime, Integer> spot:spots){
//            workshops.add(new Workshop(1, "Workshop"+i, spot));
//            i++;
//        }
//        return workshops;
//    }
    @ParameterizedTest
    @MethodSource("assignTest")
    void assertResult(String testcase, List<Workshop> workshops, List<Student> students, List<String> assignments, List<String> errors){
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getAssignments()).containsExactlyInAnyOrder(assignments.toArray(new String[assignments.size()]));
        assertThat(result.getErrors()).containsExactlyInAnyOrder(errors.toArray(new String[errors.size()]));
    }

    private static Stream<Arguments> assignTest(){
        Workshop noSpotsOneDay = new Workshop(0, "noSpotsOneDay",
                Map.of(date(2001,01,01,10,00), 0));
        Workshop OneSpotOneDay = new Workshop(0, "OneSpotOneDay",
                Map.of(date(2001,01,01,10,00), 1));
        Workshop ManySpotsOneDay = new Workshop(0, "ManySpotsOneDay",
                Map.of(date(2001,01,01,10,00), 5));
        Workshop noSpotsManyDay = new Workshop(0, "noSpotsManyDay",
                Map.of(date(2001,01,02,10,00), 0,
                        date(2001,01,03,10,00), 0));
        Workshop OneSpotManyDay = new Workshop(0, "OneSpotManyDay",
                Map.of(date(2001,01,04,10,00), 1,
                        date(2001,01,05,10,00), 1));
        Workshop OneSpotOnlyFirstDay = new Workshop(0, "OneSpotOnlyFirstDay",
                Map.of(date(2001,01,06,10,00), 1,
                        date(2001,01,07,10,00), 0));
        Workshop ManySpotsManyDay = new Workshop(0, "ManySpotsManyDay",
                Map.of(date(2001,01,8,10,00), 5,
                        date(2001,01,9,10,00), 5));
        Workshop manySpotsOnlySecondDay = new Workshop(0, "manySpotsOnlySecondDay",
                Map.of(date(2001,01,10,10,00), 0,
                        date(2001,01,11,10,00), 5));

        Student student1 = new Student(1, "Massimo", "massimo@gmail.com");
        Student student2 = new Student(2, "Mauro", "mauro@gmail.com");

        return Stream.of(
            Arguments.of("One student, one workshop unavailable",
                    List.of(noSpotsOneDay), List.of(student1), List.of(), List.of("noSpotsOneDay,Massimo")),
            Arguments.of("One student, one workshop available",
                    List.of(OneSpotOneDay), List.of(student1), List.of("OneSpotOneDay,Massimo,01/01/2001 10:00"), List.of()),
            Arguments.of("One student many workshops available",
                    List.of(ManySpotsManyDay, OneSpotManyDay), List.of(student1),
                    List.of("ManySpotsManyDay,Massimo,08/01/2001 10:00", "OneSpotManyDay,Massimo,04/01/2001 10:00"), List.of()),
            Arguments.of("One student many workshops unavailable",
                    List.of(noSpotsOneDay, noSpotsManyDay), List.of(student1),
                    List.of(), List.of("noSpotsOneDay,Massimo", "noSpotsManyDay,Massimo")),
            Arguments.of("Many students, one workshop unavailable",
                    List.of(noSpotsOneDay), List.of(student1, student2), List.of(), List.of("noSpotsOneDay,Massimo", "noSpotsOneDay,Mauro")),
            Arguments.of("Many students, one workshop available",
                    List.of(ManySpotsOneDay), List.of(student1, student2),
                    List.of("ManySpotsOneDay,Massimo,01/01/2001 10:00", "ManySpotsOneDay,Mauro,01/01/2001 10:00"), List.of()),
            Arguments.of("Many students, many workshops unavailable",
                    List.of(noSpotsOneDay, noSpotsManyDay), List.of(student1, student2),
                    List.of(), List.of("noSpotsOneDay,Massimo", "noSpotsOneDay,Mauro", "noSpotsManyDay,Massimo", "noSpotsManyDay,Mauro")),
            Arguments.of("Many students, many workshops available",
                    List.of(ManySpotsOneDay, ManySpotsManyDay), List.of(student1, student2),
                    List.of("ManySpotsOneDay,Massimo,01/01/2001 10:00", "ManySpotsOneDay,Mauro,01/01/2001 10:00","ManySpotsManyDay,Massimo,08/01/2001 10:00", "ManySpotsManyDay,Mauro,08/01/2001 10:00"), List.of()),
            Arguments.of("Many students, many workshops available split",
                    List.of(OneSpotManyDay, ManySpotsManyDay), List.of(student1, student2),
                    List.of("OneSpotManyDay,Massimo,04/01/2001 10:00", "ManySpotsManyDay,Massimo,08/01/2001 10:00", "OneSpotManyDay,Mauro,05/01/2001 10:00", "ManySpotsManyDay,Mauro,09/01/2001 10:00"), List.of())
        );
    }
}