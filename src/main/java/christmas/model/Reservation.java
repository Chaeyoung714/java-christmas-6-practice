package christmas.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reservation {
    private final int date;
    private final List<Order> orders;

    public Reservation(int date, List<Order> orders) {
        this.date = date;
        this.orders = orders;
    }

    public static Reservation from(String dateInput, String ordersInput) {
        Map<Menu, Integer> parsedOrders = createOrders(ordersInput);
        validateOrdersPriceAndAmount(parsedOrders);
        validateOrderMenus(parsedOrders);
        List<Order> orders = new ArrayList<>();
        for (Menu menu : parsedOrders.keySet()) {
            orders.add(new Order(menu, parsedOrders.get(menu)));
        }
        return new Reservation(Integer.parseInt(dateInput), orders);
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += order.getAmount() * order.getMenu().getPrice();
        }
        return totalPrice;
    }

    public int calculateTotalAmountOf(MenuType menuType) {
        int totalAmount = 0;
        for (Order order : orders) {
            if (MenuType.matches(menuType, order.getMenu())) {
                totalAmount += order.getAmount();
            }
        }
        return totalAmount;
    }

    private static Map<Menu, Integer> createOrders(String ordersInput) {
        try {
            Map<String, Integer> parsedMenuInfos = parseOrdersInput(ordersInput);
            Map<Menu, Integer> menuInfos = new HashMap<>();
            for (String menuName : parsedMenuInfos.keySet()) {
                Menu menu = Menu.findMenuByName(menuName);
                menuInfos.put(menu, parsedMenuInfos.get(menuName));
            }
            return menuInfos;
        } catch (IllegalStateException e) { // 메뉴판에 없는 메뉴 입력
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateOrderMenus(Map<Menu, Integer> menuInfos) {
        for (Menu menu : menuInfos.keySet()) {//음료만 주문
            if (!MenuType.matches(MenuType.BEVERAGE, menu)) {
                return;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    private static void validateOrdersPriceAndAmount(Map<Menu, Integer> menuInfos) {
        int totalOrderAmount = 0;
        for (Menu menu : menuInfos.keySet()) {
            totalOrderAmount += menuInfos.get(menu);
        }
        if (totalOrderAmount > 20) { //주문 개수 20개 초과
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static Map<String, Integer> parseOrdersInput(String ordersInput) {
        Map<String, Integer> orders = new HashMap<>();
        String[] parsedOrders = ordersInput.split(",");
        for (String parsedOrder : parsedOrders) {
            String[] orderInfo = parsedOrder.split("-");
            String orderMenu = orderInfo[0];
            int orderAmount = Integer.parseInt(orderInfo[1]);
            if (orders.containsKey(orderMenu)) { //중복된 메뉴명 입력
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            orders.put(orderMenu, orderAmount);
        }
        return orders;
    }

    public int getDate() {
        return date;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
