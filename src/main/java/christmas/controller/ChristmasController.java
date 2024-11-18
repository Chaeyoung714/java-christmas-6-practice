package christmas.controller;

import christmas.dto.BenefitDtos;
import christmas.dto.OrderDtos;
import christmas.dto.PaymentAmountDto;
import christmas.model.Badge;
import christmas.model.Receipt;
import christmas.model.Reservation;
import christmas.service.BenefitService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BenefitService benefitService;

    public ChristmasController(InputView inputView, OutputView outputView, BenefitService benefitService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.benefitService = benefitService;
    }

    public void run() {
        String dateInput = reserveDate();
        Reservation reservation = reserveOrders(dateInput);
        Receipt receipt = applyBenefit(reservation);
        Badge badge = applyBadge(receipt);
        outputOrderResult(reservation, receipt, badge);
    }

    private String reserveDate() {
        while (true) {
            try {
                return inputView.inputDate();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Reservation reserveOrders(String dateInput) {
        while(true) {
            try {
                String ordersInput = inputView.inputOrders();
                return Reservation.from(dateInput, ordersInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Receipt applyBenefit(Reservation reservation) {
        Receipt receipt = new Receipt();
        benefitService.applyDiscount(receipt, reservation);
        benefitService.applyFreeGift(receipt, reservation);
        return receipt;
    }

    private Badge applyBadge(Receipt receipt) {
        return benefitService.applyBadge(receipt);
    }

    private void outputOrderResult(Reservation reservation, Receipt receipt, Badge badge) {
        OrderDtos orderDtos = OrderDtos.of(reservation);
        BenefitDtos benefitDtos = BenefitDtos.of(receipt);
        outputView.printStartLine(reservation.getDate());
        outputView.printOrders(orderDtos);
        outputView.printBenefits(benefitDtos);
        outputView.printFinalPayment(PaymentAmountDto.from(benefitDtos, orderDtos));
        outputView.printBadge(badge.getName());
    }

}
