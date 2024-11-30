package christmas.service;

import christmas.dto.GiftDto;
import christmas.model.benefit.Badge;
import christmas.model.benefit.BenefitHistories;
import christmas.model.benefit.DiscountHistory;
import christmas.model.benefit.DiscountPolicy;
import christmas.model.benefit.GiftHistory;
import christmas.model.benefit.GiftPolicy;
import christmas.model.reservation.Menu;
import christmas.model.reservation.Reservation;
import christmas.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BenefitService {
    private final MenuRepository menuRepository;

    public BenefitService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public BenefitHistories applyBenefit(Reservation reservation) {
        if (!isAvailable(reservation)) {
            return new BenefitHistories();
        }
        List<DiscountHistory> discountHistories = applyDiscount(reservation);
        List<GiftHistory> giftHistories = applyGift(reservation);
        return BenefitHistories.from(discountHistories, giftHistories);
    }

    private List<DiscountHistory> applyDiscount(Reservation reservation) {
        List<DiscountHistory> discountHistories = new ArrayList<>();
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            if (discountPolicy.isAvailable(reservation.getDate())) {
                int discountAmount = discountPolicy.calculateDiscountAmount(reservation);
                discountHistories.add(new DiscountHistory(discountPolicy, discountAmount));
            }
        }
        return discountHistories;
    }

    private List<GiftHistory> applyGift(Reservation reservation) {
        List<GiftHistory> giftHistories = new ArrayList<>();
        for (GiftPolicy giftPolicy : GiftPolicy.values()) {
            if (giftPolicy.isAvailable(reservation.getTotalOrderAmount())) {
                GiftDto gift = giftPolicy.findGift();
                Menu giftMenu = menuRepository.findByName(gift.name());
                giftHistories.add(GiftHistory.from(giftPolicy, giftMenu, gift.amount()));
            }
        }
        return giftHistories;
    }

    private boolean isAvailable(Reservation reservation) {
        return reservation.getTotalOrderAmount() >= 10000;
    }

    public Optional<Badge> attachBadge(BenefitHistories benefitHistories) {
        Optional<Badge> badge = Badge.findBadge(benefitHistories.getTotalBenefitAmount());
        return badge;
    }
}
