package christmas.model.benefit;

import christmas.model.reservation.MenuType;
import christmas.model.reservation.Reservation;
import java.util.ArrayList;
import java.util.List;

public enum DiscountPolicy {
    CHRISTMAS_DISCOUNT(1, 25, 1000),
    WEEKDAY_DISCOUNT(1, 31, 2023),
    WEEKEND_DISCOUNT(1, 31, 2023),
    SPECIAL_DISCOUNT(1, 23, 1000),
    ;

    private static final List<Integer> specicalDates = new ArrayList<>(
            List.of(3, 10, 17, 24, 25, 31)
    );

    private final int startDate;
    private final int endDate;
    private final int defaultDiscountAmount;

    DiscountPolicy(int startDate, int endDate, int defaultDiscountAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.defaultDiscountAmount = defaultDiscountAmount;
    }

    public boolean isAvailable(int date) {
        return (isOngoing(date) && matchesDate(date));
    }

    private boolean matchesDate(int date) {
        if (this.equals(CHRISTMAS_DISCOUNT)) {
            return isOngoing(date);
        }
        if (this.equals(WEEKDAY_DISCOUNT)) {
            return !Day.isWeekend(date);
        }
        if (this.equals(WEEKEND_DISCOUNT)) {
            return Day.isWeekend(date);
        }
        if (this.equals(SPECIAL_DISCOUNT)) {
            return specicalDates.contains(date);
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 이름의 할인이 없습니다.");
    }

    private boolean isOngoing(int date) {
        return date >= startDate && date <= endDate;
    }

    /**
     * 의존성 끊으면 좋을 것 같음
     */
    public int calculateDiscountAmount(Reservation reservation) {
        if (this.equals(CHRISTMAS_DISCOUNT)) {
            return this.defaultDiscountAmount + (100 * (reservation.getDate() - 1));
        }
        if (this.equals(WEEKDAY_DISCOUNT)) {
            return this.defaultDiscountAmount * reservation.calculateOrderAmountOf(MenuType.DESSERT);
        }
        if (this.equals(WEEKEND_DISCOUNT)) {
            return this.defaultDiscountAmount * reservation.calculateOrderAmountOf(MenuType.MAIN);
        }
        if (this.equals(SPECIAL_DISCOUNT)) {
            return this.defaultDiscountAmount;
        }
        throw new IllegalStateException("[SYSTEM] 해당하는 이름의 할인이 없습니다.");
    }
}
