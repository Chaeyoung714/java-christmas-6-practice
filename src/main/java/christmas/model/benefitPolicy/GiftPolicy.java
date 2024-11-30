package christmas.model.benefitPolicy;

import christmas.dto.GiftDto;

public enum GiftPolicy {
    FREE_CHAMPAGNE("샴페인", 1, 120000),
    ;

    private static final String policyName = "증정 이벤트";

    private final String giftName;
    private final int amount;
    private final int limitPrice;

    GiftPolicy(String giftName, int amount, int limitPrice) {
        this.giftName = giftName;
        this.amount = amount;
        this.limitPrice = limitPrice;
    }

    public boolean isAvailable(int price) {
        if (price >= limitPrice) {
            return true;
        }
        return false;
    }

    public GiftDto findGift() {
        return new GiftDto(giftName, amount);
    }

    public static String getPolicyName() {
        return policyName;
    }
}
