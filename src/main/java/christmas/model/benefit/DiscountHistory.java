package christmas.model.benefit;


public class DiscountHistory {
    private final DiscountPolicy discountPolicy;
    private final int discountAmount;

    public DiscountHistory(DiscountPolicy discountPolicy, int discountAmount) {
        this.discountPolicy = discountPolicy;
        this.discountAmount = discountAmount;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
