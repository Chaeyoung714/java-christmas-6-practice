package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.Badge;
import christmas.model.DiscountPolicy;
import christmas.model.Menu;
import christmas.model.Receipt;
import christmas.model.Reservation;
import christmas.service.BenefitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BenefitTest {
    private final BenefitService benefitService = new BenefitService();
    private Receipt receipt;

    @BeforeEach
    void setUp() {
        receipt = new Receipt();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:1000", "2:1100", "3:1200", "4:1300", "24:3300", "25:3400"}
            , delimiter = ':')
    void 크리스마스_디데이_할인을_적용한다(String date, int expectedDiscountAmount) {
        Reservation reservation = Reservation.from(date, "티본스테이크-1");
        benefitService.applyDiscount(receipt, reservation);

        assertThat(receipt.getDiscountHistory().get(DiscountPolicy.CHIRSTMAS_DISCOUNT))
                .isEqualTo(expectedDiscountAmount);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "5", "6", "7", "10", "11", "19", "25", "31"})
    void 평일_할인을_단독_혹은_중복_적용한다(String date) {
        String testOrder = "초코케이크-1,아이스크림-3,양송이수프-1";
        Reservation reservation = Reservation.from(date, testOrder);
        benefitService.applyDiscount(receipt, reservation);

        assertThat(receipt.getDiscountHistory().get(DiscountPolicy.WEEKDAY_DISCOUNT))
                .isEqualTo(8092);
    }

    /**
     * 예외가 안터짐
     */
    @ParameterizedTest
    @Disabled
    @ValueSource(strings = {"3", "4", "5", "6", "7", "10", "11", "19", "20", "28"})
    void 주말이_아니면_주말_할인을_적용하지_않는다(String date) {
        String testOrder = "초코케이크-1,아이스크림-3,양송이수프-1";
        Reservation reservation = Reservation.from(date, testOrder);
        benefitService.applyDiscount(receipt, reservation);

        assertThatThrownBy(() -> receipt.getDiscountHistory().get(DiscountPolicy.WEEKEND_DISCOUNT))
                .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "8", "9", "15", "16", "22", "23", "29", "30"})
    void 주말_할인을_단독_혹은_중복_적용한다(String date) {
        String testOrder = "초코케이크-1,티본스테이크-2,바비큐립-1,해산물파스타-1,양송이수프-3,";
        Reservation reservation = Reservation.from(date, testOrder);
        benefitService.applyDiscount(receipt, reservation);

        assertThat(receipt.getDiscountHistory().get(DiscountPolicy.WEEKEND_DISCOUNT))
                .isEqualTo(8092);
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "10", "17", "24", "25", "31"})
    void 특별_할인을_단독_혹은_중복_적용한다(String date) {
        Reservation reservation = Reservation.from(date, "티본스테이크-1");
        benefitService.applyDiscount(receipt, reservation);

        assertThat(receipt.getDiscountHistory().get(DiscountPolicy.SPECIAL_DISCOUNT))
                .isEqualTo(1000);
    }

    @Test
    void 샴페인_증정혜택을_적용한다() {
        String testOrder = "레드와인-1,초코케이크-4";
        Reservation reservation = Reservation.from("1", testOrder);
        benefitService.applyFreeGift(receipt, reservation);

        assertThat(receipt.getFreeGiftHistory().get(Menu.CHAMPAGNE))
                .isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource(value = {"초코케이크-2:NONE", "초코케이크-3:STAR", "초코케이크-5:TREE", "초코케이크-10:SANTA"}
            , delimiter = ':')
    void 금액에_맞는_이벤트뱃지를_반환한다(String testOrder, Badge expectedBadge) {
        String onlyWeekdayDiscountAvailableDate = "26";
        Reservation reservation = Reservation.from(onlyWeekdayDiscountAvailableDate, testOrder);
        benefitService.applyDiscount(receipt, reservation);

        Badge badge = benefitService.applyBadge(receipt);

        assertThat(badge).isEqualTo(expectedBadge);
    }
}
