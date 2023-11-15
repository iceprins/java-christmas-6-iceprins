package christmas.controller;

import christmas.model.Discount;
import christmas.model.VisitDate;
import christmas.model.Order;
import christmas.service.OrderedMenuValidator;
import christmas.service.VisitDateValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.text.DecimalFormat;
import java.util.*;

public class EventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    VisitDateValidator visitDateValidator = new VisitDateValidator();
    OrderedMenuValidator orderedMenuValidator = new OrderedMenuValidator();
    VisitDate visitDate;
    Order order;

    public void start() {
        handleInput();
        printEventResult();
    }

    private String saveVisitDate() {
        outputView.printWelcome();
        String visitDate = null;
        boolean check = false;
        while (!check) {
            visitDate = inputView.readVisitDate();
            check = visitDateValidator.validateAll(visitDate);
        }
        return visitDate;
    }

    private List<String> saveOrderedMenu() {
        List<String> orderedMenu = new ArrayList<>();
        boolean check = false;
        while (!check) {
            orderedMenu = inputView.readOrder();
            check = orderedMenuValidator.validateAll(orderedMenu);
        }
        return orderedMenu;
    }

    private Map<String, Integer> generateOrderPair(List<String> orders) {
        Map<String, Integer> orderPair = new HashMap<>();
        for (String pair : orders) {
            List<String> temp = new ArrayList<>(Arrays.asList(pair.split("-")));
            orderPair.put(temp.get(0), Integer.parseInt(temp.get(1)));
        }
        return orderPair;
    }

    private void handleInput() {
        visitDate = new VisitDate(Integer.parseInt(saveVisitDate()));
        order = new Order(generateOrderPair(saveOrderedMenu()));

        visitDate.applyBenefit(order.getOrders(), order.getTotalBeforeDc());
        outputView.printPreviewMessage(visitDate.getDate());
    }

    private String getDecimalFormat(int number) {
        DecimalFormat df = new DecimalFormat();
        return df.format(number);
    }

    private void printEventResult() {
        outputView.printMenu(order.getOrders());
        outputView.printTotalBeforeDc(getDecimalFormat(order.getTotalBeforeDc()));
        outputView.printPresentMenu(Discount.getPresentMenu());
        outputView.printBenefitHistory(Discount.getBenefitHistory());
        outputView.printTotalBenefit(getDecimalFormat(-Discount.getTotalBenefit()));
        outputView.printTotalAfterDc(getDecimalFormat(order.getTotalAfterDc()));
        outputView.printEventBadge(visitDate.getBadge(Discount.getTotalBenefit()));
    }
}
