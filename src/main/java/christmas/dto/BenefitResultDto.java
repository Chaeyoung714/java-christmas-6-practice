package christmas.dto;

import christmas.model.benefit.Badge;
import christmas.model.benefit.BenefitHistories;
import java.util.Optional;

public record BenefitResultDto(BenefitHistories benefitHistories, Optional<Badge> badge) {
}
