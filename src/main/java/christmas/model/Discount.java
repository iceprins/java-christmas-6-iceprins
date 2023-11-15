package christmas.model;

import christmas.service.OutputViewConstant;

import java.text.DecimalFormat;
import java.util.*;

public enum Discount {
    CHRISTMAS("크리스마스 디데이 할인", 0),
    WEEKDAY("평일 할인", 0),
    WEEKEND("주말 할인", 0),
    SPECIAL("특별 할인", 0),
    PRESENT_EVENT("증정 이벤트", 0);

    private final String discountType;
    private int amount;

    Discount(String discountType, int amount) {
        this.discountType = discountType;
        this.amount = amount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private static boolean isNoDiscount() {
        for (Discount discountName : Discount.values()) {
            if (discountName.getAmount() != 0) {
                return true;
            }
        }
        return false;
    }

    public static int getTotalBenefit() {
        int sum = 0;
        for (Discount discountName : Discount.values()) {
            if (discountName.getAmount() != 0) {
                sum += discountName.getAmount();
            }
        }
        return sum;
    }

    public static int getTotalDiscount() {
        int sum = 0;
        for (Discount discountName : Discount.values()) {
            if (!discountName.getDiscountType().equals("증정 이벤트") && discountName.getAmount() != 0) {
                sum += discountName.getAmount();
            }
        }
        return sum;
    }

    private static List<String> getBenefitInfo() {
        DecimalFormat df = new DecimalFormat();
        List<String> benefitHistory = new ArrayList<>();

        for (Discount discountName : Discount.values()) {
            if (discountName.getAmount() != 0) {
                String benefitInfo = discountName.getDiscountType() + OutputViewConstant.COLON
                        + df.format(-discountName.getAmount()) + OutputViewConstant.WON;
                benefitHistory.add(benefitInfo);
            }
        }
        return benefitHistory;
    }

    public static List<String> getBenefitHistory() {
        List<String> result = new ArrayList<>();

        if (!isNoDiscount()) {
            result.add(OutputViewConstant.NOTHING);
            return result;
        }
        return getBenefitInfo();
    }

    public static String getPresentMenu() {
        if (PRESENT_EVENT.getAmount() == 0) {
            return OutputViewConstant.NOTHING;
        }
        return OutputViewConstant.CHAMPAGNE;
    }
}
