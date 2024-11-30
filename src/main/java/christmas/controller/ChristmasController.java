package christmas.controller;

import christmas.dto.BenefitResultDto;
import christmas.dto.ReservationResultDto;
import christmas.model.benefit.BenefitHistories;
import christmas.model.benefitPolicy.Badge;
import christmas.model.reservation.Reservation;
import christmas.service.BadgeService;
import christmas.service.BenefitService;
import christmas.service.OrderService;
import christmas.view.ErrorOutputView;
import christmas.view.InputHandler;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Optional;

public class ChristmasController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final OrderService orderService;
    private final BenefitService benefitService;
    private final BadgeService badgeService;

    public ChristmasController(InputHandler inputHandler, OutputView outputView, OrderService orderService,
                               BenefitService benefitService, BadgeService badgeService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.orderService = orderService;
        this.benefitService = benefitService;
        this.badgeService = badgeService;
    }

    public void run() {
        int date = inputHandler.inputDate();
        Reservation reservation = registerReservation(date);
        BenefitResultDto benefitResultDto = applyBenefit(reservation);
        outputReservationResult(reservation, benefitResultDto);
    }

    private Reservation registerReservation(int date) {
        while (true) {
            try {
                Map<String, Integer> ordersInput = inputHandler.inputOrders();
                return orderService.registerOrders(date, ordersInput);
            } catch (IllegalArgumentException e) {
                ErrorOutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private BenefitResultDto applyBenefit(Reservation reservation) {
        BenefitHistories benefitHistories = benefitService.applyBenefit(reservation);
        Optional<Badge> badge = badgeService.attachBadge(benefitHistories);
        return new BenefitResultDto(benefitHistories, badge);
    }

    private void outputReservationResult(Reservation reservation, BenefitResultDto benefitResultDto) {
        BenefitHistories benefitHistories = benefitResultDto.benefitHistories();
        Optional<Badge> badge = benefitResultDto.badge();
        ReservationResultDto reservationResultDto = ReservationResultDto.from(reservation, benefitHistories, badge);
        outputView.printResult(reservationResultDto);
    }
}
