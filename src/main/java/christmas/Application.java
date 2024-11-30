package christmas;

import christmas.controller.ChristmasController;
import christmas.repository.MenuRepository;
import christmas.service.MenuService;
import christmas.service.OrderService;
import christmas.view.InputHandler;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MenuRepository menuRepository = new MenuRepository();
        ChristmasController controller = new ChristmasController(
                new InputHandler(new InputView())
                , new OutputView()
                , new MenuService(menuRepository)
                , new OrderService(menuRepository)
        );

        controller.run();
    }
}