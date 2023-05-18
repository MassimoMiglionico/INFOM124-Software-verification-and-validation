package delft;

import static delft.DelftStringUtilities.substringsBetween;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;


class DelftStringUtilitiesTest {
    @Test
    void strSingle(){
        assertThat(substringsBetween("a", "a", "a")).isNull();
        assertThat(substringsBetween("a", "b", "b")).isNull();
        assertThat(substringsBetween("a", "a", "b")).isNull();
        assertThat(substringsBetween("a", "b", "a")).isNull();
    }

    @Test
    void strMultiple(){
        assertThat(substringsBetween("aa", "a", "a")).isEqualTo(new String[]{""});
        assertThat(substringsBetween("aaa", "a", "a")).isEqualTo(new String[]{""});
        assertThat(substringsBetween("aaaa", "a", "a")).isEqualTo(new String[]{"", ""});
        assertThat(substringsBetween("ab", "a", "b")).isEqualTo(new String[]{""});
        assertThat(substringsBetween("abc", "a", "c")).isEqualTo(new String[]{"b"});
        assertThat(substringsBetween("abbc", "a", "c")).isEqualTo(new String[]{"bb"});
    }

    @Test
    void strEmptyOrNull(){
        assertThat(substringsBetween(null, "T", "n")).isNull();
        assertThat(substringsBetween("", "T", "n")).isEqualTo(new String[]{});
    }

    @Test
    void strNoMatch(){
        assertThat(substringsBetween("{This test is OK}", "A", "B")).isNull();
    }

    @Test
    void invertOpenAndClose(){
        assertThat(substringsBetween("{This test is OK}", "}", "{")).isNull();
    }

    @Test
    void openNEmptyOrNull(){
        assertThat(substringsBetween("{This test is OK}", null, "}")).isNull();
        assertThat(substringsBetween("{This test is OK}", "", "}")).isNull();
    }

    @Test
    void closeNEmptyOrNull(){
        assertThat(substringsBetween("{This test is OK}", "{", null)).isNull();
        assertThat(substringsBetween("{This test is OK}", "{", "")).isNull();
    }
}
