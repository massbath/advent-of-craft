package games;

public class FizzBuzz {
    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        checkIfInputIsInRange(input);

        return switch (input) {
            case Integer ignored when isDivisibleBy3(input) && isDivisibleBy5(input) -> "FizzBuzz";
            case Integer ignored when isDivisibleBy3(input) -> "Fizz";
            case Integer ignored when isDivisibleBy5(input) -> "Buzz";
            default -> input.toString();
        };
    }

    private static boolean isDivisibleBy5(Integer input) {
        return input % 5 == 0;
    }

    private static boolean isDivisibleBy3(Integer input) {
        return input % 3 == 0;
    }

    private static void checkIfInputIsInRange(Integer input) throws OutOfRangeException {
        if (input <= 0 || input > 100) throw new OutOfRangeException();
    }
}
