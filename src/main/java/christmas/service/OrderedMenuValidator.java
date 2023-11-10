package christmas.service;

import christmas.model.Menu;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static void isInMenu(List<String> menus, List<String> input) {
        for (String s : input) {
            List<String> temp = Arrays.asList(s.split("-"));
            if (!menus.contains(temp.get(0))) {
                throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.INVALID_MENU);
            }
        }
    }

    private static void isOnlyBeverage(List<String> input) {
        List<String> beverages = getBeverage();
        boolean check = false;
        for (String s : input) {
            List<String> temp = Arrays.asList(s.split("-"));
            if (!beverages.contains(temp.get(0))) {
                check = true;
                break;
            }
        }
        if (!check) {
            throw new IllegalArgumentException(Constant.ERROR_MESSAGE + Constant.ONLY_BEVERAGE);
        }
    }

    private static List<String> getBeverage() {
        Menu[] menus = (Menu.values());
        List<String> beverages = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getType().equals("beverage")) {
                beverages.add(menu.getMenuName());
            }
        }
        return beverages;
    }
}
