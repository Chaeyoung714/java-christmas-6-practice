package christmas.view;

import christmas.dto.BenefitDtos;
import christmas.dto.BenefitDtos.DiscountDto;
import christmas.dto.BenefitDtos.FreeGiftDto;
import christmas.dto.OrderDtos;
import christmas.dto.OrderDtos.OrderDto;
import christmas.dto.PaymentAmountDto;

public class OutputView {

    public void printStartLine(int date) {
        System.out.println(String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
                , date)
                + System.lineSeparator());
    }

    public void printOrders(OrderDtos orderDtos) {
        System.out.println("<주문 메뉴>");
        for (OrderDto order : orderDtos.dtos()) {
            System.out.println(String.format("%s %d개", order.menu(), order.amount()));
        }
        System.out.println(System.lineSeparator() + "<할인 전 총주문 금액>");
        System.out.println(String.format("%,d원", orderDtos.totalPrice()));
    }

    public void printBenefits(BenefitDtos benefitDtos) {
        printGiftDetail(benefitDtos);
        printBenefitDetail(benefitDtos);
        printTotalBenefitAmount(benefitDtos);
    }

    private void printGiftDetail(BenefitDtos benefitDtos) {
        System.out.println(System.lineSeparator() + "<증정 메뉴>");
        if (benefitDtos.freeGiftDtos().isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (FreeGiftDto freeGift : benefitDtos.freeGiftDtos()) {
            System.out.println(String.format("%s %d개", freeGift.name(), freeGift.giftAmount()));
        }
    }

    private void printBenefitDetail(BenefitDtos benefitDtos) {
        System.out.println(System.lineSeparator() + "<혜택 내역>");
        if (benefitDtos.discountDtos().isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (DiscountDto discount : benefitDtos.discountDtos()) {
            System.out.println(String.format("%s: -%,d원", discount.name(), discount.discountAmount()));
        }
        String freeGiftPolicyName = benefitDtos.freeGiftPolicyName();
        int freeGiftBenefitAmount = benefitDtos.totalGiftBenefitAmount();
        System.out.println(String.format("%s: -%,d원", freeGiftPolicyName, freeGiftBenefitAmount));
    }

    private void printTotalBenefitAmount(BenefitDtos benefitDtos) {
        System.out.println(System.lineSeparator() + "<총혜택 금액>");
        if (benefitDtos.totalBenefitAmount() == 0) {
            System.out.println("0원");
            return;
        }
        System.out.println(String.format("-%,d원", benefitDtos.totalBenefitAmount()));
    }

    public void printFinalPayment(PaymentAmountDto dto) {
        System.out.println(System.lineSeparator() + "<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d원", dto.paymentAmount()));
    }

    public void printBadge(String badgeName) {
        System.out.println(System.lineSeparator() + "<12월 이벤트 배지>");
        System.out.println(badgeName);
    }
}
