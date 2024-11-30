package christmas.service;

import christmas.model.reservation.Menu;
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

    /**
     * 검증 단계 필요함
     */
    public Reservation registerOrders(int date, Map<String, Integer> ordersInput) {
        List<Order> orders = new ArrayList<>();
        for (String menuName : ordersInput.keySet()) {
            Menu menu = menuRepository.findByName(menuName);
            orders.add(new Order(menu, ordersInput.get(menuName)));
        }
        return new Reservation(date, orders);
    }
}
