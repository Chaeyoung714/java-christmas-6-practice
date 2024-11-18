package christmas.dto;

import christmas.model.Order;
import christmas.model.Reservation;
import java.util.ArrayList;
import java.util.List;

public record OrderDtos(List<OrderDto> dtos, int totalPrice) {

    public static OrderDtos of(Reservation reservation) {
        List<OrderDto> dtos = new ArrayList<>();
        for (Order order : reservation.getOrders()) {
            dtos.add(new OrderDto(order.getMenu().getName(), order.getAmount()));
        }
        return new OrderDtos(dtos, reservation.calculateTotalPrice());
    }

    public record OrderDto(String menu, int amount) {
    }
}
