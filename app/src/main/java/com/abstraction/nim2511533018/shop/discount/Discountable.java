package com.abstraction.nim2511533018.shop.discount;

public interface Discountable {
    int discountPercent();

    default long applyDiscount(long price) { 
        return price - percentOf(price, Math.max(0, Math.min(100, discountPercent()))); 
    }

    static long percentOf(long price, int percent) {
        validate(price, percent);
        return price / 100 * percent + price % 100 * percent / 100;
    }
    private static void validate(long price, int percent) {
        if (price < 0 || percent < 0 || percent > 100)
            throw new IllegalArgumentException("Harga atau persentase tidak valid");
    }
}
