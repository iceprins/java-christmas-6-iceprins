package christmas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class VisitDateTest {
    @BeforeEach
    void setUp() {
        for (Discount discount : Discount.values()) {
            discount.setAmount(0);
        }
    }

    @DisplayName("입력값에 따라 할인 금액을 계산해서 Discount Enum 클래스의 각 상수의 amount 값 업데이트")
    @ParameterizedTest
    @MethodSource("parameterProvider")
    void applyBenefitTest(VisitDate visitDate, Map<String, Integer> input1,
                                 int input2, List<Integer> expected) {
        visitDate.applyBenefit(input1, input2);

        List<Integer> test = new ArrayList<>();

        for (Discount discount : Discount.values()) {
            test.add(discount.getAmount());
        }
        assertThat(test).isEqualTo(expected);
    }

    static Stream<Arguments> parameterProvider() {
        return Stream.of(
                Arguments.of(new VisitDate(3), Map.of("티본스테이크", 1,"바비큐립",
                        1,"초코케이크", 2,"제로콜라", 1), 142000,
                        Arrays.asList(1200, 4046, 0, 1000, 25000)),
                Arguments.of(new VisitDate(26), Map.of("타파스", 1,"제로콜라", 1), 8500,
                        Arrays.asList(0, 0, 0, 0, 0)),
                Arguments.of(new VisitDate(25), Map.of("해산물파스타", 1,"아이스크림",
                                2,"레드와인", 1), 105000,
                        Arrays.asList(3400, 4046, 0, 1000, 0))
        );
    }

    @DisplayName("총 혜택 금액에 따라 배지 차등 부여")
    @ParameterizedTest
    @CsvSource({"3000, ", "4999, ", "5000, 별", "9999, 별", "10000, 트리",
            "16000, 트리", "19999, 트리", "20000, 산타", "50000, 산타"})
    void getBadgeTest(int input, String expected) {
        VisitDate visitDate = new VisitDate(1);
        String test = visitDate.getBadge(input);
        assertThat(test).isEqualTo(expected);
    }

}