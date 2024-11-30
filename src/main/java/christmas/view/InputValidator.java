package christmas.view;

public class InputValidator {
    public static void validateDate(String dateInput) {
        try {
            int date = Integer.parseInt(dateInput);
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
