package christmas.dto;

import christmas.model.benefit.BenefitHistories;
import christmas.model.reservation.Reservation;

public record PriceDto(int totalBenefitPrice, int totalPaymentPrice) {

    public static PriceDto from(Reservation reservation, BenefitHistories benefitHistories) {
        int totalOrderPrice = reservation.getTotalOrderAmount();
        int totalBenefitPrice = benefitHistories.calculateTotalBenefitAmount();
        int totalDiscountPrice = totalBenefitPrice - benefitHistories.calculateTotalGiftPrice();
        return new PriceDto(totalBenefitPrice, totalOrderPrice - totalDiscountPrice);
    }
}
