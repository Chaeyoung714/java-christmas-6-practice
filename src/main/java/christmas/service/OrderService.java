package christmas.service;

import christmas.model.reservation.Menu;
import christmas.model.reservation.MenuType;
import christmas.model.reservation.Order;
import christmas.model.reservation.Reservation;
import christmas.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final MenuRepository menuRepository;

    public OrderService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Reservation registerOrders(int date, Map<String, Integer> ordersInput) {
        try {
            List<Order> orders = new ArrayList<>();
            for (String menuName : ordersInput.keySet()) {
                Menu menu = menuRepository.findByName(menuName);
                orders.add(new Order(menu, ordersInput.get(menuName)));
            }
            validateOrders(orders);
            return Reservation.from(date, orders);
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateOrders(List<Order> orders) {
        validateOrderAmountLimit(orders);
        validateOrderMenuLimit(orders);
    }

    private void validateOrderAmountLimit(List<Order> orders) {
        int totalOrderAmount = 0;
        for (Order order : orders) {
            totalOrderAmount += order.getAmount();
        }
        if (totalOrderAmount > 20) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOrderMenuLimit(List<Order> orders) {
        for (Order order : orders) {
            if (!order.matchesMenuType(MenuType.BEVERAGE)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}
