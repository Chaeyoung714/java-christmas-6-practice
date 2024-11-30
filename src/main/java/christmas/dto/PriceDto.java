package christmas.dto;

import christmas.model.benefit.BenefitHistories;
import christmas.model.reservation.Reservation;

public record PriceDto(int totalBenefitPrice, int totalPaymentPrice) {

    public static PriceDto from(Reservation reservation, BenefitHistories benefitHistories) {
        int totalOrderPrice = reservation.getTotalOrderAmount();
        int totalBenefitPrice = benefitHistories.getTotalBenefitAmount();
        int totalDiscountPrice = benefitHistories.getTotalDiscountAmount();
        return new PriceDto(totalBenefitPrice, totalOrderPrice - totalDiscountPrice);
    }
}
