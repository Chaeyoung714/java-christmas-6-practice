package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Receipt {
    private final Map<DiscountPolicy, Integer> discountHistory;
    private final Map<Menu, Integer> freeGiftHistory;

    public Receipt() {
        this.discountHistory = new HashMap<>();
        this.freeGiftHistory = new HashMap<>();
    }

    public void updateDiscount(DiscountPolicy discountPolicy, int discountAmount) {
        if (!discountHistory.containsKey(discountPolicy)) {
            discountHistory.put(discountPolicy, discountAmount);
            return;
        }
        throw new IllegalStateException("[SYSTEM] Duplicated discount policy applied");
    }

    public void updateFreeGift(FreeGiftPolicy freeGiftPolicy, int giftAmount) {
        if (!freeGiftHistory.containsKey(freeGiftPolicy.getGift())) {
            freeGiftHistory.put(freeGiftPolicy.getGift(), giftAmount);
            return;
        }
        throw new IllegalStateException("[SYSTEM] Duplicated free gift policy applied");
    }

    public int calculateTotalBenefitAmount() {
        int totalDiscountAmount = 0;
        for (DiscountPolicy policy : discountHistory.keySet()) {
            totalDiscountAmount += discountHistory.get(policy);
        }
        int totalGiftPrice = calculateTotalGiftBenefitAmount();
        return totalDiscountAmount + totalGiftPrice;
    }

    public int calculateTotalGiftBenefitAmount() {
        int totalGiftPrice = 0;
        for (Menu gift : freeGiftHistory.keySet()) {
            totalGiftPrice += gift.getPrice() * freeGiftHistory.get(gift);
        }
        return totalGiftPrice;
    }

    public Map<DiscountPolicy, Integer> getDiscountHistory() {
        return discountHistory;
    }

    public Map<Menu, Integer> getFreeGiftHistory() {
        return freeGiftHistory;
    }
}
