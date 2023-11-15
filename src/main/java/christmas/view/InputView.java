package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.service.InputViewConstant;

import java.util.Arrays;
import java.util.List;

public class InputView {
    public String readVisitDate() {
        System.out.println(InputViewConstant.READ_VISIT_DATE);
        return Console.readLine();
    }

    public List<String> readOrder() {
        System.out.println(InputViewConstant.READ_ORDER);
        return Arrays.asList(Console.readLine().split(","));
    }
}
