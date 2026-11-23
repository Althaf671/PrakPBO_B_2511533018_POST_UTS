package com.abstraction.nim2511533018.shop.service;

import java.util.List;

import com.abstraction.nim2511533018.shop.model.Product;
import com.abstraction.nim2511533018.shop.payment.PaymentMethod;
import com.abstraction.nim2511533018.shop.payment.RefundablePayment;
import com.abstraction.nim2511533018.shop.shipping.Shippable;
import com.abstraction.nim2511533018.shop.shipping.ShippingService;

public class CheckoutService {
    public long subtotal(List<Product> items) {
        return items.stream()
            .mapToLong(Product::getFinalPrice)
            .reduce(0L, Math::addExact);
    }

    public int totalWeight(List<Product> items) {
        return items.stream()
            .filter(Shippable.class::isInstance)
            .map(Shippable.class::cast)
            .mapToInt(Shippable::getWeightGrams)
            .reduce(0, Math::addExact);
    }

    public long total(List<Product> items, ShippingService shipping) {
        return Math.addExact(subtotal(items), shipping.calculateCost(totalWeight(items)));
    }

    public long grandTotal(List<Product> items, ShippingService shipping, PaymentMethod payment) {
        long total = total(items, shipping);
        return Math.addExact(total, payment.adminFee(total));
    }

    public boolean checkout(List<Product> items, ShippingService shipping, PaymentMethod payment) {
        return !items.isEmpty() && payment.pay(grandTotal(items, shipping, payment));
    }

    public boolean refund(RefundablePayment payment, long amount) { return payment.refund(amount); }
}
