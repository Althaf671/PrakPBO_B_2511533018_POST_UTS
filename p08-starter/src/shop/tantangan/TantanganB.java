package shop.tantangan;

import java.util.List;
import shop.discount.NoDiscount;
import shop.exception.PaymentFailedException;
import shop.model.Address;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.notify.SmsChannel;
import shop.notify.WhatsAppChannel;
import shop.payment.QrisPayment;
import shop.service.CheckoutService;
import shop.service.InvoicePrinter;
import shop.service.Notifier;
import shop.service.OrderCalculator;
import tools.NimParams;

public class TantanganB
{
    public static void main(String[] args) throws PaymentFailedException
    {
        PhysicalProduct kopi = new PhysicalProduct("KOPI", "Kopi Gayo 250g", 85_000, 300);
        Notifier multiNotifier = new Notifier(List.of(
                new SmsChannel(),
                new WhatsAppChannel()));
        CheckoutService checkout = new CheckoutService(
                new OrderCalculator(NimParams.ratePerKg()),
                new InvoicePrinter(),
                multiNotifier);

        System.out.println("=== Demo Tantangan B: Banyak Saluran Notifikasi ===");
        Order order = new Order("MB-01", new Address("Althaf", "Padang"))
                .add(kopi, 1);
        checkout.checkout(order, new NoDiscount(), new QrisPayment());
    }
}
