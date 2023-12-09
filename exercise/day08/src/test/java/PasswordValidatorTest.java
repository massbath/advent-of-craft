import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
    }

    @Test
    void a_password_should_have_at_least_8_characters() {
        assertThat(validator.validate("azerty")).isFalse();
    }

    @Test
    void a_password_should_contains_at_least_1_capital_letter() {
        assertThat(validator.validate("azertyytreza")).isFalse();
    }

    @Test
    void a_password_should_contains_at_least_1_lowercase_letter() {
        assertThat(validator.validate("AZERTYYTREZA")).isFalse();
    }

    @Test
    void a_password_should_contains_at_least_1_number_letter() {
        assertThat(validator.validate("AZERTYytreza")).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "AZERTY3treza, false",
            "AZERTY3trez., true",
            "AZERTY3trez*, true",
            "AZERTY3trez#, true",
            "AZERTY3trez@, true",
            "AZERTY3trez$, true",
            "AZERTY3trez%, true",
            "AZERTY3trez&, true",
            "AZERTY3trez!, false",
            "AZERTY3trez|, false",
    })
    void a_password_should_contains_at_least_1_allowed_special_character(String password, boolean isValid) {
        assertThat(validator.validate(password)).isEqualTo(isValid);
    }
}
