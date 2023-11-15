package christmas.model;

import christmas.service.OutputViewConstant;

import java.util.*;

public class VisitDate {
    static final int WEEK_DISCOUNT = 2023;
    static final int PRESENT_EVENT_CRITERION = 120000;
    static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    static final int PRESENT_EVENT_AMOUNT = 25000;
    static final int STAR_BASE_AMOUNT = 5000;
    static final int TREE_BASE_AMOUNT = 10000;
    static final int SANTA_BASE_AMOUNT = 20000;
    static final int EVENT_BASE_AMOUNT = 10000;

    private final int date;
    public VisitDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public void applyBenefit(Map<String, Integer> orders, int totalBeforeDc) {
        if (totalBeforeDc >= EVENT_BASE_AMOUNT) {
            calculateChristmasDiscount();
            calculateWeekdayDiscount(orders);
            calculateWeekendDiscount(orders);
            calculatedSpecialDiscount();
            calculatedPresentEvent(totalBeforeDc);
        }
    }

    public String getBadge(int totalBenefit) {
        if (totalBenefit >= STAR_BASE_AMOUNT && totalBenefit < TREE_BASE_AMOUNT) {
            return OutputViewConstant.EVENT_BADGE_STAR;
        }
        if (totalBenefit >= TREE_BASE_AMOUNT && totalBenefit < SANTA_BASE_AMOUNT) {
            return OutputViewConstant.EVENT_BADGE_TREE;
        }
        if (totalBenefit >= SANTA_BASE_AMOUNT) {
            return OutputViewConstant.EVENT_BADGE_SANTA;
        }
        return null;
    }

    private void calculateChristmasDiscount() {
        int discount = 0;
        if (date <= 25) {
            discount = 1000 + 100 * (date - 1);
        }
        Discount.CHRISTMAS.setAmount(discount);
    }

    private void calculateWeekdayDiscount(Map<String, Integer> orders) {
        if (date % 7 != 1 && date % 7 != 2) {
            Discount.WEEKDAY.setAmount(Order.getNumberOfDessert(orders) * WEEK_DISCOUNT);
        }
    }

    private void calculateWeekendDiscount(Map<String, Integer> orders) {
        if (date % 7 == 1 || date % 7 == 2) {
            Discount.WEEKEND.setAmount(Order.getNumberOfMain(orders) * WEEK_DISCOUNT);
        }
    }

    private void calculatedSpecialDiscount() {
        if (date % 7 == 3 || date == 25) {
            Discount.SPECIAL.setAmount(SPECIAL_DISCOUNT_AMOUNT);
        }
    }

    private void calculatedPresentEvent(int totalBeforeDc) {
        if (totalBeforeDc >= PRESENT_EVENT_CRITERION) {
            Discount.PRESENT_EVENT.setAmount(PRESENT_EVENT_AMOUNT);
        }
    }
}
