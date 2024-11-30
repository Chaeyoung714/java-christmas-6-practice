package christmas.controller;

import christmas.view.InputHandler;
import christmas.view.OutputView;

public class ChristmasController {
    private final InputHandler inputHandler;
    private final OutputView outputView;

    public ChristmasController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void run() {
        int date = inputHandler.inputDate();
        System.out.println("date = " + date);
    }
}
