package christmas.service;

import java.util.List;
import java.util.regex.Pattern;

public class OrderedMenuValidator {
    private static void isNothing(List<String> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.NOTHING_MENU);
        }
    }

    public static void isRightFormat(List<String> input) {
        String std = "(^[가-힣]*)-[0-9]{1,2}";
        for (int i = 0; i < input.size(); i++) {
            if (!Pattern.matches(std, input.get(i))) {
                throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.INVALID_MENU);
            }
        }
    }
}
