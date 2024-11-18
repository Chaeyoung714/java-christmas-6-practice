package christmas.model;

public enum FreeGiftPolicy {
    CHAMPAGNE_FREE(Menu.CHAMPAGNE,120000);

    private static final String name = "증정 이벤트";

    private final Menu gift;
    private final int limit;

    FreeGiftPolicy(Menu gift, int limit) {
        this.gift = gift;
        this.limit = limit;
    }

    public static boolean isAvailable(int orderPrice, FreeGiftPolicy freeGiftPolicy) {
        for (FreeGiftPolicy policy : FreeGiftPolicy.values()) {
            if (policy.equals(freeGiftPolicy)) {
                if (orderPrice >= policy.limit) {
                    return true;
                }
                return false;
            }
        }
        throw new IllegalStateException("[SYSTEM] No such free gift policy");
    }

    public Menu getGift() {
        return gift;
    }

    public static String getName() {
        return name;
    }
}
