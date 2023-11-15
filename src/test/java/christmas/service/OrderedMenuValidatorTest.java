package christmas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OrderedMenuValidatorTest {
    private OrderedMenuValidator orderedMenuValidator;

    @BeforeEach
    void setUp() {
        orderedMenuValidator = new OrderedMenuValidator();
    }

    @DisplayName("주문 입력값 예외 처리 테스트")
    @ParameterizedTest
    @MethodSource("parameterProvider")
    public void validateAllTest(List<String> input1, boolean expected) {
        boolean test = orderedMenuValidator.validateAll(input1);
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider() {
        return Stream.of(
                // 입력값이 없는 케이스
                Arguments.of(List.of(""), false),
                // 메뉴의 개수가 1 이상의 숫자가 아닌 케이스
                Arguments.of(List.of("티본스테이크-a,바비큐립-1,초코케이크-2,제로콜라-1"), false),
                Arguments.of(List.of("티본스테이크-1,바비큐립-1,초코케이크-0,제로콜라-1"), false),
                // 메뉴판에 없는 메뉴를 입력한 케이스
                Arguments.of(List.of("티본스테이크-1,바비큐립-1,짜장면-2,제로콜라-1"), false),
                Arguments.of(List.of("티본스테이크-1,오므라이스-1,타파스-2,제로콜라-1"), false),
                // 주문한 메뉴가 음료뿐인 케이스
                Arguments.of(List.of("제로콜라-1,레드와인-2"), false),
                // 주문한 메뉴의 개수가 20개를 초과한 케이스
                Arguments.of(List.of("티본스테이크-4,바비큐립-6,타파스-5,초코케이크-7,제로콜라-3"), false),
                // 입력한 메뉴 형식이 예시와 다른 케이스
                Arguments.of(List.of("티본스테이크1,바비큐립-1,초코케이크-2,제로콜라-1"), false),
                Arguments.of(List.of("티본스테이크-1,바비큐립-1,초코케이크 2,제로콜라-1"), false),
                Arguments.of(List.of("티본스테이크-1, 바비큐립-1,초코케이크-2,제로콜라-1"), false),
                // 중복 메뉴를 입력한 케이스
                Arguments.of(List.of("티본스테이크-1,바비큐립-1,티본스테이크-2,제로콜라-1"), false)
        );
    }
}