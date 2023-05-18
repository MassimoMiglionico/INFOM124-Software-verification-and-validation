package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;


class ZigZagTest {
    @Test
    void emptyString(){
        assertThrows(NullPointerException.class, () -> {
           new ZigZag().zigzag(null, 1);
        });
    }


    @Test
    void illegalArgument(){
        assertThrows(IllegalArgumentException.class, () -> {
            new ZigZag().zigzag("PAYPALISHIRING", 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ZigZag().zigzag("PAYPALISHIRING", 1001);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new ZigZag().zigzag("", 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            StringBuilder word = new StringBuilder("");
            for(int i=0; i<1001; i++){
                word.append("e");
            }
            new ZigZag().zigzag(word.toString(),1);
        });
    }

    @Test
    void boundaryValidArgument(){
        StringBuilder word = new StringBuilder("");
        for(int i=0; i<1000; i++){
            word.append("e");
        }
        assertThat(new ZigZag().zigzag(word.toString(),1)).isEqualTo(word.toString());
    }

    @Test
    void oneRow(){
        assertThat(new ZigZag().zigzag("PAYPALISHIRING", 1)).isEqualTo("PAYPALISHIRING");
        assertThat(new ZigZag().zigzag("P", 1)).isEqualTo("P");
    }

    @Test
    void twoRow(){
        assertThat(new ZigZag().zigzag("PAYPALISHIRING", 2)).isEqualTo("PYAIHRN\n" +
                                                                                           "APLSIIG");
        assertThat(new ZigZag().zigzag("P", 2)).isEqualTo("P");
    }

    @Test
    void threeRow(){
        assertThat(new ZigZag().zigzag("PAYPALISHIRING", 3)).isEqualTo("P A H N\n" +
                                                                                           "APLSIIG\n" +
                                                                                           "Y I R");
        assertThat(new ZigZag().zigzag("P", 3)).isEqualTo("P");
    }

    @Test
    void fourRow(){
        assertThat(new ZigZag().zigzag("PAYPALISHIRING", 4)).isEqualTo("P  I  N\n" +
                                                                                           "A LS IG\n" +
                                                                                           "YA HR\n" +
                                                                                           "P  I");
        assertThat(new ZigZag().zigzag("P", 4)).isEqualTo("P");
    }
    @Test
    void fiveRow(){
        assertThat(new ZigZag().zigzag("PAYPALISHIRING", 5)).isEqualTo("P   H\n" +
                                                                                           "A  SI\n" +
                                                                                           "Y I R\n" +
                                                                                           "PL  IG\n" +
                                                                                           "A   N");
        assertThat(new ZigZag().zigzag("P", 5)).isEqualTo("P");
    }
}
