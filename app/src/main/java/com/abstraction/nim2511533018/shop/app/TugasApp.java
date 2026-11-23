package com.abstraction.nim2511533018.shop.app;

import java.util.List;
import com.abstraction.nim2511533018.shop.model.*;
import com.abstraction.nim2511533018.shop.payment.*;
import com.abstraction.nim2511533018.shop.shipping.*;
import com.abstraction.nim2511533018.shop.service.CheckoutService;
import com.abstraction.nim2511533018.shop.StudentInfo;
import com.abstraction.nim2511533018.shop.notification.*;

public class TugasApp {
    public static void main(String[] args) {
        System.out.println("Tugas oleh " + StudentInfo.NAME + " (" + StudentInfo.NIM + ")");
        CheckoutService checkout = new CheckoutService();
        ShippingService express = new ExpressShipping();
        EWalletPayment wallet = new EWalletPayment("DompetKu", 300_000);
        CreditCardPayment card = new CreditCardPayment("Siti Rahma", 1_000_000);
        for (PaymentMethod payment : List.of(wallet, new BankTransferPayment("Bank Nagari"), card)) {
            long total = checkout.total(ShopApp.sampleCart(), express);
            System.out.println(payment.getName() + ": Rp" + checkout.grandTotal(ShopApp.sampleCart(), express, payment)
                + " (admin Rp" + payment.adminFee(total) + ")");
        }
        System.out.println("Kebijakan kartu kredit: " + card.policy());
        System.out.println("Kebijakan e-wallet: " + wallet.policy());
        NotificationService service = new NotificationService();
        service.register(new EmailChannel()); service.register(new SmsChannel(StudentInfo.SMS_MAX_LENGTH));
        service.register(new WhatsAppChannel());
        service.register(new NotificationChannel() {
            @Override public String channelName() { return "LOG"; }
            @Override public boolean send(CustomerContact contact, String message) {
                if (contact == null || message == null || message.isBlank()) return false;
                System.out.println("[LOG] " + contact.getName() + ": " + message); return true;
            }
        });
        String message = "Pesanan ORD-0001 sudah dikirim dengan kurir Ekspres. Nomor resi akan muncul di aplikasi dalam 1x24 jam. "
            + "Terima kasih telah berbelanja produk UMKM Sumatera Barat di toko kami! Silakan hubungi layanan pelanggan jika membutuhkan bantuan terkait pesanan Anda.";
        for (CustomerContact contact : List.of(new CustomerContact("Siti Rahma", "siti@example.com", "081234567890"),
                new CustomerContact("Budi", "", "+6281298765432")))
            System.out.println("Terkirim ke " + contact.getName() + " lewat " + service.broadcast(contact, message) + " kanal");
    }
}
