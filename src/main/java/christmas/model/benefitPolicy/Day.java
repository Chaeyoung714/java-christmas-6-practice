package christmas.model.benefitPolicy;

public enum Day {
    SUNDAY(3),
    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(0),
    FRIDAY(1),
    SATURDAY(2);

    private final int remainderOfSeven;

    Day(int remainderOfSeven) {
        this.remainderOfSeven = remainderOfSeven;
    }

    public static boolean isWeekend(int date) {
        int remainderOfSevenOfDate = date % 7;
        return remainderOfSevenOfDate == 1 || remainderOfSevenOfDate == 2;
    }
}
