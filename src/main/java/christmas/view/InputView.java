package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

public class InputView {

    public int inputDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
                + System.lineSeparator()
                + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String dateInput = Console.readLine();
        InputValidator.validateDate(dateInput);
        return Integer.parseInt(dateInput);
    }

    public Map<String, Integer> inputOrders() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String ordersInput = Console.readLine();
        return parseOrders(ordersInput);
    }

    private Map<String, Integer> parseOrders(String ordersInput) {
        String[] orders = ordersInput.split(",", -1);
        Map<String, Integer> ordersMap = new HashMap<>();
        for (String order : orders) {
            String[] menuAndAmount = order.split("-", -1);
            InputValidator.validateOrder(menuAndAmount);
            InputValidator.validateNameDuplication(menuAndAmount[0], ordersMap);
            ordersMap.put(menuAndAmount[0], Integer.parseInt(menuAndAmount[1]));
        }
        return ordersMap;
    }
}
