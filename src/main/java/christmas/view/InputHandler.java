package christmas.view;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public int inputDate() {
        while (true) {
            try {
                return inputView.inputDate();
            } catch (IllegalArgumentException e) {
                ErrorOutputView.printErrorMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }
}
