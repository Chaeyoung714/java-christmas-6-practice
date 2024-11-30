package christmas.model.benefit;

import christmas.model.reservation.Menu;

public class GiftHistory {
    private final GiftPolicy giftPolicy;
    private final Menu gift;
    private final int amount;
    private final int totalPrice;

    private GiftHistory(GiftPolicy giftPolicy, Menu gift, int amount, int totalPrice) {
        this.giftPolicy = giftPolicy;
        this.gift = gift;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public static GiftHistory from(GiftPolicy giftPolicy, Menu gift, int amount) {
        return new GiftHistory(giftPolicy, gift, amount, amount * gift.getPrice());
    }

    public GiftPolicy getGiftPolicy() {
        return giftPolicy;
    }

    public int calculateBenefitAmount() {
        return amount * gift.getPrice();
    }

    public String getGiftName() {
        return gift.getName();
    }

    public Menu getGift() {
        return gift;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
