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
    @Test
    void emptyOrNull(){
        assertThat(unique(new double[]{})).isEqualTo(new double[]{});
        assertThrows(NullPointerException.class, () -> {
            assertThat(unique(null));
        });
    }
    @Test
    void singleArray(){
        assertThat(unique(new double[]{1})).isEqualTo(new double[]{1});
        assertThat(unique(new double[]{Double.NaN})).isEqualTo(new double[]{Double.NaN});
        assertThat(unique(new double[]{Double.POSITIVE_INFINITY})).isEqualTo(new double[]{Double.POSITIVE_INFINITY});
        assertThat(unique(new double[]{Double.NEGATIVE_INFINITY})).isEqualTo(new double[]{Double.NEGATIVE_INFINITY});
    }

    @Test
    void multipleArray(){
        assertThat(unique(new double[]{1,2})).isEqualTo(new double[]{2,1});
        assertThat(unique(new double[]{2,1})).isEqualTo(new double[]{2,1});
        assertThat(unique(new double[]{-1,2,1})).isEqualTo(new double[]{2,1,-1});
        assertThat(unique(new double[]{-1,2,1,-1})).isEqualTo(new double[]{2,1,-1});
        assertThat(unique(new double[]{-1,2,1,-1,2,1})).isEqualTo(new double[]{2,1,-1});
        assertThat(unique(new double[]{1,2, -1,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NaN}))
                .isEqualTo(new double[]{Double.NaN,Double.POSITIVE_INFINITY,2,1,-1,Double.NEGATIVE_INFINITY});

    }
}
