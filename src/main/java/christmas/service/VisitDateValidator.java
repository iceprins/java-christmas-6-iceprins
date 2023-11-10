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

    private static void isRightRange(String input) {
        int changedInput = Integer.parseInt(input);
        if (changedInput < 1 || changedInput > 31) {
            throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.INVALID_DATE);
        }
    }

    public boolean validateAll(String input) {
        boolean check = true;
        try {
            isNothing(input);
            isDigit(input);
            isRightRange(input);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }
        return check;
    }
}
