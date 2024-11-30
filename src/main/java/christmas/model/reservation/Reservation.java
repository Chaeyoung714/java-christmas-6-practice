package christmas.model.reservation;

import java.util.List;

public class Reservation {
    private final int date;
    private final List<Order> orders;

    public Reservation(int date, List<Order> orders) {
        this.date = date;
        this.orders = orders;
    }

    public int calculateTotalPrice() {
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

    public int getDate() {
        return date;
    }
}
