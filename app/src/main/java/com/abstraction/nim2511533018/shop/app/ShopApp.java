package com.abstraction.nim2511533018.shop.app;

import java.util.List;
import com.abstraction.nim2511533018.shop.model.*;
import com.abstraction.nim2511533018.shop.payment.*;
import com.abstraction.nim2511533018.shop.shipping.*;
import com.abstraction.nim2511533018.shop.service.CheckoutService;
public class ShopApp {
    public static List<Product> sampleCart() {
        return List.of(new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300),
            new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400),
            new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000, "https://toko-umkm.example/unduh/ebk-003"));
    }
    public static void main(String[] args) {
        List<Product> cart = sampleCart();
        CheckoutService checkout = new CheckoutService();
        ShippingService regular = new RegularShipping(), express = new ExpressShipping();
        System.out.println("== Katalog ==");
        cart.forEach(p -> System.out.println(p.getInfo()));
        System.out.println("== Ringkasan ==");
        System.out.println("Subtotal: Rp" + checkout.subtotal(cart));
        System.out.println("Berat kiriman: " + checkout.totalWeight(cart) + " g");
        System.out.println("Total Reguler: Rp" + checkout.total(cart, regular));
        System.out.println("Total Ekspres: Rp" + checkout.total(cart, express));
        EWalletPayment wallet = new EWalletPayment("DompetKu", 300_000);
        PaymentMethod transfer = new BankTransferPayment("Bank Nagari");
        CreditCardPayment card = new CreditCardPayment("Siti Rahma", 1_000_000);
        System.out.println("== Pembayaran ==");
        System.out.println(wallet.getName() + " berhasil? " + checkout.checkout(cart, regular, wallet));
        System.out.println("Sisa saldo: Rp" + wallet.getBalance());
        System.out.println(transfer.getName() + " berhasil? " + checkout.checkout(cart, express, transfer));
        System.out.println(card.getName() + " berhasil? " + checkout.checkout(cart, express, card));
        System.out.println("Cicilan 3 bulan: Rp" + card.monthlyInstallment(checkout.total(cart, express), 3));
        Product ebook = cart.get(2);
        System.out.println("Refund e-wallet berhasil? " + checkout.refund(wallet, ebook.getFinalPrice()));
        System.out.println("Saldo setelah refund: Rp" + wallet.getBalance());
        Order order = new Order("ORD-0001", new Address("Jl. Moh Hatta. 10", "Padang", "25121"));
        order.addLine(cart.get(0), 2); order.addLine(ebook, 1);
        System.out.println(order.createReceipt().toText());
        PaymentMethod voucher = new PaymentMethod() {
            @Override public String getName() { return "Voucher Promo Hari Ini"; }
            @Override public boolean pay(long amount) { return amount > 0 && amount <= 50_000; }
        };
        System.out.println(voucher.getName() + " untuk e-book berhasil? " + checkout.checkout(List.of(ebook), regular, voucher));
    }
}
