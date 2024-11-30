package christmas.controller;

import christmas.model.benefit.Badge;
import christmas.model.benefit.BenefitHistories;
import christmas.model.reservation.Reservation;
import christmas.service.BenefitService;
import christmas.service.MenuService;
import christmas.service.OrderService;
import christmas.view.InputHandler;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Optional;

public class ChristmasController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final MenuService menuService;
    private final OrderService orderService;
    private final BenefitService benefitService;

    public ChristmasController(InputHandler inputHandler, OutputView outputView, MenuService menuService,
                               OrderService orderService, BenefitService benefitService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.menuService = menuService;
        this.orderService = orderService;
        this.benefitService = benefitService;
    }

    public void run() {
        menuService.registerMenus();
        int date = inputHandler.inputDate();
        Map<String, Integer> ordersInput = inputHandler.inputOrders();
        Reservation reservation = orderService.registerOrders(date, ordersInput);
        BenefitHistories benefitHistories = benefitService.applyBenefit(reservation);
        Optional<Badge> badge = benefitService.attachBadge(benefitHistories);
        outputView.printResult(reservation, benefitHistories, badge);
    }
}
