package com.abstraction.nim2511533018.shop.app;

import java.util.List;

import com.abstraction.nim2511533018.shop.model.Address;
import com.abstraction.nim2511533018.shop.model.DigitalProduct;
import com.abstraction.nim2511533018.shop.model.Order;
import com.abstraction.nim2511533018.shop.model.PhysicalProduct;
import com.abstraction.nim2511533018.shop.model.Product;
import com.abstraction.nim2511533018.shop.payment.BankTransferPayment;
import com.abstraction.nim2511533018.shop.payment.CreditCardPayment;
import com.abstraction.nim2511533018.shop.payment.EWalletPayment;
import com.abstraction.nim2511533018.shop.payment.PaymentMethod;
import com.abstraction.nim2511533018.shop.service.CheckoutService;
import com.abstraction.nim2511533018.shop.shipping.ExpressShipping;
import com.abstraction.nim2511533018.shop.shipping.RegularShipping;
import com.abstraction.nim2511533018.shop.shipping.ShippingService;

public class ShopApp {

    public static List<Product> sampleCart() {
        return List.of(
            new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300),
            new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400),
            new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000, "https://toko-umkm.example/unduh/ebk-003"));
    }

    public static void main(String[] args) {
        var cart = sampleCart();
        var checkout = new CheckoutService();
        ShippingService regular = new RegularShipping(), express = new ExpressShipping();
        
        IO.println("== Katalog ==");
        cart.forEach(p -> IO.println(p.getInfo()));

        IO.println("== Ringkasan ==");
        IO.println("Subtotal: Rp" + checkout.subtotal(cart));
        IO.println("Berat kiriman: " + checkout.totalWeight(cart) + " g");
        IO.println("Total Reguler: Rp" + checkout.total(cart, regular));
        IO.println("Total Ekspres: Rp" + checkout.total(cart, express));

        var wallet = new EWalletPayment("DompetKu", 300_000L);
        var transfer = new BankTransferPayment("Bank Nagari");
        var card = new CreditCardPayment("Siti Rahma", 1_000_000L);

        IO.println("== Pembayaran ==");
        IO.println(wallet.getName() + " berhasil? " + checkout.checkout(cart, regular, wallet));
        IO.println("Sisa saldo: Rp" + wallet.getBalance());
        IO.println(transfer.getName() + " berhasil? " + checkout.checkout(cart, express, transfer));
        IO.println(card.getName() + " berhasil? " + checkout.checkout(cart, express, card));
        IO.println("Cicilan 3 bulan: Rp" + card.monthlyInstallment(checkout.total(cart, express), 3));

        var ebook = cart.get(2);

        IO.println("Refund e-wallet berhasil? " + checkout.refund(wallet, ebook.getFinalPrice()));
        IO.println("Saldo setelah refund: Rp" + wallet.getBalance());

        var order = new Order("ORD-0001", new Address("Jl. Moh Hatta. 10", "Padang", "25121"));
        order.addLine(cart.get(0), 2); order.addLine(ebook, 1);

        IO.println(order.createReceipt().toText());

        var voucher = new PaymentMethod() {
            @Override 
            public String getName() { return "Voucher Promo Hari Ini"; }
            
            @Override 
            public boolean pay(long amount) { return amount > 0 && amount <= 50_000; }
        };

        IO.println(voucher.getName() + " untuk e-book berhasil? " + checkout.checkout(List.of(ebook), regular, voucher));
    }
}
