package christmas.model;

public enum Day {
    SUNDAY(3),
    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(0),
    FRIDAY(1),
    SATURDAY(2),
    ;

    private final int startDateOnDecember;

    Day(int startDateOnDecember) {
        this.startDateOnDecember = startDateOnDecember;
    }

    public static boolean isWeekendOn(int date) {
        Day day = findDayOn(date);
        return day.equals(FRIDAY) || day.equals(SATURDAY);
    }

    public static boolean isWeekdayOn(int date) {
        return !isWeekendOn(date);
    }

    private static Day findDayOn(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalStateException("[SYSTEM] Invalid date");
        }
        int dateRemainder = date % 7;
        for (Day day : Day.values()) {
            if (day.startDateOnDecember == dateRemainder) {
                return day;
            }
        }
        throw new IllegalStateException("[SYSTEM] No such date on December");
    }
}
