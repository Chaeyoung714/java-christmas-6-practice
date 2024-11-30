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
        this.giftHistories.add(new GiftHistory(giftPolicy, gift, amount));
    }

    public List<DiscountHistory> getDiscountHistories() {
        return discountHistories;
    }

    public List<GiftHistory> getGiftHistories() {
        return giftHistories;
    }
}
