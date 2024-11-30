package christmas.model;

import java.util.List;

public class Reservation {
    private final int date;
    private final List<Order> orders;

    public Reservation(int date, List<Order> orders) {
        this.date = date;
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
