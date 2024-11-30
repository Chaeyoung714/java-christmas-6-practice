package christmas.view;

import java.util.Map;

public class InputValidator {
    public static void validateDate(String dateInput) {
        try {
            int date = Integer.parseInt(dateInput);
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateOrder(String[] menuAndAmount) {
        validateOrderFormat(menuAndAmount);
        validateMenuName(menuAndAmount[0]);
        validateAmount(menuAndAmount[1]);
    }

    private static void validateMenuName(String menuName) {
        if (menuName == null || menuName.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateAmount(String amount) {
        if (Integer.parseInt(amount) < 1) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateOrderFormat(String[] menuAndAmount) {
        if (menuAndAmount.length != 2) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateNameDuplication(String menuName, Map<String, Integer> ordersMap) {
        if (ordersMap.keySet().contains(menuName)) {
            throw new IllegalArgumentException();
        }
    }
}
