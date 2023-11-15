package christmas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountTest {
    @BeforeEach
    void setUp() {
        for (Discount discount : Discount.values()) {
            discount.setAmount(0);
        }
    }

    @DisplayName("총 혜택 금액 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider1")
    void getTotalBenefitTest(VisitDate visitDate, Order order, int expected) {
        visitDate.applyBenefit(order.getOrders(), order.getTotalBeforeDc());

        int test = Discount.getTotalBenefit();
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider1() {
        return Stream.of(
                Arguments.of(new VisitDate(3), new Order(Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1)), 31246),
                Arguments.of(new VisitDate(26), new Order(Map.of("타파스", 1,"제로콜라", 1)),
                        0),
                Arguments.of(new VisitDate(5), new Order(Map.of("해산물파스타", 1,"아이스크림", 2,
                        "레드와인", 1)), 5446)
        );
    }

    @DisplayName("총 할인 금액 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider2")
    void getTotalDiscountTest(VisitDate visitDate, Order order, int expected) {
        visitDate.applyBenefit(order.getOrders(), order.getTotalBeforeDc());

        int test = Discount.getTotalDiscount();
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider2() {
        return Stream.of(
                Arguments.of(new VisitDate(3), new Order(Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1)), 6246),
                Arguments.of(new VisitDate(26), new Order(Map.of("타파스", 1,"제로콜라", 1)),
                        0),
                Arguments.of(new VisitDate(5), new Order(Map.of("해산물파스타", 1,"아이스크림", 2,
                        "레드와인", 1)), 5446)
        );
    }

    @DisplayName("혜택 내역 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider3")
    void getBenefitHistoryTest(VisitDate visitDate, Order order, List<String> expected) {
        visitDate.applyBenefit(order.getOrders(), order.getTotalBeforeDc());

        List<String> test = Discount.getBenefitHistory();
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider3() {
        return Stream.of(
                Arguments.of(new VisitDate(3), new Order(Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1)), Arrays.asList("크리스마스 디데이 할인: -1,200원",
                        "평일 할인: -4,046원", "특별 할인: -1,000원", "증정 이벤트: -25,000원")),
                Arguments.of(new VisitDate(26), new Order(Map.of("타파스", 1,"제로콜라", 1)),
                        Arrays.asList("없음")),
                Arguments.of(new VisitDate(5), new Order(Map.of("해산물파스타", 1,"아이스크림", 2,
                        "레드와인", 1)), Arrays.asList("크리스마스 디데이 할인: -1,400원", "평일 할인: -4,046원"))
        );
    }

    @DisplayName("총혜택 금액에 따라 부여되는 증정 메뉴 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider4")
    void getPresentMenuTest(VisitDate visitDate, Order order, String expected) {
        visitDate.applyBenefit(order.getOrders(), order.getTotalBeforeDc());

        String test = Discount.getPresentMenu();
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider4() {
        return Stream.of(
                Arguments.of(new VisitDate(3), new Order(Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1)), "샴페인 1개"),
                Arguments.of(new VisitDate(26), new Order(Map.of("타파스", 1,"제로콜라", 1)),
                        "없음"),
                Arguments.of(new VisitDate(5), new Order(Map.of("해산물파스타", 1,"아이스크림", 2,
                        "레드와인", 1)), "없음")
        );
    }
}