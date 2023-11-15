package christmas.model;

import java.util.*;

public class Order {
    private final Map<String, Integer> orders;

    public Order(Map<String, Integer> orders) {
        this.orders = orders;
    }

    public Map<String, Integer> getOrders() {
        return orders;
    }

    public int getTotalBeforeDc() {
        int total = 0;
        List<String> menuNames = new ArrayList<>(orders.keySet());

        for (Menu menu : Menu.values()) {
            if (menuNames.contains(menu.getMenuName())) {
                total += orders.get(menu.getMenuName()) * menu.getPrice();
            }
        }
        return total;
    }

    public int getTotalAfterDc() {
        return getTotalBeforeDc() -Discount.getTotalDiscount();
    }

    public static int getNumberOfDessert(Map<String, Integer> orders) {
        int sum = 0;
        List<String> menuNames = new ArrayList<>(orders.keySet());

        for (Menu menu : Menu.getDessertMenu()) {
            if (menuNames.contains(menu.getMenuName())) {
                sum += orders.get(menu.getMenuName());
            }
        }
        return sum;
    }
}
