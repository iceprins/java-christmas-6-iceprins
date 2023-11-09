package christmas.service;

public class VisitDateValidator {
    private static void isNothing(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.NOTHING_DATE);
        }
    }

    private static void isDigit(String input) {
        for (int i = 0; i < input.length(); i++) {
            char target = input.charAt(i);
            if (!Character.isDigit(target)) {
                throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.NOT_DIGIT);
            }
        }
    }
}
