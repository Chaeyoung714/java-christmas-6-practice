package christmas.model.benefit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    ;

    private final String name;
    private final int limitPrice;

    Badge(String name, int limitPrice) {
        this.name = name;
        this.limitPrice = limitPrice;
    }

    public static Optional<Badge> findBadge(int price) {
        for (Badge badge : sortByDescendingOrder()) {
            if (badge.limitPrice <= price) {
                return Optional.of(badge);
            }
        }
        return Optional.empty();
    }

    private static List<Badge> sortByDescendingOrder() {
        return Arrays.stream(Badge.values())
                .sorted(Comparator.comparing(Badge::getLimitPrice).reversed())
                .toList();
    }

    public int getLimitPrice() {
        return limitPrice;
    }

    public String getName() {
        return name;
    }
}
