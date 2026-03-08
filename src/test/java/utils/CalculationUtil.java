package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtil {

    public static BigDecimal calculateSubtotal(int qty, double price) {
        return BigDecimal.valueOf(qty)
                .multiply(BigDecimal.valueOf(price))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateDiscount(BigDecimal subtotal, double discountPercent) {
        return subtotal
                .multiply(BigDecimal.valueOf(discountPercent))
                .divide(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateTax(BigDecimal amountAfterDiscount, double taxPercent) {
        return amountAfterDiscount
                .multiply(BigDecimal.valueOf(taxPercent))
                .divide(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateFinalAmount(BigDecimal subtotal,
                                                  BigDecimal discount,
                                                  BigDecimal tax) {

        return subtotal
                .subtract(discount)
                .add(tax)
                .setScale(2, RoundingMode.HALF_UP);
    }
}