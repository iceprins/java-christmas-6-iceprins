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
}
