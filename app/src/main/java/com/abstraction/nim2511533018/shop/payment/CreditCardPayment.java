package com.abstraction.nim2511533018.shop.payment;

import com.abstraction.nim2511533018.shop.StudentInfo;
import com.abstraction.nim2511533018.shop.discount.Discountable;

public class CreditCardPayment 
    implements RefundablePayment, InstallmentPayment {

    private final String holder;
    private final long limit;
    private long used;

    public CreditCardPayment(String holder, long limit) {
        if (holder == null || holder.isBlank() || limit < 0) 
            throw new IllegalArgumentException("Kartu tidak valid");

        this.holder = holder;
        this.limit = limit;
    }

    @Override 
    public String getName() { return "Kartu Kredit (" + holder + ")"; }

    @Override 
    public int maxInstallmentMonths() { return 12; }

    public long getUsedCredit() { return used; }
    public long getRemainingLimit() { return limit - used; }

    @Override 
    public boolean pay(long amount) {
        if (amount <= 0 || amount > getRemainingLimit()) 
            return false;

        used += amount;

        return true;
    }

    @Override 
    public boolean refund(long amount) {
        if (amount <= 0 || amount > used) 
            return false;

        used -= amount;

        return true;
    }

    @Override 
    public long adminFee(long amount) { 
        return Discountable.percentOf(amount, StudentInfo.CARD_ADMIN_PERCENT); 
    }

    @Override 
    public String policy() { 
        return RefundablePayment.super.policy() + "; " + InstallmentPayment.super.policy(); 
    }
}
