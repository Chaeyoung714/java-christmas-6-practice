package christmas.model;

import java.util.Comparator;
import java.util.List;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    ;

    private final String name;
    private final int limit;

    Badge(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    public static Badge findBadge(int totalBenefitAmount) {
        List<Badge> badgesInDescendingOrder = List.of(Badge.values())
                .stream()
                .sorted(Comparator.comparing(Badge::getLimit).reversed())
                .toList();
        for (Badge badge : badgesInDescendingOrder) {
            if (totalBenefitAmount >= badge.limit) {
                return badge;
            }
        }
        throw new IllegalStateException("[SYSTEM] Benefit giftAmount value is under zero");
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }
}
