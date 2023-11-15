package christmas.service;

import christmas.model.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class OrderedMenuValidator {
    private static void isNothing(List<String> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(InputViewConstant.ERROR_MESSAGE + InputViewConstant.INVALID_MENU);
        }
    }

    private static void isRightFormat(List<String> input) {
        String std = "(^[가-힣]*)-[0-9]{1,2}";
        for (String s : input) {
            if (!Pattern.matches(std, s)) {
                throw new IllegalArgumentException(InputViewConstant.ERROR_MESSAGE + InputViewConstant.INVALID_MENU);
            }
        }
    }

    private static void isInMenu(List<String> input) {
        List<String> menus = Menu.getMenuNameSet();
        for (String s : input) {
            List<String> pair = Arrays.asList(s.split("-"));
            if (!menus.contains(pair.get(0))) {
                throw new IllegalArgumentException(InputViewConstant.ERROR_MESSAGE + InputViewConstant.INVALID_MENU);
            }
        }
    }

    private static void isOnlyBeverage(List<String> input) {
        List<String> beverages = Menu.getBeverageNameSet();
        boolean check = false;
        for (String s : input) {
            List<String> pair = Arrays.asList(s.split("-"));
            if (!beverages.contains(pair.get(0))) {
                check = true;
                break;
            }
        }
        if (!check) {
            throw new IllegalArgumentException(InputViewConstant.ERROR_MESSAGE + InputViewConstant.INVALID_MENU);
        }
    }

    private static void isOrderedOverOne(List<String> input) {
        for (String s : input) {
            List<String> pair = Arrays.asList(s.split("-"));
            if (Integer.parseInt(pair.get(1)) < 1) {
                throw new IllegalArgumentException(InputViewConstant.ERROR_MESSAGE + InputViewConstant.INVALID_MENU);
            }
        }
    }

    private static void isOrderedLessThanTwenty(List<String> input) {
        int sum = 0;
        for (String s : input) {
            List<String> pair = Arrays.asList(s.split("-"));
            sum += Integer.parseInt(pair.get(1));
        }
        if (sum > 20) {
            throw new IllegalArgumentException(InputViewConstant.ERROR_MESSAGE + InputViewConstant.INVALID_MENU);
        }
    }

    private static void isMenuDuplicated(List<String> input) {
        List<String> temp = new ArrayList<>();
        for (String s : input) {
            List<String> pair = Arrays.asList(s.split("-"));
            if (!temp.contains(pair.get(0))) {
                temp.add(pair.get(0));
                continue;
            }
            throw new IllegalArgumentException(InputViewConstant.ERROR_MESSAGE + InputViewConstant.INVALID_MENU);
        }
    }

    public boolean validateAll(List<String> input) {
        boolean check = true;
        try {
            isNothing(input);
            isRightFormat(input);
            isInMenu(input);
            isOnlyBeverage(input);
            isOrderedOverOne(input);
            isOrderedLessThanTwenty(input);
            isMenuDuplicated(input);
        } catch (IllegalArgumentException e) {
            check = false;
            System.out.println(e.getMessage());
        }
        return check;
    }
}
