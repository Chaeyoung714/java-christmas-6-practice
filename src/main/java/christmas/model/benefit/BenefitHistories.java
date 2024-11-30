package christmas.model.benefit;

import christmas.model.reservation.Menu;
import java.util.ArrayList;
import java.util.List;

public class BenefitHistories {
    private final List<DiscountHistory> discountHistories;
    private final List<GiftHistory> giftHistories;

    public BenefitHistories() {
        this.discountHistories = new ArrayList<>();
        this.giftHistories = new ArrayList<>();
    }

    public void updateDiscountHistory(DiscountPolicy discountPolicy, int discountAmount) {
        this.discountHistories.add(new DiscountHistory(discountPolicy, discountAmount));
    }

    public void updateGiftHistory(GiftPolicy giftPolicy, Menu gift, int amount) {
        this.giftHistories.add(GiftHistory.from(giftPolicy, gift, amount));
    }

    public int calculateTotalBenefitAmount() {
        int totalBenefitAmount = 0;
        for (DiscountHistory discountHistory : discountHistories) {
            totalBenefitAmount += discountHistory.getDiscountAmount();
        }
        totalBenefitAmount += calculateTotalGiftPrice();
        return totalBenefitAmount;
    }

    public int calculateTotalGiftPrice() {
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
}
