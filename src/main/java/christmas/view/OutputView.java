package christmas.view;

import christmas.model.benefit.Badge;
import christmas.model.benefit.BenefitHistories;
import christmas.model.benefit.DiscountHistory;
import christmas.model.benefit.GiftHistory;
import christmas.model.benefit.GiftPolicy;
import christmas.model.reservation.Order;
import christmas.model.reservation.Reservation;
import java.util.List;
import java.util.Optional;

public class OutputView {
    public void printResult(Reservation reservation, BenefitHistories benefitHistories, Optional<Badge> badge) {
        printStartLine(reservation.getDate());
        printOrders(reservation);
        printBenefitHistories(benefitHistories);
        printPrice(reservation, benefitHistories);
        printBadge(badge);
    }

    private void printStartLine(int date) {
        System.out.println(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date));
    }

    private void printOrders(Reservation reservation) {
        System.out.println(System.lineSeparator() + "<주문 메뉴>");
        for (Order order : reservation.getOrders()) {
            System.out.println(String.format("%s %d개", order.getMenu().getName(), order.getAmount()));
        }

        System.out.println(System.lineSeparator() + "<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", reservation.calculateTotalPrice()));
    }

    private void printBenefitHistories(BenefitHistories benefitHistories) {
        printGiftHistories(benefitHistories.getGiftHistories());

        System.out.println(System.lineSeparator() + "<혜택 내역>");
        if (benefitHistories.getGiftHistories().isEmpty() && benefitHistories.getDiscountHistories().isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (DiscountHistory discountHistory : benefitHistories.getDiscountHistories()) {
            System.out.println(String.format("%s: -%,d원"
                    , discountHistory.getDiscountPolicy().getName(), discountHistory.getDiscountAmount()));
        }
        System.out.println(String.format("%s: -%,d원"
                , GiftPolicy.getPolicyName(), benefitHistories.calculateTotalGiftPrice()));
    }

    private void printGiftHistories(List<GiftHistory> giftHistories) {
        System.out.println(System.lineSeparator() + "<증정 메뉴>");
        if (giftHistories.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (GiftHistory giftHistory : giftHistories) {
            System.out.println(String.format("%s %d개", giftHistory.getGift().getName(), giftHistory.getAmount()));
        }
    }

    private void printPrice(Reservation reservation, BenefitHistories benefitHistories) {
        int totalOrderPrice = reservation.calculateTotalPrice();
        int totalBenefitPrice = benefitHistories.calculateTotalBenefitAmount();
        int totalGiftBenefitPrice = benefitHistories.calculateTotalGiftPrice();

        System.out.println(System.lineSeparator() + "<총혜택 금액>");
        String printingFormat = "-%,d원";
        if (totalBenefitPrice == 0) {
            printingFormat = "%d원";
        }
        System.out.println(String.format(printingFormat, totalBenefitPrice));

        System.out.println(System.lineSeparator() + "<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", totalOrderPrice - totalBenefitPrice + totalGiftBenefitPrice));
    }

    private void printBadge(Optional<Badge> badge) {
        System.out.println(System.lineSeparator() + "<12월 이벤트 배지>");
        if (badge.isEmpty()) {
            System.out.println("없음");
            return;
        }
        System.out.println(badge.get().getName());

    }
}
