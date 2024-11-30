package christmas.model.benefit;

import christmas.model.reservation.Menu;

public class GiftHistory {
    private final GiftPolicy giftPolicy;
    private final Menu gift;
    private final int amount;

    public GiftHistory(GiftPolicy giftPolicy, Menu gift, int amount) {
        this.giftPolicy = giftPolicy;
        this.gift = gift;
        this.amount = amount;
    }

    public GiftPolicy getGiftPolicy() {
        return giftPolicy;
    }

    public int calculateBenefitAmount() {
        return amount * gift.getPrice();
    }

    public Menu getGift() {
        return gift;
    }

    public int getAmount() {
        return amount;
    }
}
