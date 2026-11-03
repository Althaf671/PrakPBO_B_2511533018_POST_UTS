package com.abstraction.nim2511533018.shop.payment;

import com.abstraction.nim2511533018.shop.StudentInfo;

public class BankTransferPayment implements PaymentMethod {
    private final String bankName;

    public BankTransferPayment(String bankName) {
        if (bankName == null || bankName.isBlank()) 
            throw new IllegalArgumentException("Nama bank kosong");

        this.bankName = bankName;
    }
    @Override 
    public String getName() { return "Transfer " + bankName; }

    @Override 
    public boolean pay(long amount) { return amount > 0; }

    @Override public long adminFee(long amount) {
        if (amount < 0) 
            throw new IllegalArgumentException("Jumlah negatif");
        
        return StudentInfo.TRANSFER_ADMIN_FEE;
    }
}
