package christmas.dto;

import christmas.model.benefit.GiftHistory;
import christmas.model.benefit.GiftPolicy;
import java.util.ArrayList;
import java.util.List;

public record GiftHistoryDtos(List<GiftHistoryDto> giftHistories, int totalGiftPrice, String policyName) {
    public static GiftHistoryDtos of(List<GiftHistory> giftHistories, int totalGiftPrice) {
        List<GiftHistoryDto> dtos = new ArrayList<>();
        for (GiftHistory giftHistory : giftHistories) {
            dtos.add(new GiftHistoryDto(giftHistory.getGiftName(), giftHistory.getAmount(), giftHistory.getTotalPrice()));
        }
        return new GiftHistoryDtos(dtos, totalGiftPrice, GiftPolicy.getPolicyName());
    }

    public record GiftHistoryDto(String giftName, int giftAmount, int giftPrice) {
    }
}
