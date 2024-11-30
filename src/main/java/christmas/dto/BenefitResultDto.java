package christmas.dto;

import christmas.model.benefit.BenefitHistories;
import christmas.model.benefitPolicy.Badge;
import java.util.Optional;

public record BenefitResultDto(BenefitHistories benefitHistories, Optional<Badge> badge) {
}
