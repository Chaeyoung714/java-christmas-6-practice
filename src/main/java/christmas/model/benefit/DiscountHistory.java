package christmas.model.benefit;


import christmas.model.benefitPolicy.DiscountPolicy;

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

    public String getDiscountPolicyName() {
        return discountPolicy.getName();
    }
}
