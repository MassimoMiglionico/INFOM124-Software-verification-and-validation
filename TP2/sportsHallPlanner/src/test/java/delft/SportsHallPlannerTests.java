package delft;

import java.util.*;
import java.util.stream.*;
import org.assertj.core.data.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static delft.Field.*;
import static delft.Property.*;
import static delft.SportsHallPlanner.planHalls;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class SportsHallPlannerTests {
    @ParameterizedTest
    @MethodSource("planHallsArguments")
    void sportHallPlannerTest(String testcase, List<Request> requests, List<SportsHall> halls, Map<SportsHall, Request> expected){
        assertThat(planHalls(requests, halls)).isEqualTo(expected);
    }

    private static Stream<Arguments> planHallsArguments(){
        SportsHall hall1 = new SportsHall(Set.of(NEAR_CITY_CENTRE, HAS_RESTAURANT, CLOSE_PUBLIC_TRANSPORT), Map.of(BADMINTON,2));
        SportsHall hall2 = new SportsHall(Set.of(NEAR_CITY_CENTRE, HAS_RESTAURANT), Map.of(BASKETBALL,2, BADMINTON,4));
        SportsHall hall3 = new SportsHall(Set.of(NEAR_CITY_CENTRE, HAS_RESTAURANT, CLOSE_PUBLIC_TRANSPORT), Map.of(BADMINTON,10));
        SportsHall hallEmptyField = new SportsHall(Set.of(NEAR_CITY_CENTRE), Map.of());
        SportsHall hallEmptyProperty = new SportsHall(Set.of(), Map.of(TENNIS,10));

        Request requestAllProperties = new Request(Set.of(NEAR_CITY_CENTRE, HAS_RESTAURANT, CLOSE_PUBLIC_TRANSPORT), BADMINTON, 2);
        Request requestOneProperty = new Request(Set.of(NEAR_CITY_CENTRE), BADMINTON, 2);
        Request requestLotOfFields = new Request(Set.of(NEAR_CITY_CENTRE, HAS_RESTAURANT, CLOSE_PUBLIC_TRANSPORT), BADMINTON, 10);
        Request requestNoProperty = new Request(Set.of(), BADMINTON, 2);


        return Stream.of(
                Arguments.of("No request no hall", List.of(), List.of(), Map.of()),
                Arguments.of("No request one hall", List.of(), List.of(hall1), Map.of()),
                Arguments.of("No request some halls", List.of(), List.of(hall1, hall2), Map.of()),
                Arguments.of("One request no halls", List.of(requestAllProperties), List.of(), null),
                Arguments.of("One request one halls match", List.of(requestOneProperty), List.of(hall1), Map.of(hall1,requestOneProperty)),
                Arguments.of("One request one halls no match", List.of(requestAllProperties), List.of(hall2), null),
                Arguments.of("One request some halls match first", List.of(requestAllProperties), List.of(hall1, hall2), Map.of(hall1,requestAllProperties)),
                Arguments.of("One request some halls match second", List.of(requestOneProperty), List.of(hallEmptyField, hall2), Map.of(hall2,requestOneProperty)),
                Arguments.of("Many request no hall", List.of(requestOneProperty, requestAllProperties), List.of(), null),
                Arguments.of("Many request one hall match first", List.of(requestOneProperty), List.of(hall1, hall2), Map.of(hall1, requestOneProperty)),
                Arguments.of("Many request one hall match second", List.of(requestOneProperty), List.of(hallEmptyProperty, hall2), Map.of(hall2, requestOneProperty)),
                Arguments.of("Many request some hall match none", List.of(requestOneProperty, requestLotOfFields), List.of(hallEmptyProperty, hallEmptyField), null),
                Arguments.of("Many request some hall match only one", List.of(requestOneProperty, requestLotOfFields), List.of(hall1, hall2), null),
                Arguments.of("Many request some hall match all", List.of(requestAllProperties, requestNoProperty), List.of(hall1, hall3), Map.of(hall1, requestAllProperties,  hall3, requestNoProperty))

        );
    }

    @Test
    void testIllegalArgumentException(){
        SportsHall hall1 = new SportsHall(Set.of(NEAR_CITY_CENTRE, HAS_RESTAURANT, CLOSE_PUBLIC_TRANSPORT), Map.of(BADMINTON,2));
        assertThrows(IllegalArgumentException.class, () -> {
           planHalls(List.of(), List.of(hall1, hall1));
        });
    }
}