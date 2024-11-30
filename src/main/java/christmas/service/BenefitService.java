package christmas.service;

import static christmas.model.benefit.DiscountPolicy.CHRISTMAS_DISCOUNT;
import static christmas.model.benefit.DiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.model.benefit.DiscountPolicy.WEEKDAY_DISCOUNT;
import static christmas.model.benefit.DiscountPolicy.WEEKEND_DISCOUNT;

import christmas.dto.GiftDto;
import christmas.model.benefit.BenefitHistories;
import christmas.model.benefit.DiscountHistory;
import christmas.model.benefit.DiscountPolicy;
import christmas.model.benefit.GiftHistory;
import christmas.model.benefit.GiftPolicy;
import christmas.model.reservation.Menu;
import christmas.model.reservation.MenuType;
import christmas.model.reservation.Reservation;
import christmas.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;

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
            if (discountPolicy.isAvailable(reservation.getDate()) && hasCorrespondingOrder(discountPolicy, reservation)) {
                int discountAmount = calculateDiscountAmount(discountPolicy, discountPolicy.getDefaultDiscountAmount(),
                        reservation);
                discountHistories.add(new DiscountHistory(discountPolicy, discountAmount));
            }
        }
        return discountHistories;
    }

    private boolean hasCorrespondingOrder(DiscountPolicy discountPolicy, Reservation reservation) {
        if (discountPolicy.equals(CHRISTMAS_DISCOUNT)) {
            return true;
        }
        if (discountPolicy.equals(WEEKDAY_DISCOUNT)) {
            return reservation.hasOrderMatchingMenuType(MenuType.DESSERT);
        }
        if (discountPolicy.equals(WEEKEND_DISCOUNT)) {
            return reservation.hasOrderMatchingMenuType(MenuType.MAIN);
        }
        if (discountPolicy.equals(SPECIAL_DISCOUNT)) {
            return true;
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 이름의 할인이 없습니다.");
    }

    private int calculateDiscountAmount(DiscountPolicy discountPolicy, int defaultDiscountAmount, Reservation reservation) {
        if (discountPolicy.equals(CHRISTMAS_DISCOUNT)) {
            return defaultDiscountAmount + (100 * (reservation.getDate() - 1));
        }
        if (discountPolicy.equals(WEEKDAY_DISCOUNT)) {
            return defaultDiscountAmount * reservation.calculateOrderAmountOf(MenuType.DESSERT);
        }
        if (discountPolicy.equals(WEEKEND_DISCOUNT)) {
            return defaultDiscountAmount * reservation.calculateOrderAmountOf(MenuType.MAIN);
        }
        if (discountPolicy.equals(SPECIAL_DISCOUNT)) {
            return defaultDiscountAmount;
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 이름의 할인이 없습니다.");
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
}
