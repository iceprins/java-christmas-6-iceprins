package christmas.model;

import java.util.*;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", "appetizer", 6000),
    TAPAS("타파스", "appetizer", 5500),
    CAESAR_SALAD("시저샐러드", "appetizer", 8000),

    T_BONE_STAKE("티본스테이크", "main", 55000),
    BARBECUE_RIB("바비큐립", "main", 54000),
    SEAFOOD_PASTA("해산물파스타","main", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", "main", 25000),

    CHOCO_CAKE("초코케이크", "dessert", 15000),
    ICE_CREAM("아이스크림", "dessert", 5000),

    ZERO_COKE("제로콜라", "beverage", 3000),
    RED_WINE("레드와인", "beverage", 60000),
    CHAMPAGNE("샴페인", "beverage", 25000);

    private final String menuName;
    private final String type;
    private final int price;

    Menu(String menuName, String type, int price) {
        this.menuName = menuName;
        this.type = type;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public static List<String> getMenuNameSet() {
        List<String> menus = new ArrayList<>();
        List<Menu> names = Arrays.asList((Menu.values()));
        names.forEach(Menu -> menus.add(Menu.getMenuName()));
        return menus;
    }
}
