package christmas.model.benefit;

import java.util.ArrayList;
import java.util.List;

public class BenefitHistories {
    private final List<DiscountHistory> discountHistories;
    private final List<GiftHistory> giftHistories;
    private final int totalDiscountAmount;
    private final int totalGiftPrice;
    private final int totalBenefitAmount;

    public BenefitHistories() {
        this.discountHistories = new ArrayList<>();
        this.giftHistories = new ArrayList<>();
        this.totalDiscountAmount = 0;
        this.totalGiftPrice = 0;
        this.totalBenefitAmount = 0;
    }

    private BenefitHistories(List<DiscountHistory> discountHistories, List<GiftHistory> giftHistories,
                            int totalDiscountAmount, int totalGiftPrice, int totalBenefitAmount) {
        this.discountHistories = discountHistories;
        this.giftHistories = giftHistories;
        this.totalDiscountAmount = totalDiscountAmount;
        this.totalGiftPrice = totalGiftPrice;
        this.totalBenefitAmount = totalBenefitAmount;
    }

    public static BenefitHistories from(List<DiscountHistory> discountHistories, List<GiftHistory> giftHistories) {
        int totalDiscountAmount = calculateTotalDiscountAmount(discountHistories);
        int totalGiftPrice = calculateTotalGiftPrice(giftHistories);
        int totalBenefitAmount = totalDiscountAmount + totalGiftPrice;
        return new BenefitHistories(discountHistories, giftHistories, totalDiscountAmount, totalGiftPrice,
                totalBenefitAmount);
    }

    private static int calculateTotalDiscountAmount(List<DiscountHistory> discountHistories) {
        int totalDiscountAmount = 0;
        for (DiscountHistory discountHistory : discountHistories) {
            totalDiscountAmount += discountHistory.getDiscountAmount();
        }
        return totalDiscountAmount;
    }

    private static int calculateTotalGiftPrice(List<GiftHistory> giftHistories) {
        int totalGiftPrice = 0;
        for (GiftHistory giftHistory : giftHistories) {
            totalGiftPrice += giftHistory.calculateBenefitAmount();
        }
        return totalGiftPrice;
    }

    public List<DiscountHistory> getDiscountHistories() {
        return discountHistories;
    }

    public List<GiftHistory> getGiftHistories() {
        return giftHistories;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public int getTotalGiftPrice() {
        return totalGiftPrice;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
