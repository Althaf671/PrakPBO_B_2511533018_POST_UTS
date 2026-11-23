package com.abstraction.nim2511533018.review.fixed;

import java.util.List;
import com.abstraction.nim2511533018.shop.model.*;
import com.abstraction.nim2511533018.shop.payment.EWalletPayment;
import com.abstraction.nim2511533018.shop.service.CheckoutService;
import com.abstraction.nim2511533018.shop.shipping.ExpressShipping;

/** Demo desain perbaikan T3; sumber BadShop asli di proyek masih kosong. */
public class BadShopRefactored {
    public static void main(String[] args) {
        Product coffee = new PhysicalProduct("KOPI-001", "Kopi", 85_000, 1000);
        CheckoutService checkout = new CheckoutService();
        System.out.println("Total cart: " + checkout.subtotal(List.of(coffee, coffee)));
        System.out.println("Ongkir Ekspres 2 kg: " + new ExpressShipping().calculateCost(2000));
        Order first = new Order("ORD-1", new Address("Jl. Sudirman 10", "Padang", "25121"));
        Order second = new Order("ORD-2", new Address("Jl. Sudirman 10", "Bukittinggi", "26111"));
        System.out.println(first.getOrderId() + " -> " + first.getShippingAddress());
        System.out.println(second.getOrderId() + " -> " + second.getShippingAddress());
        Product flashSale = new Product("FLASH-1", "Flash Sale", 100_000) {
            @Override public String category() { return "Promo"; }
            @Override public long getFinalPrice() { return 70_000; }
        };
        System.out.println("Harga flash sale: " + flashSale.getFinalPrice());
        EWalletPayment voucher = new EWalletPayment("Voucher", 100_000);
        System.out.println(voucher.pay(30_000) + " | sisa Rp" + voucher.getBalance());
    }
}
