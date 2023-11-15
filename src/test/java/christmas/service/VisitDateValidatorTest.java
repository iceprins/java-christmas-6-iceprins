package christmas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class VisitDateValidatorTest {
    private VisitDateValidator visitDateValidator;

    @BeforeEach
    void setUp() {
        visitDateValidator = new VisitDateValidator();
    }

    @DisplayName("방문 날짜 입력값 예외 처리 테스트")
    @ParameterizedTest
    @MethodSource("parameterProvider")
    public void validateAllTest(String input1, boolean expected) {
        boolean test = visitDateValidator.validateAll(input1);
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider() {
        return Stream.of(
                // 입력값이 없는 케이스
                Arguments.of("", false),
                // 입력값이 숫자가 아닌 케이스
                Arguments.of("a", false),
                Arguments.of("!", false),
                Arguments.of(" ", false),
                // 입력값이 1 이상 31 이하가 아닌 케이스
                Arguments.of("0", false),
                Arguments.of("32", false)
        );
    }
}