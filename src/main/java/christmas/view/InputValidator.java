package christmas.view;

public final class InputValidator {

    private InputValidator() {
    }

    public static void validateDateInput(String dateInput) {
        try {
            int date = Integer.parseInt(dateInput);
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateMenusInput(String menusInput) {
        String[] parsedMenus = menusInput.split(",", -1);
        for (String parsedMenu : parsedMenus) {
            String[] menuInfo = parsedMenu.split("-", -1);
            if (menuInfo.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            String menuAmountInput = menuInfo[1];
            validateMenuAmount(menuAmountInput);
        }
    }

    private static void validateMenuAmount(String menuAmountInput) {
        try {
            int menuAmount = Integer.parseInt(menuAmountInput);
            if (menuAmount < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
