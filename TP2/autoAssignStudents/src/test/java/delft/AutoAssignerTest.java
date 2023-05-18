
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

    @Test
    void oneWorkshopOneStudentAvailable(){
        List<Student> students = createStudents(new String[]{"Massimo"});
        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 3);
        List<Workshop> workshops = createWorkshops(spots);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30");
    }

    @Test
    void oneWorkshopOneStudentUnavailable(){
        List<Student> students = createStudents(new String[]{"Massimo"});
        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 0);
        List<Workshop> workshops = createWorkshops(spots);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getAssignments()).isEmpty();
        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo");
    }

    @Test
    void oneWorkshopManyStudentAvailable(){
        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 3);
        List<Workshop> workshops = createWorkshops(spots);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
                                                                              "Workshop1,Mauro,07/12/2023 10:30");
    }

    @Test
    void oneWorkshopManyStudentUnavailable(){
        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
        ZonedDateTime[] spots1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        Map<ZonedDateTime, Integer> spots = createSpots(spots1, 0);
        List<Workshop> workshops = createWorkshops(spots);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getAssignments()).isEmpty();
        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo",
                                                                              "Workshop1,Mauro");
    }

    @Test
    void manyWorkshopOneStudentAvailable(){
        List<Student> students = createStudents(new String[]{"Massimo"});
        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 3);
        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 3);
        List<Workshop> workshops = createWorkshops(spots1, spots2);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
                                                                              "Workshop2,Massimo,08/12/2023 10:30");
    }

    @Test
    void manyWorkshopOneStudentUnavailable(){
        List<Student> students = createStudents(new String[]{"Massimo"});
        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 0);
        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 0);
        List<Workshop> workshops = createWorkshops(spots1, spots2);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getAssignments()).isEmpty();
        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo",
                                                                              "Workshop2,Massimo");
    }

    @Test
    void manyWorkshopManyStudentAvailable(){
        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 3);
        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 3);
        List<Workshop> workshops = createWorkshops(spots1, spots2);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
                                                                              "Workshop1,Mauro,07/12/2023 10:30",
                                                                              "Workshop2,Massimo,08/12/2023 10:30",
                                                                              "Workshop2,Mauro,08/12/2023 10:30");
    }

    @Test
    void manyWorkshopManyStudentUnavailable(){
        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30)};
        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,8,10,30)};
        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 0);
        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 0);
        List<Workshop> workshops = createWorkshops(spots1, spots2);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getAssignments()).isEmpty();
        assertThat(result.getErrors()).containsExactlyInAnyOrder("Workshop1,Massimo",
                                                                              "Workshop1,Mauro",
                                                                              "Workshop2,Massimo",
                                                                              "Workshop2,Mauro");
    }

    @Test
    void manyWorkshopManyStudentSplit(){
        List<Student> students = createStudents(new String[]{"Massimo", "Mauro"});
        ZonedDateTime[] dates1 = new ZonedDateTime[]{date(2023,12,7,10,30),
                                                     date(2023,12,8,10,30)};
        ZonedDateTime[] dates2 = new ZonedDateTime[]{date(2023,12,9,10,30),
                                                     date(2023,12,10,10,30)};
        Map<ZonedDateTime, Integer> spots1 = createSpots(dates1, 1);
        Map<ZonedDateTime, Integer> spots2 = createSpots(dates2, 1);
        List<Workshop> workshops = createWorkshops(spots1, spots2);
        AssignmentsLogger result = new AutoAssigner().assign(students, workshops);
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getAssignments()).containsExactlyInAnyOrder("Workshop1,Massimo,07/12/2023 10:30",
                                                                              "Workshop1,Mauro,08/12/2023 10:30",
                                                                              "Workshop2,Massimo,09/12/2023 10:30",
                                                                              "Workshop2,Mauro,10/12/2023 10:30");
    }
    // helper method to create a ZonedDateTime
    private ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }

    private List<Student> createStudents(String[] names){
        List<Student> students = new ArrayList<>();
        int i = 1;
        for(String name:names){
            students.add(new Student(i, name, name.toLowerCase()+"@gmail.com"));
            i++;
        }
        return students;
    }

    private Map<ZonedDateTime, Integer> createSpots(ZonedDateTime[] dates, Integer number){
        Map<ZonedDateTime, Integer> spots = new HashMap<>();
        for(ZonedDateTime date:dates){
            spots.put(date, number);
        }
        return spots;
    }

    private List<Workshop> createWorkshops(Map<ZonedDateTime, Integer>... spots){
        List<Workshop> workshops = new ArrayList<>();
        int i = 1;
        for(Map<ZonedDateTime, Integer> spot:spots){
            workshops.add(new Workshop(1, "Workshop"+i, spot));
            i++;
        }
        return workshops;
    }
}