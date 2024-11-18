package christmas.model;

import java.util.ArrayList;
import java.util.List;

public enum DiscountPolicy {
    CHIRSTMAS_DISCOUNT("크리스마스 디데이 할인", 1000),
    WEEKDAY_DISCOUNT("평일 할인", 2023),
    WEEKEND_DISCOUNT("주말 할인", 2023),
    SPECIAL_DISCOUNT("특별 할인", 1000),
    ;

    private static final List<Integer> specialDiscountDates = new ArrayList<>(List.of(
            3, 10, 17, 24, 25, 31
    ));

    private final String name;
    private final int defaultDiscountAmount;

    DiscountPolicy(String name, int defaultDiscountAmount) {
        this.name = name;
        this.defaultDiscountAmount = defaultDiscountAmount;
    }

    public static boolean isAvailable(int date, DiscountPolicy discountPolicy) {
        if (discountPolicy.equals(CHIRSTMAS_DISCOUNT)) {
            return (date >= 1 && date <= 25);
        }
        if (discountPolicy.equals(WEEKDAY_DISCOUNT)) {
            return Day.isWeekdayOn(date);
        }
        if (discountPolicy.equals(WEEKEND_DISCOUNT)) {
            return Day.isWeekendOn(date);
        }
        if (discountPolicy.equals(SPECIAL_DISCOUNT)) {
            return specialDiscountDates.contains(date);
        }
        throw new IllegalStateException("[SYSTEM] No such discount policy");
    }

    public int getDefaultDiscountAmount() {
        return defaultDiscountAmount;
    }

    public String getName() {
        return name;
    }
}
