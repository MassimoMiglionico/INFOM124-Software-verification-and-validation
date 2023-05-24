package delft;

import static delft.MathArrays.unique;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class MathArraysTest {
//    @Test
//    void emptyOrNull(){
//        assertThat(unique(new double[]{})).isEqualTo(new double[]{});
//        assertThrows(NullPointerException.class, () -> {
//            assertThat(unique(null));
//        });
//    }
//    @Test
//    void singleArray(){
//        assertThat(unique(new double[]{1})).isEqualTo(new double[]{1});
//        assertThat(unique(new double[]{Double.NaN})).isEqualTo(new double[]{Double.NaN});
//        assertThat(unique(new double[]{Double.POSITIVE_INFINITY})).isEqualTo(new double[]{Double.POSITIVE_INFINITY});
//        assertThat(unique(new double[]{Double.NEGATIVE_INFINITY})).isEqualTo(new double[]{Double.NEGATIVE_INFINITY});
//    }
//
//    @Test
//    void multipleArray(){
//        assertThat(unique(new double[]{1,2})).isEqualTo(new double[]{2,1});
//        assertThat(unique(new double[]{2,1})).isEqualTo(new double[]{2,1});
//        assertThat(unique(new double[]{-1,2,1})).isEqualTo(new double[]{2,1,-1});
//        assertThat(unique(new double[]{-1,2,1,-1})).isEqualTo(new double[]{2,1,-1});
//        assertThat(unique(new double[]{-1,2,1,-1,2,1})).isEqualTo(new double[]{2,1,-1});
//        assertThat(unique(new double[]{1,2, -1,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NaN}))
//                .isEqualTo(new double[]{Double.NaN,Double.POSITIVE_INFINITY,2,1,-1,Double.NEGATIVE_INFINITY});
//
//    }

    @ParameterizedTest
    @MethodSource("uniqueTest")
    void uniqueAssert(String testcase, double[] array, double[] expected){
        assertThat(unique(array)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("uniqueException")
    void uniqueAssert(String testcase, double[] array){
        assertThrows(NullPointerException.class, () -> {
            unique(array);
        });
    }

    private static Stream<Arguments> uniqueTest(){
        return Stream.of(
            Arguments.of("Empty array", doubles(), doubles()),
            Arguments.of("One value array", doubles(1), doubles(1)),
            Arguments.of("Array in wrong order", doubles(1,2,4), doubles(4,2,1)),
            Arguments.of("Array with duplicates", doubles(-1,2,4,4,1), doubles(4,2,1,-1)),
            Arguments.of("Array with NaN and infinites",
                    doubles(1,-1,Double.NaN, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY),
                    doubles(Double.NaN, Double.POSITIVE_INFINITY,4,1, -1, Double.NEGATIVE_INFINITY))
        );
    }

    private static Stream<Arguments> uniqueException(){
        return Stream.of(
            Arguments.of("Null array", null)
        );
    }

    private static double[] doubles(double... s) { return s; }
}
