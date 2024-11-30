package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputHandler;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController controller = new ChristmasController(
                new InputHandler(new InputView())
                , new OutputView()
        );

        controller.run();
    }
}