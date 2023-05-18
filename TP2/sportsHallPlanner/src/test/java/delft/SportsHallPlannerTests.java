package delft;

import java.util.*;
import java.util.stream.*;
import org.assertj.core.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static delft.Field.*;
import static delft.Property.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SportsHallPlannerTests {
    @Test
    void noRequestNoHall(){
        assertThat(SportsHallPlanner.planHalls(new ArrayList<>(), new ArrayList<>())).isEqualTo(new HashMap<>());
    }

    @Test
    void noRequestOneHall(){
        Set<Property> properties = createProperties(CLOSE_PUBLIC_TRANSPORT);
        HashMap<Field, Integer> fields= new HashMap<>();
        fields.put(BADMINTON,1);
        SportsHall hall = new SportsHall(properties, fields);
        List<SportsHall> halls = createHalls(hall);
        assertThat(SportsHallPlanner.planHalls(new ArrayList<>(), halls)).isEqualTo(new HashMap<>());
    }

    @Test
    void oneRequestNoHall(){
        Set<Property> properties = createProperties(CLOSE_PUBLIC_TRANSPORT);
        Field field = BASKETBALL;
        int numberOfFields = 1;
        Request request = new Request(properties, field, numberOfFields);
        List<Request> requests = createRequest(request);
        assertThat(SportsHallPlanner.planHalls(requests, new ArrayList<>())).isNull();
    }

    @Test
    void oneRequestOneHallSuccess(){
        Set<Property> requestProperties = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        Field field = BASKETBALL;
        int numberOfFields = 1;
        Request request = new Request(requestProperties, field, numberOfFields);
        List<Request> requests = createRequest(request);

        Set<Property> hallProperties = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        HashMap<Field, Integer> fields= new HashMap<>();
        fields.put(BASKETBALL,1);
        SportsHall hall = new SportsHall(hallProperties, fields);
        List<SportsHall> halls = createHalls(hall);
        HashMap<SportsHall,Request> expected = new HashMap<>();
        expected.put(hall,request);
        assertThat(SportsHallPlanner.planHalls(requests, halls)).isEqualTo(expected);
    }

    @Test
    void oneRequestOneHallFail(){
        Set<Property> requestProperties = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        Field field = BASKETBALL;
        int numberOfFields = 1;
        Request request = new Request(requestProperties, field, numberOfFields);
        List<Request> requests = createRequest(request);

        Set<Property> hallProperties = createProperties(CLOSE_PUBLIC_TRANSPORT);
        HashMap<Field, Integer> fields= new HashMap<>();
        fields.put(BASKETBALL,1);
        SportsHall hall = new SportsHall(hallProperties, fields);
        List<SportsHall> halls = createHalls(hall);
        assertThat(SportsHallPlanner.planHalls(requests, halls)).isNull();
    }

    @Test
    void oneRequestManyHallSuccess(){
        Set<Property> requestProperties = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        Field field = BASKETBALL;
        int numberOfFields = 1;
        Request request = new Request(requestProperties, field, numberOfFields);
        List<Request> requests = createRequest(request);

        Set<Property> hallProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        HashMap<Field, Integer> fields1 = new HashMap<>();
        fields1.put(BASKETBALL,1);
        SportsHall hall1 = new SportsHall(hallProperties1, fields1);
        Set<Property> hallProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, NEAR_CITY_CENTRE);
        HashMap<Field, Integer> fields2 = new HashMap<>();
        fields2.put(BASKETBALL,3);
        SportsHall hall2 = new SportsHall(hallProperties2, fields2);
        List<SportsHall> halls = createHalls(hall1, hall2);
        HashMap<SportsHall,Request> expected = new HashMap<>();
        expected.put(hall1,request);
        assertThat(SportsHallPlanner.planHalls(requests, halls)).isEqualTo(expected);
    }
    @Test
    void oneRequestManyHallFail(){
        Set<Property> requestProperties = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        Field field = BASKETBALL;
        int numberOfFields = 1;
        Request request = new Request(requestProperties, field, numberOfFields);
        List<Request> requests = createRequest(request);

        Set<Property> hallProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT);
        HashMap<Field, Integer> fields1 = new HashMap<>();
        fields1.put(BASKETBALL,1);
        SportsHall hall1 = new SportsHall(hallProperties1, fields1);
        Set<Property> hallProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        HashMap<Field, Integer> fields2 = new HashMap<>();
        fields2.put(BADMINTON,1);
        SportsHall hall2 = new SportsHall(hallProperties2, fields2);
        List<SportsHall> halls = createHalls(hall1, hall2);
        assertThat(SportsHallPlanner.planHalls(requests, halls)).isNull();
    }

    @Test
    void manyRequestManyHallSuccess(){
        Set<Property> requestProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        Field field1 = BASKETBALL;
        int numberOfFields1 = 3;
        Request request1 = new Request(requestProperties1, field1, numberOfFields1);

        Set<Property> requestProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, NEAR_CITY_CENTRE);
        Field field2 = BADMINTON;
        int numberOfFields2 = 1;
        Request request2 = new Request(requestProperties2, field2, numberOfFields2);

        List<Request> requests = createRequest(request1, request2);

        Set<Property> hallProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT, NEAR_CITY_CENTRE);
        HashMap<Field, Integer> fields1 = new HashMap<>();
        fields1.put(BADMINTON,1);
        SportsHall hall1 = new SportsHall(hallProperties1, fields1);
        
        Set<Property> hallProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        HashMap<Field, Integer> fields2 = new HashMap<>();
        fields2.put(BASKETBALL,3);
        
        SportsHall hall2 = new SportsHall(hallProperties2, fields2);
        List<SportsHall> halls = createHalls(hall1, hall2);
        HashMap<SportsHall,Request> expected = new HashMap<>();
        expected.put(hall2,request1);
        expected.put(hall1,request2);
        assertThat(SportsHallPlanner.planHalls(requests, halls)).isEqualTo(expected);
    }

    @Test
    void manyRequestManyHallFail(){
        Set<Property> requestProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        Field field1 = BASKETBALL;
        int numberOfFields1 = 3;
        Request request1 = new Request(requestProperties1, field1, numberOfFields1);

        Set<Property> requestProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, NEAR_CITY_CENTRE);
        Field field2 = BADMINTON;
        int numberOfFields2 = 2;
        Request request2 = new Request(requestProperties2, field2, numberOfFields2);

        List<Request> requests = createRequest(request1, request2);

        Set<Property> hallProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT, NEAR_CITY_CENTRE);
        HashMap<Field, Integer> fields1 = new HashMap<>();
        fields1.put(BADMINTON,2);
        SportsHall hall1 = new SportsHall(hallProperties1, fields1);

        Set<Property> hallProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        HashMap<Field, Integer> fields2 = new HashMap<>();
        fields2.put(BASKETBALL,2);

        SportsHall hall2 = new SportsHall(hallProperties2, fields2);
        List<SportsHall> halls = createHalls(hall1, hall2);
        HashMap<SportsHall,Request> expected = new HashMap<>();
        expected.put(hall2,request1);
        expected.put(hall1,request2);
        assertThat(SportsHallPlanner.planHalls(requests, halls)).isNull();
    }

    @Test
    void duplicateHall(){
        Set<Property> requestProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        Field field1 = BASKETBALL;
        int numberOfFields1 = 3;
        Request request1 = new Request(requestProperties1, field1, numberOfFields1);

        Set<Property> requestProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, NEAR_CITY_CENTRE);
        Field field2 = BADMINTON;
        int numberOfFields2 = 2;
        Request request2 = new Request(requestProperties2, field2, numberOfFields2);

        List<Request> requests = createRequest(request1, request2);

        Set<Property> hallProperties1 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        HashMap<Field, Integer> fields1 = new HashMap<>();
        fields1.put(BASKETBALL,3);
        SportsHall hall1 = new SportsHall(hallProperties1, fields1);

        Set<Property> hallProperties2 = createProperties(CLOSE_PUBLIC_TRANSPORT, HAS_RESTAURANT);
        HashMap<Field, Integer> fields2 = new HashMap<>();
        fields2.put(BASKETBALL,3);

        SportsHall hall2 = new SportsHall(hallProperties2, fields2);
        List<SportsHall> halls = createHalls(hall1, hall2);
        HashMap<SportsHall,Request> expected = new HashMap<>();
        expected.put(hall2,request1);
        expected.put(hall1,request2);
        assertThrows(IllegalArgumentException.class, () -> {
            SportsHallPlanner.planHalls(requests, halls);
        });
    }

    Set<Property> createProperties(Property... propertyList){
        Set<Property> properties = new HashSet<>();
        for(Property property:propertyList){
            properties.add(property);
        }
        return properties;
    }

    List<SportsHall> createHalls(SportsHall... hallList){
        List<SportsHall> halls = new ArrayList<>();
        for(SportsHall hall:hallList){
            halls.add(hall);
        }
        return halls;
    }

    List<Request> createRequest(Request... requestList){
        List<Request> requests = new ArrayList<>();
        for(Request request:requestList){
            requests.add(request);
        }
        return requests;
    }
}
