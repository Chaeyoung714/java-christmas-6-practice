package christmas.dto;

public record PaymentAmountDto(int paymentAmount) {

    public static PaymentAmountDto from(BenefitDtos benefitDtos, OrderDtos orderDtos) {
        int paymentAmount =
                orderDtos.totalPrice() - benefitDtos.totalBenefitAmount() + benefitDtos.totalGiftBenefitAmount();
        return new PaymentAmountDto(paymentAmount);
    }
}
