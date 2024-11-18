package christmas.dto;

import christmas.model.DiscountPolicy;
import christmas.model.FreeGiftPolicy;
import christmas.model.Menu;
import christmas.model.Receipt;
import java.util.ArrayList;
import java.util.List;

public record BenefitDtos(List<DiscountDto> discountDtos, List<FreeGiftDto> freeGiftDtos, int totalBenefitAmount,
                           String freeGiftPolicyName, int totalGiftBenefitAmount) {

    public static BenefitDtos of(Receipt receipt) {
        List<DiscountDto> discountDtos = createDiscountDtos(receipt);
        List<FreeGiftDto> freeGiftDtos = createFreeGiftDtos(receipt);
        return new BenefitDtos(discountDtos, freeGiftDtos, receipt.calculateTotalBenefitAmount(),
                FreeGiftPolicy.getName(), receipt.calculateTotalGiftBenefitAmount());
    }

    private static List<DiscountDto> createDiscountDtos(Receipt receipt) {
        List<DiscountDto> discountDtos = new ArrayList<>();
        for (DiscountPolicy policy : receipt.getDiscountHistory().keySet()) {
            discountDtos.add(new DiscountDto(
                    policy.getName(), receipt.getDiscountHistory().get(policy)));
        }
        return discountDtos;
    }

    private static List<FreeGiftDto> createFreeGiftDtos(Receipt receipt) {
        List<FreeGiftDto> freeGiftDtos = new ArrayList<>();
        for (Menu gift : receipt.getFreeGiftHistory().keySet()) {
            freeGiftDtos.add(new FreeGiftDto(
                    gift.getName(), receipt.getFreeGiftHistory().get(gift)));
        }
        return freeGiftDtos;
    }

    public record DiscountDto(String name, int discountAmount) {
    }

    public record FreeGiftDto(String name, int giftAmount) {
    }
}
