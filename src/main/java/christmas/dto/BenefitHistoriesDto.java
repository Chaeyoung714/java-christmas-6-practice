package christmas.dto;

import christmas.model.benefit.BenefitHistories;

public record BenefitHistoriesDto(DiscountHistoryDtos discountHistories, GiftHistoryDtos giftHistories) {

    public static BenefitHistoriesDto from(BenefitHistories benefitHistories) {
        DiscountHistoryDtos discountHistoryDtos = DiscountHistoryDtos.of(benefitHistories.getDiscountHistories());
        GiftHistoryDtos giftHistoryDtos = GiftHistoryDtos.of(benefitHistories.getGiftHistories(), benefitHistories.calculateTotalGiftPrice());
        return new BenefitHistoriesDto(discountHistoryDtos, giftHistoryDtos);
    }
}
