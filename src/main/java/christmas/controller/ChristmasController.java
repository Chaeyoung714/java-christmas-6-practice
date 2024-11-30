package christmas.controller;

import christmas.model.Reservation;
import christmas.service.MenuService;
import christmas.service.OrderService;
import christmas.view.InputHandler;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final MenuService menuService;
    private final OrderService orderService;

    public ChristmasController(InputHandler inputHandler, OutputView outputView, MenuService menuService,
                               OrderService orderService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void run() {
        menuService.registerMenus();
        int date = inputHandler.inputDate();
        Map<String, Integer> ordersInput = inputHandler.inputOrders();
        Reservation reservation = orderService.registerOrders(date, ordersInput);

    }
}
