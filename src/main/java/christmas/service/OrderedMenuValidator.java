package christmas.service;

import java.util.List;
import java.util.regex.Pattern;

public class OrderedMenuValidator {
    private static void isNothing(List<String> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.NOTHING_MENU);
        }
    }

    private static void isRightFormat(List<String> input) {
        String std = "(^[가-힣]*)-[0-9]{1,2}";
        for (String s : input) {
            if (!Pattern.matches(std, s)) {
                throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.INVALID_MENU);
            }
        }
    }
}
