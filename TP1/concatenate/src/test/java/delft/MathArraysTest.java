package delft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static delft.MathArrays.concatenate;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathArraysTest {
//    @Test
//    void arrayNull(){
//        assertThrows(NullPointerException.class, () -> {
//            concatenate(null);
//        });
//        assertThrows(NullPointerException.class, () -> {
//            concatenate(null, null);
//        });
//        assertThrows(NullPointerException.class, () -> {
//            concatenate(new double[]{1}, null);
//        });
//        assertThrows(NullPointerException.class, () -> {
//            concatenate(null, new double[]{1});
//        });
//        assertThrows(NullPointerException.class, () -> {
//            concatenate(new double[]{1}, null, new double[]{1});
//        });
//    }
//
//    @Test
//    void arrayEmpty(){
//        assertThat(concatenate()).isEqualTo(new double[]{});
//        assertThat(concatenate(new double[]{})).isEqualTo(new double[]{});
//        assertThat(concatenate(new double[]{}, new double[]{})).isEqualTo(new double[]{});
//        assertThat(concatenate(new double[]{}, new double[]{1})).isEqualTo(new double[]{1});
//        assertThat(concatenate(new double[]{}, new double[]{1, 7, 8})).isEqualTo(new double[]{1,7,8});
//        assertThat(concatenate(new double[]{1}, new double[]{})).isEqualTo(new double[]{1});
//        assertThat(concatenate(new double[]{1, 7, 8}, new double[]{})).isEqualTo(new double[]{1,7,8});
//        assertThat(concatenate(new double[]{1}, new double[]{}, new double[]{1})).isEqualTo(new double[]{1,1});
//    }
//
//    @Test
//    void singleArray(){
//        assertThat(concatenate(new double[]{1})).isEqualTo(new double[]{1});
//        assertThat(concatenate(new double[]{-1})).isEqualTo(new double[]{-1});
//        assertThat(concatenate(new double[]{1,7,0})).isEqualTo(new double[]{1,7,0});
//        assertThat(concatenate(new double[]{1,-7,0})).isEqualTo(new double[]{1,-7,0});
//    }
//
//    @Test
//    void multpileArrays(){
//        assertThat(concatenate(new double[]{1}, new double[]{1})).isEqualTo(new double[]{1,1});
//        assertThat(concatenate(new double[]{-1}, new double[]{1})).isEqualTo(new double[]{-1,1});
//        assertThat(concatenate(new double[]{1}, new double[]{1,7,0})).isEqualTo(new double[]{1,1,7,0});
//        assertThat(concatenate(new double[]{-1}, new double[]{1,-7,0})).isEqualTo(new double[]{-1,1,-7,0});
//    }

    @ParameterizedTest
    @MethodSource("concatTest")
    void concatAssert(String testcase, double[][] arrays, double[] expected){
        assertThat(concatenate(arrays)).isEqualTo(expected);
    }

    private static Stream<Arguments> concatTest(){
        return Stream.of(
                Arguments.of("No list", doublesOf(), doubles()),
                Arguments.of("One empty", doublesOf(doubles()), doubles()),
                Arguments.of("Many empty", doublesOf(doubles(), doubles(), doubles()), doubles()),
                Arguments.of("One array one value", doublesOf(doubles(1)), doubles(1)),
                Arguments.of("One array many values", doublesOf(doubles(1,2,4)), doubles(1,2,4)),
                Arguments.of("Many arrays one value", doublesOf(doubles(2), doubles(3), doubles(4)), doubles(2,3,4)),
                Arguments.of("Many arrays many values", doublesOf(doubles(2,6), doubles(2,9), doubles(8,10)), doubles(2,6,2,9,8,10)),
                Arguments.of("Many arrays mix no or many values", doublesOf(doubles(2), doubles(), doubles(2,9)), doubles(2,2,9))

        );
    }

    @ParameterizedTest
    @MethodSource("concatException")
    void concatException(String testcase, double[][] arrays){
        assertThrows(NullPointerException.class, () -> {
            concatenate(arrays);
        });
    }

    private static Stream<Arguments> concatException(){
        return Stream.of(
                Arguments.of("Null array", null),
                Arguments.of("One null value", doublesOf(null)),
                Arguments.of("Many null value", doublesOf(doubles(null), doubles(null)))
        );
    }


    private static double[] doubles(double... ds)  { return ds; }

    private static double[][] doublesOf(double[]... ds)  { return ds; }

}
