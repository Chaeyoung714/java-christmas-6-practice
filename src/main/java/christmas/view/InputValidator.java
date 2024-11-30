package christmas.view;

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

    /**
     * 메뉴 개수가 1 이상의 정수인지 확인한다.
     * 다른 검증조건 체크 필요함
     */
    public static void validateOrders(String ordersInput) {
        try {
            String[] orders = ordersInput.split(",", -1);
            for (String order : orders) {
                String[] menuAndAmount = order.split("-", -1);
                int amount = Integer.parseInt(menuAndAmount[1]);
                if (amount < 1) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
