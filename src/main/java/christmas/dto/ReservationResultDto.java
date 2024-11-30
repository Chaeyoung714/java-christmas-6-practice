package christmas.dto;

import christmas.model.benefit.Badge;
import christmas.model.benefit.BenefitHistories;
import christmas.model.reservation.Reservation;
import java.util.Optional;

public record ReservationResultDto(ReservationDtos reservations, BenefitHistoriesDto benefitHistories, PriceDto priceInfo, Optional<String> badgeName) {

    public static ReservationResultDto from(Reservation reservation, BenefitHistories benefitHistories, Optional<Badge> badge) {
        ReservationDtos reservationDtos = ReservationDtos.of(reservation);
        BenefitHistoriesDto benefitHistoriesDto = BenefitHistoriesDto.from(benefitHistories);
        PriceDto priceDto = PriceDto.from(reservation, benefitHistories);
        Optional<String> badgeName = findBadgeName(badge);
        return new ReservationResultDto(reservationDtos, benefitHistoriesDto, priceDto, badgeName);
    }

    private static Optional<String> findBadgeName(Optional<Badge> badge) {
        if (badge.isPresent()) {
            return Optional.of(badge.get().getName());
        }
        return Optional.empty();
    }
}
