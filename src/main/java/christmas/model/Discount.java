package christmas.model;

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
}
