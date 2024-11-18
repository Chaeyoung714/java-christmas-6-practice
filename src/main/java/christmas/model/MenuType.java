package christmas.model;

public enum MenuType {
    APPETIZER,
    MAIN,
    DESSERT,
    BEVERAGE;

    public static boolean matches(MenuType menuType, Menu menu) {
        return menu.getType().equals(menuType);
    }
}
