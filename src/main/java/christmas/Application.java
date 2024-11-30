package christmas;

import christmas.controller.ChristmasController;
import christmas.repository.MenuRepository;
import christmas.repository.MenuRepositoryImpl;
import christmas.service.BadgeService;
import christmas.service.BenefitService;
import christmas.service.OrderService;
import christmas.view.InputHandler;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {

        MenuRepository menuRepository = new MenuRepositoryImpl();

        ChristmasController controller = new ChristmasController(
                new InputHandler(new InputView())
                , new OutputView()
                , new OrderService(menuRepository)
                , new BenefitService(menuRepository)
                , new BadgeService()
        );

        controller.run();
    }
}