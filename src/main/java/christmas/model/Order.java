package christmas.model;

public class Order {
    private final Menu menu;
    private final int amount;

    public Order(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }
}
