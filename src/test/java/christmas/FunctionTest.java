package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.benefit.BenefitHistories;
import christmas.model.benefit.DiscountPolicy;
import christmas.model.benefit.GiftPolicy;
import christmas.model.reservation.Order;
import christmas.model.reservation.Reservation;
import christmas.repository.MenuRepository;
import christmas.repository.MenuRepositoryImpl;
import christmas.service.BenefitService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FunctionTest {
    private final MenuRepository menuRepository = new MenuRepositoryImpl();
    private final BenefitService benefitService = new BenefitService(menuRepository);

    @Test
    void 스페셜할인과_크리스마스할인과_평일할인_증정이벤트_중복_테스트() {

        Order order1 = new Order(menuRepository.findByName("티본스테이크"), 1);
        Order order2 = new Order(menuRepository.findByName("바비큐립"), 1);
        Order order3 = new Order(menuRepository.findByName("초코케이크"), 2);
        Order order4 = new Order(menuRepository.findByName("제로콜라"), 1);

        Reservation reservation = Reservation.from(3, new ArrayList<>(List.of(order1, order2, order3, order4)));
        BenefitHistories benefitHistories = benefitService.applyBenefit(reservation);
        assertThat(benefitHistories.getDiscountHistories().size()).isEqualTo(3);
        assertThat(benefitHistories.getGiftHistories().size()).isEqualTo(1);
        assertThat(benefitHistories.getDiscountHistories().get(0).getDiscountPolicy()).isEqualTo(
                DiscountPolicy.CHRISTMAS_DISCOUNT);
        assertThat(benefitHistories.getDiscountHistories().get(0).getDiscountAmount()).isEqualTo(1200);
        assertThat(benefitHistories.getGiftHistories().get(0).getGiftPolicy()).isEqualTo(GiftPolicy.FREE_CHAMPAGNE);
        assertThat(benefitHistories.getGiftHistories().get(0).getAmount()).isEqualTo(1);

    }

    @Test
    void 만원_미만_혜택_없음_테스트() {
        Order order1 = new Order(menuRepository.findByName("타파스"), 1);
        Order order2 = new Order(menuRepository.findByName("제로콜라"), 1);

        Reservation reservation = Reservation.from(26, new ArrayList<>(List.of(order1, order2)));
        BenefitHistories benefitHistories = benefitService.applyBenefit(reservation);

        assertThat(benefitHistories.getDiscountHistories()).isEmpty();
        assertThat(benefitHistories.getGiftHistories()).isEmpty();
    }
}
