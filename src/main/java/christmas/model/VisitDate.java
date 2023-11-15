package christmas.model;

import java.util.*;

public class VisitDate {
    static final int WEEK_DISCOUNT = 2023;

    private final int date;
    public VisitDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
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
}
