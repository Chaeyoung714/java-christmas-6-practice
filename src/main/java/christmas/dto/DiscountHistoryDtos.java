package christmas.dto;

import christmas.model.benefit.DiscountHistory;
import java.util.ArrayList;
import java.util.List;

public record DiscountHistoryDtos(List<DiscountHistoryDto> discountHistories) {
    public static DiscountHistoryDtos of(List<DiscountHistory> discountHistories) {
        List<DiscountHistoryDto> dtos = new ArrayList<>();
        for (DiscountHistory discountHistory : discountHistories) {
            dtos.add(new DiscountHistoryDto(discountHistory.getDiscountPolicyName(),
                    discountHistory.getDiscountAmount()));
        }
        return new DiscountHistoryDtos(dtos);
    }

    public record DiscountHistoryDto(String policyName, int discountAmount) {
    }
}
