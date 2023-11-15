package christmas.view;

import christmas.service.OutputViewConstant;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printWelcome() {
        System.out.println(OutputViewConstant.WELCOME_MSG);
    }

    public void printPreviewMessage(int date) {
        System.out.printf(OutputViewConstant.PREVIEW_MSG + System.lineSeparator(), date);
    }

    public void printMenu(Map<String, Integer> orderPair) {
        System.out.println(OutputViewConstant.ORDERED_MENU_TITLE);
        for (Map.Entry<String, Integer> pair : orderPair.entrySet()) {
            System.out.printf(OutputViewConstant.MENU_UNIT + System.lineSeparator(), pair.getKey(), pair.getValue());
        }
        System.out.print(System.lineSeparator());
    }

    public void printTotalBeforeDc(String sum) {
        System.out.println(OutputViewConstant.TOTAL_BEFORE_DC_TITLE);
        System.out.printf(OutputViewConstant.MONEY_UNIT + System.lineSeparator(), sum);
    }

    public void printPresentMenu(String presentMenu) {
        System.out.println(OutputViewConstant.PRESENT_MENU_TITLE);
        System.out.println(presentMenu + System.lineSeparator());
    }
}
