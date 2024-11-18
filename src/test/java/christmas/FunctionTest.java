package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.Reservation;
import christmas.view.InputValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FunctionTest {

    @ParameterizedTest
    @ValueSource(strings = {"테스트메뉴-1&테스트메뉴뉴-2", "테스트메뉴~1,테스트메뉴뉴-2", "테스트메뉴-1,", "테스트메뉴-,테스트메뉴-2",
            "테스트메뉴- 1,테스트메뉴-2"})
    void 메뉴_주문시_제시된_형식_지키지_않으면_예외(String wrongMenuInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidator.validateMenusInput(wrongMenuInput))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"테스트메뉴-0", "테스트메뉴--1, 테스트메뉴-0.5"})
    void 메뉴_주문시_메뉴_개수가_1이상이_아니면_예외(String wrongMenuAmountInput) {
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidator.validateMenusInput(wrongMenuAmountInput))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 주문_정보_저장() {
        String testDate = "1";
        String testOrderString = "양송이수프-3,티본스테이크-2,초코케이크-1,제로콜라-4";

        Reservation reservation = Reservation.from(testDate, testOrderString);
        int date = reservation.getDate();
        List<Order> testOrders = reservation.getOrders();

        Map<Menu, Integer> targetOrders = new HashMap<>();
        targetOrders.put(Menu.MUSHROOM_SOUP, 3);
        targetOrders.put(Menu.TBONE_STEAK, 2);
        targetOrders.put(Menu.CHOCOLATE_CAKE, 1);
        targetOrders.put(Menu.ZERO_COKE, 4);

        assertThat(date).isEqualTo(1);
        assertThat(testOrders.size()).isEqualTo(4);
        for (int i = 0; i < 4; i++) {
            Order testOrder = testOrders.get(i);
            assertThat(targetOrders.keySet()).contains(testOrder.getMenu());
            assertThat(targetOrders.get(testOrder.getMenu())).isEqualTo(testOrder.getAmount());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"논제로콜라-3", "제로콜라 -3", "제로 콜라-3"})
    void 메뉴판에_없는_메뉴_주문시_예외(String wrongOrder) {
        assertThatIllegalArgumentException().isThrownBy(() -> Reservation.from("1", wrongOrder))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 음료만_주문시_예외() {
        String wrongOrder = "제로콜라-3,레드와인-2,샴페인-1";
        assertThatIllegalArgumentException().isThrownBy(() -> Reservation.from("1", wrongOrder))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 주문_개수_제한_초과시_예외() {
        String wrongOrder = "제로콜라-3,초코케이크-8,티본스테이크-10";
        assertThatIllegalArgumentException().isThrownBy(() -> Reservation.from("1", wrongOrder))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 총주문금액_제한_미만시_예외() {
        String wrongOrder = "타파스-1";
        assertThatIllegalArgumentException().isThrownBy(() -> Reservation.from("1", wrongOrder))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 중복메뉴_입력시_예외() {
        String wrongOrder = "제로콜라-3,레드와인-2,제로콜라-1";
        assertThatIllegalArgumentException().isThrownBy(() -> Reservation.from("1", wrongOrder))
                .withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
