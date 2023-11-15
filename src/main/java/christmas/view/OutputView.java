package christmas.view;

import christmas.service.OutputViewConstant;

public class OutputView {
    public void printWelcome() {
        System.out.println(OutputViewConstant.WELCOME_MSG);
    }

    public void printPreviewMessage(int date) {
        System.out.printf(OutputViewConstant.PREVIEW_MSG + System.lineSeparator(), date);
    }
}
