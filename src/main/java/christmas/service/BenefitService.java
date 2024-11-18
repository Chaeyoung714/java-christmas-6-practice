package christmas.service;

import static christmas.model.DiscountPolicy.*;
import static christmas.model.DiscountPolicy.CHIRSTMAS_DISCOUNT;
import static christmas.model.DiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKDAY_DISCOUNT;
import static christmas.model.DiscountPolicy.WEEKEND_DISCOUNT;
import static christmas.model.FreeGiftPolicy.CHAMPAGNE_FREE;

import christmas.model.Badge;
import christmas.model.DiscountPolicy;
import christmas.model.FreeGiftPolicy;
import christmas.model.MenuType;
import christmas.model.Receipt;
import christmas.model.Reservation;

public class BenefitService {

    public void applyDiscount(Receipt receipt, Reservation reservation) {
        if (reservation.calculateTotalPrice() < 10000) {
            return;
        }
        int date = reservation.getDate();
        if (DiscountPolicy.isAvailable(date, CHIRSTMAS_DISCOUNT)) {
            int increasedDiscountAmount = (reservation.getDate() - 1) * 100;
            int discountAmount = CHIRSTMAS_DISCOUNT.getDefaultDiscountAmount() + increasedDiscountAmount;
            receipt.updateDiscount(CHIRSTMAS_DISCOUNT, discountAmount);
        }
        if (DiscountPolicy.isAvailable(date, WEEKDAY_DISCOUNT)) {
            int dessertOrderAmount = reservation.calculateTotalAmountOf(MenuType.DESSERT);
            int discountAmount = WEEKDAY_DISCOUNT.getDefaultDiscountAmount() * dessertOrderAmount;
            receipt.updateDiscount(WEEKDAY_DISCOUNT, discountAmount);
        }
        if (DiscountPolicy.isAvailable(date, WEEKEND_DISCOUNT)) {
            int mainOrderAmount = reservation.calculateTotalAmountOf(MenuType.MAIN);
            int discountAmount = WEEKEND_DISCOUNT.getDefaultDiscountAmount() * mainOrderAmount;
            receipt.updateDiscount(WEEKEND_DISCOUNT, discountAmount);
        }
        if (DiscountPolicy.isAvailable(date, SPECIAL_DISCOUNT)) {
            int discountAmount = SPECIAL_DISCOUNT.getDefaultDiscountAmount();
            receipt.updateDiscount(SPECIAL_DISCOUNT, discountAmount);
        }
    }

    public void applyFreeGift(Receipt receipt, Reservation reservation) {
        int orderPrice = reservation.calculateTotalPrice();
        if (FreeGiftPolicy.isAvailable(orderPrice, CHAMPAGNE_FREE)) {
            receipt.updateFreeGift(CHAMPAGNE_FREE, 1);
        }
    }

    public Badge applyBadge(Receipt receipt) {
        int totalBenefitAmount = receipt.calculateTotalBenefitAmount();
        return Badge.findBadge(totalBenefitAmount);
    }
}
