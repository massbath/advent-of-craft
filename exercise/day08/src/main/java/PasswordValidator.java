import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PasswordValidator {

    private final List<Character> specialCharactersAllowed = List.of('.', '*', '#', '@', '$', '%', '&');

    public boolean validate(String password) {
        return checkThat(password,
                hasMinimumLength(),
                hasUppercase(),
                hasLowercase(),
                hasNumber(),
                hasSpecialCharactersAllowed()
        );
    }

    @SafeVarargs
    private boolean checkThat(String password, Predicate<String>... rules) {
        return Arrays.stream(rules).allMatch(rule -> rule.test(password));
    }

    private static Predicate<String> hasMinimumLength() {
        return (password) -> password.length() >= 8;
    }

    private Predicate<String> hasUppercase() {
        return (password) -> password.chars().anyMatch(Character::isUpperCase);
    }

    private Predicate<String> hasLowercase() {
        return (password) -> password.chars().anyMatch(Character::isLowerCase);
    }

    private Predicate<String> hasNumber() {
        return (password) -> password.chars().anyMatch(Character::isDigit);
    }

    private Predicate<String> hasSpecialCharactersAllowed() {
        return (password) -> password.chars().anyMatch(o -> specialCharactersAllowed.contains((char) o));
    }
}
