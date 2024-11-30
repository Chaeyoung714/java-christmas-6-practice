package christmas.service;

import christmas.dto.GiftDto;
import christmas.model.benefit.BenefitHistories;
import christmas.model.benefit.DiscountPolicy;
import christmas.model.benefit.GiftPolicy;
import christmas.model.reservation.Menu;
import christmas.model.reservation.Reservation;
import christmas.repository.MenuRepository;

public class BenefitService {
    private final MenuRepository menuRepository;

    public BenefitService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public BenefitHistories applyBenefit(Reservation reservation) {
        BenefitHistories benefitHistories = new BenefitHistories();
        if (!isAvailable(reservation)) {
            return benefitHistories;
        }
        applyDiscount(reservation, benefitHistories);
        applyGift(reservation, benefitHistories);
        return benefitHistories;
    }

    private void applyDiscount(Reservation reservation, BenefitHistories benefitHistories) {
        for (DiscountPolicy discountPolicy : DiscountPolicy.values()) {
            if (discountPolicy.isAvailable(reservation.getDate())) {
                int discountAmount = discountPolicy.calculateDiscountAmount(reservation);
                benefitHistories.updateDiscountHistory(discountPolicy, discountAmount);
            }
        }
    }

    private void applyGift(Reservation reservation, BenefitHistories benefitHistories) {
        for (GiftPolicy giftPolicy : GiftPolicy.values()) {
            if (giftPolicy.isAvailable(reservation.calculateTotalPrice())) {
                GiftDto gift = giftPolicy.findGift();
                Menu giftMenu = menuRepository.findByName(gift.name());
                benefitHistories.updateGiftHistory(giftPolicy, giftMenu, gift.amount());
            }
        }
    }

    private boolean isAvailable(Reservation reservation) {
        return reservation.calculateTotalPrice() >= 10000;
    }
}
