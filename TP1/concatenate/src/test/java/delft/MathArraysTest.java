package delft;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static delft.MathArrays.concatenate;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathArraysTest {
    @Test
    void arrayNull(){
        assertThrows(NullPointerException.class, () -> {
            concatenate(null);
        });
        assertThrows(NullPointerException.class, () -> {
            concatenate(null, null);
        });
        assertThrows(NullPointerException.class, () -> {
            concatenate(new double[]{1}, null);
        });
        assertThrows(NullPointerException.class, () -> {
            concatenate(null, new double[]{1});
        });
        assertThrows(NullPointerException.class, () -> {
            concatenate(new double[]{1}, null, new double[]{1});
        });
    }

    @Test
    void arrayEmpty(){
        assertThat(concatenate()).isEqualTo(new double[]{});
        assertThat(concatenate(new double[]{})).isEqualTo(new double[]{});
        assertThat(concatenate(new double[]{}, new double[]{})).isEqualTo(new double[]{});
        assertThat(concatenate(new double[]{}, new double[]{1})).isEqualTo(new double[]{1});
        assertThat(concatenate(new double[]{}, new double[]{1, 7, 8})).isEqualTo(new double[]{1,7,8});
        assertThat(concatenate(new double[]{1}, new double[]{})).isEqualTo(new double[]{1});
        assertThat(concatenate(new double[]{1, 7, 8}, new double[]{})).isEqualTo(new double[]{1,7,8});
        assertThat(concatenate(new double[]{1}, new double[]{}, new double[]{1})).isEqualTo(new double[]{1,1});
    }

    @Test
    void singleArray(){
        assertThat(concatenate(new double[]{1})).isEqualTo(new double[]{1});
        assertThat(concatenate(new double[]{-1})).isEqualTo(new double[]{-1});
        assertThat(concatenate(new double[]{1,7,0})).isEqualTo(new double[]{1,7,0});
        assertThat(concatenate(new double[]{1,-7,0})).isEqualTo(new double[]{1,-7,0});
    }

    @Test
    void multpileArrays(){
        assertThat(concatenate(new double[]{1}, new double[]{1})).isEqualTo(new double[]{1,1});
        assertThat(concatenate(new double[]{-1}, new double[]{1})).isEqualTo(new double[]{-1,1});
        assertThat(concatenate(new double[]{1}, new double[]{1,7,0})).isEqualTo(new double[]{1,1,7,0});
        assertThat(concatenate(new double[]{-1}, new double[]{1,-7,0})).isEqualTo(new double[]{-1,1,-7,0});
    }
}
