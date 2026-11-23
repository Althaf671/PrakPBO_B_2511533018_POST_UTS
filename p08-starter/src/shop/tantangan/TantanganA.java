package shop.tantangan;

import shop.discount.NoDiscount;
import shop.exception.PaymentFailedException;
import shop.model.Address;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.payment.QrisPayment;
import shop.service.CheckoutService;
import shop.service.InvoicePrinter;
import shop.service.Notifier;
import shop.service.OrderCalculator;
import shop.shipping.SameDayRate;

public class TantanganA
{
    public static void main(String[] args) throws PaymentFailedException
    {
        PhysicalProduct beras = new PhysicalProduct("BRS", "Beras Solok 2kg", 40_000, 2_000);
        CheckoutService sameDayCheckout = new CheckoutService(
                new OrderCalculator(new SameDayRate()),
                new InvoicePrinter(),
                new Notifier());

        System.out.println("=== Demo Tantangan A: Same Day (<= 3 kg) ===");
        Order order1 = new Order("SD-01", new Address("Althaf", "Padang"))
                .add(beras, 1);
        sameDayCheckout.checkout(order1, new NoDiscount(), new QrisPayment());

        System.out.println("=== Demo Tantangan A: Same Day (> 3 kg) ===");
        Order order2 = new Order("SD-02", new Address("Althaf", "Padang"))
                .add(beras, 2);
        sameDayCheckout.checkout(order2, new NoDiscount(), new QrisPayment());
    }
}
