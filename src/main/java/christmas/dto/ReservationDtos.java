package christmas.dto;

import christmas.model.reservation.Order;
import christmas.model.reservation.Reservation;
import java.util.ArrayList;
import java.util.List;

public record ReservationDtos(List<ReservationDto> reservations, int date, int totalOrderAmount) {

    public static ReservationDtos of(Reservation reservation) {
        List<ReservationDto> dtos = new ArrayList<>();
        for (Order order : reservation.getOrders()) {
            dtos.add(new ReservationDto(order.getMenuName(), order.getAmount()));
        }
        return new ReservationDtos(dtos, reservation.getDate(), reservation.getTotalOrderAmount());
    }

    public record ReservationDto(String menuName, int amount) {
    }
}
