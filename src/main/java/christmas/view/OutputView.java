package christmas.view;

import christmas.model.benefit.Badge;
import christmas.model.benefit.BenefitHistories;
import christmas.model.reservation.Order;
import christmas.model.reservation.Reservation;
import java.util.List;
import java.util.Optional;

public class OutputView {
    public void printResult(Reservation reservation, BenefitHistories benefitHistories, Optional<Badge> badge) {
        printStartLine(reservation.getDate());
        printOrders(reservation.getOrders());
        printBenefitHistories(benefitHistories);
        printPrice(benefitHistories);
        printBadge(badge);
    }

    private void printStartLine(int date) {
    }

    private void printOrders(List<Order> orders) {
        
    }

    private void printBenefitHistories(BenefitHistories benefitHistories) {
        
    }

    private void printPrice(BenefitHistories benefitHistories) {
        
    }

    private void printBadge(Optional<Badge> badge) {
        
    }


}
