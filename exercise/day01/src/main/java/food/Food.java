package food;

import java.time.LocalDate;
import java.util.function.Supplier;

public record Food(ExpirationDate expirationDate, ConsumptionApproval consumption) {
    public boolean isEdible(Supplier<LocalDate> now) {
        return expirationDate.hasNotPassed(now.get()) && consumption.isApproved();
    }
}
