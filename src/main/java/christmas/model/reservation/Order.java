package christmas.model.reservation;

public class Order {
    private final Menu menu;
    private final int amount;

    public Order(Menu menu, int amount) {
        this.menu = menu;
        this.amount = amount;
    }

    public boolean matchesMenuType(MenuType menuType) {
        return menu.getType().equals(menuType);
    }

    public int getMenuPrice() {
        return menu.getPrice();
    }

    public Menu getMenu() {
        return menu;
    }

    public int getAmount() {
        return amount;
    }
}
