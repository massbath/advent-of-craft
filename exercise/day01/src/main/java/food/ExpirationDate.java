package food;

import java.time.LocalDate;

public record ExpirationDate(LocalDate value) {
    public boolean hasNotPassed(LocalDate date) {
        return value.isAfter(date);
    }
}
