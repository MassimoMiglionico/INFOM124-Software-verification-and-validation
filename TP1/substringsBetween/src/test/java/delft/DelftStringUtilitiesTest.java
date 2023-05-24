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
//    @Test
//    void strSingle(){
//        assertThat(substringsBetween("a", "a", "a")).isNull();
//        assertThat(substringsBetween("a", "b", "b")).isNull();
//        assertThat(substringsBetween("a", "a", "b")).isNull();
//        assertThat(substringsBetween("a", "b", "a")).isNull();
//    }
//
//    @Test
//    void strMultiple(){
//        assertThat(substringsBetween("aa", "a", "a")).isEqualTo(new String[]{""});
//        assertThat(substringsBetween("aaa", "a", "a")).isEqualTo(new String[]{""});
//        assertThat(substringsBetween("aaaa", "a", "a")).isEqualTo(new String[]{"", ""});
//        assertThat(substringsBetween("ab", "a", "b")).isEqualTo(new String[]{""});
//        assertThat(substringsBetween("abc", "a", "c")).isEqualTo(new String[]{"b"});
//        assertThat(substringsBetween("abbc", "a", "c")).isEqualTo(new String[]{"bb"});
//    }
//
//    @Test
//    void strEmptyOrNull(){
//        assertThat(substringsBetween(null, "T", "n")).isNull();
//        assertThat(substringsBetween("", "T", "n")).isEqualTo(new String[]{});
//    }
//
//    @Test
//    void strNoMatch(){
//        assertThat(substringsBetween("{This test is OK}", "A", "B")).isNull();
//    }
//
//    @Test
//    void invertOpenAndClose(){
//        assertThat(substringsBetween("{This test is OK}", "}", "{")).isNull();
//    }
//
//    @Test
//    void openNEmptyOrNull(){
//        assertThat(substringsBetween("{This test is OK}", null, "}")).isNull();
//        assertThat(substringsBetween("{This test is OK}", "", "}")).isNull();
//    }

//    @Test
//    void closeNEmptyOrNull(){
//        assertThat(substringsBetween("{This test is OK}", "{", null)).isNull();
//        assertThat(substringsBetween("{This test is OK}", "{", "")).isNull();
//    }
    @ParameterizedTest
    @MethodSource("subStringTest")
    void subStringAssert(String testcase, String str, String open, String close, String[] expected){
        assertThat(substringsBetween(str, open, close)).isEqualTo(expected);
    }

    private static Stream<Arguments> subStringTest(){
        return Stream.of(
            Arguments.of("Null str", null, "a", "a", null),
            Arguments.of("Null open", "test", null, "t", null),
            Arguments.of("Null close", "test", "t", null, null),
            Arguments.of("Empty str", "", "a", "a", strings()),
            Arguments.of("Empty open", "test", "", "t", null),
            Arguments.of("Empty close", "test", "t", "", null),
            Arguments.of("No open in str", "TestC", "O", "C", null),
            Arguments.of("No close in str", "OTest", "O", "C", null),
            Arguments.of("invert open and close", "CTestO", "O", "C", null),
            Arguments.of("Single character str", "a", "b", "b", null),
            Arguments.of("Single character str", "a", "a", "a", null),
            Arguments.of("Nothing between open and close", "TP", "T", "P", strings("")),
            Arguments.of("Word between open and close", "TTestP", "T", "P", strings("Test")),
            Arguments.of("Many open and close with no words between", "aaaaaa", "a", "a", strings("","","")),
            Arguments.of("Many open and close with words between", "aTest1aaTest2aaTest3a", "a", "a", strings("Test1","Test2","Test3")),
            Arguments.of("", "OTestCOfdgs", "O", "C", strings("Test"))
        );
    }

    private static String[] strings(String... s) { return s; }
}
