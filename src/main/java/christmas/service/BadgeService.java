package christmas.service;

import christmas.model.benefit.BenefitHistories;
import christmas.model.benefitPolicy.Badge;
import java.util.Optional;

public class BadgeService {

    public Optional<Badge> attachBadge(BenefitHistories benefitHistories) {
        Optional<Badge> badge = Badge.findBadge(benefitHistories.getTotalBenefitAmount());
        return badge;
    }
}
