package delft;

import static delft.NumberUtils.add;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.*;

import org.apache.xpath.Arg;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class NumberUtilsTest {

    // call the method under test: NumberUtils.add(left, right)
//	@Test
//	void leftBigger() {
//		assertThat(add(numbers(2,4,0), numbers(2,3))).isEqualTo(numbers(2,6,3));
//		assertThat(add(numbers(2,4,0), numbers(1))).isEqualTo(numbers(2,4,1));
//		assertThat(add(numbers(2,4), numbers(2,3))).isEqualTo(numbers(4,7));
//		assertThat(add(numbers(2,3), numbers(1))).isEqualTo(numbers(2,4));
//		assertThat(add(numbers(0,2,3), numbers(1,2))).isEqualTo(numbers(0,3,5));
//		assertThat(add(numbers(0,8,3), numbers(1,7))).isEqualTo(numbers(1,0,0));
//		assertThat(add(numbers(1,9), numbers(1))).isEqualTo(numbers(2,0));
////		assertThat(add(numbers(9,9), numbers(1))).isEqualTo(numbers(1,0,0));
////		assertThat(add(numbers(7), numbers(1))).isEqualTo(numbers(8));
//		assertThat(add(numbers(9), numbers())).isEqualTo(numbers(9));
//		assertThat(add(numbers(), numbers())).isEqualTo(numbers());
//	}
////
//	@Test
//	void rightBigger() {
//		assertThat(add(numbers(2,3), numbers(2,4,0))).isEqualTo(numbers(2,6,3));
//		assertThat(add(numbers(1), numbers(2,4,0))).isEqualTo(numbers(2,4,1));
//		assertThat(add(numbers(2,3), numbers(2,4))).isEqualTo(numbers(4,7));
//		assertThat(add(numbers(1), numbers(2,3))).isEqualTo(numbers(2,4));
//		assertThat(add(numbers(1,2), numbers(0,2,3))).isEqualTo(numbers(0,3,5));
//		assertThat(add(numbers(1,7), numbers(0,8,3))).isEqualTo(numbers(1,0,0));
//		assertThat(add(numbers(1), numbers(1,9))).isEqualTo(numbers(2,0));
////		assertThat(add(numbers(1), numbers(9,9))).isEqualTo(numbers(1,0,0));
////		assertThat(add(numbers(1), numbers(7))).isEqualTo(numbers(8));
//		assertThat(add(numbers(), numbers(9))).isEqualTo(numbers(9));
//	}
//
//	@Test
//	void bigNumbers() {
//		assertThat(add(numbers(1,2,4,8), numbers(5,2))).isEqualTo(numbers(1,3,0,0));
//		assertThat(add(numbers(5,2), numbers(1,2,4,8))).isEqualTo(numbers(1,3,0,0));
//		assertThat(add(numbers(1,2,4,8), numbers(7,5,2))).isEqualTo(numbers(2,0,0,0));
//		assertThat(add(numbers(7,5,2), numbers(1,2,4,8))).isEqualTo(numbers(2,0,0,0));
//	}
//
//	@Test
//	void leftEmptyOrNull(){
//		assertThat(add(null, numbers(2,4))).isNull();
//		assertThat(add(numbers(), numbers(2,4))).isEqualTo(numbers(2,4));
//	}
//
//	@Test
//	void rightEmptyOrNull(){
//		assertThat(add(numbers(2,3), null)).isNull();
//		assertThat(add(numbers(2,3), numbers())).isEqualTo(numbers(2,3));
//	}
//
//	@Test
//	void illegalNumbers(){
//		assertThrows(IllegalArgumentException.class, () -> {
//			add(numbers(-1), numbers(2,3));
//		});
//		assertThrows(IllegalArgumentException.class, () -> {
//			add(numbers(2,4), numbers(10));
//		});
//	}
//	// returns a mutable list of integers
//
	@ParameterizedTest
	@MethodSource("addTest")
	void assertAdd(String testcase, List<Integer> left, List<Integer> right, List<Integer> expected){
		assertThat(add(left, right)).isEqualTo(expected);
	}
	@ParameterizedTest
	@MethodSource("addIllegal")
	void assertIllegal(String testcase, List<Integer> left, List<Integer> right){
		assertThrows(IllegalArgumentException.class, () -> {
			add(left, right);
		});
	}


	private static Stream<Arguments> addTest(){
		return Stream.of(
			Arguments.of("null left", null, numbers(3), null),
			Arguments.of("null right", numbers(2,3), null, null),
			Arguments.of("empty both", numbers(), numbers(), numbers()),
			Arguments.of("empty + single digit", numbers(), numbers(5), numbers(5)),
			Arguments.of("empty + many digits", numbers(4,5), numbers(), numbers(4,5)),
			Arguments.of( "Single digit operation", numbers(3), numbers(4), numbers(7)),
			Arguments.of("Multi digits operation with report", numbers(1,4), numbers(2,6), numbers(4,0)),
			Arguments.of("Mix of number digits", numbers(7), numbers(7,1), numbers(7,8)),
			Arguments.of("useless zero before digits", numbers(0,1,6), numbers(0,4), numbers(0,2,0)),
			Arguments.of("Big numbers", numbers(1,0,0,0,0), numbers(9,9,9,9), numbers(1,9,9,9,9))
		);
	}

	private static Stream<Arguments> addIllegal(){
		return Stream.of(
				Arguments.of("illegal off point bottom", numbers(-1), numbers()),
				Arguments.of("illegal out point bottom", numbers(), numbers(-45)),
				Arguments.of("illegal off point top", numbers(10), numbers()),
				Arguments.of("illegal out point top", numbers(), numbers(96))

		);
	}


	private static List<Integer> numbers(int... nums) {
		List<Integer> list = new ArrayList<>();
		for (int n : nums)
			list.add(n);
		return list;
	}
}
