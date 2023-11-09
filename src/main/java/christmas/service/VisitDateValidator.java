package christmas.service;

public class VisitDateValidator {
    private static void isNothing(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.NOTHING_DATE);
        }
    }
}
