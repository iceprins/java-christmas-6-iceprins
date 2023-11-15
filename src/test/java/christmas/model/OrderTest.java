package christmas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @BeforeEach
    void setUp() {
        for (Discount discount : Discount.values()) {
            discount.setAmount(0);
        }
    }

    @DisplayName("Order 객체의 orders 값에 따라 할인 전 총주문 금액 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider1")
    void getTotalBeforeDcTest(Map<String, Integer> input1, int expected) {
        Order order = new Order(input1);
        int test = order.getTotalBeforeDc();
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider1() {
        return Stream.of(
                Arguments.of(Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1), 142000),
                Arguments.of(Map.of("타파스", 1,"제로콜라", 1), 8500),
                Arguments.of(Map.of("해산물파스타", 1,"아이스크림", 2,"레드와인", 1), 105000)
        );
    }

    @DisplayName("Order 객체의 orders 값에 따라 주문한 디저트의 개수 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider2")
    void getNumberOfDessertTest(Map<String, Integer> input1, int expected) {
        int test = Order.getNumberOfDessert(input1);
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider2() {
        return Stream.of(
                Arguments.of(Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1), 2),
                Arguments.of(Map.of("타파스", 1,"제로콜라", 1), 0),
                Arguments.of(Map.of("해산물파스타", 1,"아이스크림", 2,
                        "레드와인", 1, "초코케이크", 3), 5)
        );
    }

    @DisplayName("Order 객체의 orders 값에 따라 주문한 디저트의 개수 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider3")
    void getNumberOfMainTest(Map<String, Integer> input1, int expected) {
        int test = Order.getNumberOfMain(input1);
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider3() {
        return Stream.of(
                Arguments.of(Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1), 2),
                Arguments.of(Map.of("타파스", 1,"제로콜라", 1), 0),
                Arguments.of(Map.of("해산물파스타", 1,"아이스크림", 2,
                        "레드와인", 1, "초코케이크", 3), 1)
        );
    }

    @DisplayName("할인 후 예상 결제 금액 구하기")
    @ParameterizedTest
    @MethodSource("parameterProvider4")
    void getTotalAfterDcTest(VisitDate visitDate, Map<String, Integer> input1, int expected) {
        Order order = new Order(input1);
        visitDate.applyBenefit(input1, order.getTotalBeforeDc());

        int test = order.getTotalAfterDc();
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider4() {
        return Stream.of(
                Arguments.of(new VisitDate(3), Map.of("티본스테이크", 1,"바비큐립", 1,
                        "초코케이크", 2,"제로콜라", 1), 135754),
                Arguments.of(new VisitDate(26), Map.of("타파스", 1,"제로콜라", 1), 8500),
                Arguments.of(new VisitDate(5), Map.of("해산물파스타", 1,"아이스크림", 2,
                        "레드와인", 1, "초코케이크", 3), 138485)
        );
    }
}