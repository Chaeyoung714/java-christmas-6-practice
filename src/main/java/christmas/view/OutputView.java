package christmas.view;

import christmas.dto.BenefitHistoriesDto;
import christmas.dto.DiscountHistoryDtos.DiscountHistoryDto;
import christmas.dto.GiftHistoryDtos;
import christmas.dto.GiftHistoryDtos.GiftHistoryDto;
import christmas.dto.PriceDto;
import christmas.dto.ReservationDtos;
import christmas.dto.ReservationDtos.ReservationDto;
import christmas.dto.ReservationResultDto;
import java.util.Optional;

public class OutputView {
    public void printResult(ReservationResultDto dto) {
        printStartLine(dto.reservations().date());
        printOrders(dto.reservations());
        printBenefitHistories(dto.benefitHistories());
        printPrice(dto.priceInfo());
        printBadge(dto.badgeName());
    }

    private void printStartLine(int date) {
        System.out.println(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date));
    }

    private void printOrders(ReservationDtos dtos) {
        System.out.println(System.lineSeparator() + "<주문 메뉴>");
        for (ReservationDto dto : dtos.reservations()) {
            System.out.println(String.format("%s %d개", dto.menuName(), dto.amount()));
        }

        System.out.println(System.lineSeparator() + "<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", dtos.totalOrderAmount()));
    }

    private void printBenefitHistories(BenefitHistoriesDto dto) {
        printGiftHistories(dto.giftHistories());

        System.out.println(System.lineSeparator() + "<혜택 내역>");
        if (dto.giftHistories().giftHistories().isEmpty() && dto.discountHistories().discountHistories().isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (DiscountHistoryDto discountHistoryDto : dto.discountHistories().discountHistories()) {
            System.out.println(String.format("%s: -%,d원"
                    , discountHistoryDto.policyName(), discountHistoryDto.discountAmount()));
        }
        System.out.println(String.format("%s: -%,d원"
                , dto.giftHistories().policyName(), dto.giftHistories().totalGiftPrice()));
    }

    private void printGiftHistories(GiftHistoryDtos dtos) {
        System.out.println(System.lineSeparator() + "<증정 메뉴>");
        if (dtos.giftHistories().isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (GiftHistoryDto dto : dtos.giftHistories()) {
            System.out.println(String.format("%s %d개", dto.giftName(), dto.giftAmount()));
        }
    }

    private void printPrice(PriceDto dto) {
        System.out.println(System.lineSeparator() + "<총혜택 금액>");
        String printingFormat = "-%,d원";
        if (dto.totalBenefitPrice() == 0) {
            printingFormat = "%d원";
        }
        System.out.println(String.format(printingFormat, dto.totalBenefitPrice()));

        System.out.println(System.lineSeparator() + "<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", dto.totalPaymentPrice()));
    }

    private void printBadge(Optional<String> badgeName) {
        System.out.println(System.lineSeparator() + "<12월 이벤트 배지>");
        if (badgeName.isEmpty()) {
            System.out.println("없음");
            return;
        }
        System.out.println(badgeName.get());
    }
}
