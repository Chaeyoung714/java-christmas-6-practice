package christmas.model.reservation;

import java.util.List;
import org.mockito.internal.matchers.Or;

public class Reservation {
    private final int date;
    private final List<Order> orders;
    private final int totalOrderAmount;

    private Reservation(int date, List<Order> orders, int totalOrderAmount) {
        this.date = date;
        this.orders = orders;
        this.totalOrderAmount = totalOrderAmount;
    }

    public static Reservation from(int date, List<Order> orders) {
        return new Reservation(date, orders, calculateTotalPrice(orders));
    }

    private static int calculateTotalPrice(List<Order> orders) {
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += order.getAmount() * order.getMenuPrice();
        }
        return totalPrice;
    }

    public int calculateOrderAmountOf(MenuType menuType) {
        int totalAmount = 0;
        for (Order order : orders) {
            if (order.matchesMenuType(menuType)) {
                totalAmount += order.getAmount();
            }
        }
        return totalAmount;
    }

    public boolean hasOrderMatchingMenuType(MenuType menuType) {
        return calculateOrderAmountOf(menuType) > 0;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getDate() {
        return date;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }
}
