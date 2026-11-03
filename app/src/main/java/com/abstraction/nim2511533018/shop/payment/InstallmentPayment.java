package com.abstraction.nim2511533018.shop.payment;

public interface InstallmentPayment extends PaymentMethod {
    int maxInstallmentMonths();

    default long monthlyInstallment(long amount, int months) {
        if (amount < 0 || months < 1 || months > maxInstallmentMonths())
            throw new IllegalArgumentException("Jumlah harus nonnegatif dan tenor 1-12 bulan");

        return amount / months + (amount % months == 0 ? 0 : 1);
    }

    default String policy() { 
        return "Cicilan maksimal " + maxInstallmentMonths() + " bulan"; 
    }
}
