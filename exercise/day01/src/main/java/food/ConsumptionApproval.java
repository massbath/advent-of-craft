package food;

import java.util.UUID;

public record ConsumptionApproval(boolean approvedForConsumption, UUID inspectorId) {
    boolean isApproved() {
        return approvedForConsumption && inspectorId != null;
    }
}
