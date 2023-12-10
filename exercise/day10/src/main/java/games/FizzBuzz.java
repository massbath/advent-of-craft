package games;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;
    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        if (isOutOfRange(input)) {
            throw new OutOfRangeException();
        }
        return FizzBuzzConditions.convert(input);
    }

    private static boolean isOutOfRange(Integer input) {
        return input <= MIN || input > MAX;
    }

    private enum FizzBuzzConditions{
        FIZZBUZZ((number) -> is(15, number), (number)-> "FizzBuzz"),
        FIZZ((number) -> is(3, number),(number)-> "Fizz"),
        BUZZ((number) -> is(5, number), (number)-> "Buzz"),
        DEFAULT((number) -> true, Object::toString);

        private final Predicate<Integer> condition;
        private final Function<Integer,String> converter;

        FizzBuzzConditions(Predicate<Integer> condition, Function<Integer, String> converter) {
            this.condition = condition;
            this.converter = converter;
        }

        public static String convert(Integer number){
            return stream(FizzBuzzConditions.values())
                    .filter(it -> it.condition.test(number))
                    .findFirst()
                    .map(it -> it.converter.apply(number))
                    .get();
        }
        private static boolean is(Integer divisor, Integer input) {
            return input % divisor == 0;
        }
    }
}

