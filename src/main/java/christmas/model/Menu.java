package christmas.model;

public class Menu {
    private final String name;
    private final int price;

    public MenuType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    private final MenuType type;

    public Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }


}
