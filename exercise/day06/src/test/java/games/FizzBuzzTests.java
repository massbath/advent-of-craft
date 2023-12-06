package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {
    @ParameterizedTest
    @ValueSource(ints = {1, 67, 82,})
    void return_number_without_conversion_when_it_is_not_fizz_or_buzz(int givenNumber) {
        assertThat(FizzBuzz.convert(givenNumber)).isEqualTo(String.valueOf(givenNumber));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 66, 99})
    void returns_Fizz_when_divisible_by_3(int givenNumber) {
        assertThat(FizzBuzz.convert(givenNumber)).isEqualTo("Fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 50, 85})
    void returns_Buzz_when_divisible_by_5(int givenNumber) {
        assertThat(FizzBuzz.convert(givenNumber)).isEqualTo("Buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void returns_FizzBuzz_when_divisible_by_both_3_and_5(int givenNumber) {
        assertThat(FizzBuzz.convert(givenNumber)).isEqualTo("FizzBuzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 101})
    void throws_an_exception_when_number_is_out_of_range(int givenNumber) {
        assertThatThrownBy(() -> FizzBuzz.convert(givenNumber)).isInstanceOf(OutOfRangeException.class);
    }
}