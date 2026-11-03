package com.abstraction.nim2511533018.shop.payment;

public interface PaymentMethod {
    String getName();
    boolean pay(long amount);

    default long adminFee(long amount) {
        if (amount < 0) 
            throw new IllegalArgumentException("Jumlah tidak boleh negatif");
        
        return 0;
    }
}
