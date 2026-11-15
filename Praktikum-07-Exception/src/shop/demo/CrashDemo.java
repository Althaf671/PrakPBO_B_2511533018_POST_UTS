package shop.demo;

import java.util.List;
import shop.model.Address;
import shop.model.PhysicalProduct;
import shop.model.Product;

public class CrashDemo
{
    static class Customer
    {
        private final String name;
        private Address address;

        Customer(String name)
        {
            this.name = name;
        }

        String getName()
        {
            return name;
        }

        Address getAddress()
        {
            return address;
        }
    }

    static Product pickProduct(List<Product> catalog, int number)
    {
        return catalog.get(number - 1);
    }

    static int readQuantity(String text)
    {
        return Integer.parseInt(text);
    }

    static String shippingCity(Customer customer)
    {
        return customer.getAddress().getCity();
    }

    public static void main(String[] args)
    {
        List<Product> catalog = List.of(
        new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300),
        new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400));
        String scenario = args.length > 0 ? args[0] : "1";
        System.out.println("Skenario " + scenario);
        switch (scenario)
        {
            case "1" -> System.out.println("Produk: " + pickProduct(catalog, 5).getName());
            case "2" -> System.out.println("Jumlah: " + readQuantity("dua"));
            case "3" -> System.out.println("Kirim ke: " + shippingCity(new Customer("Siti")));
            default -> System.out.println("Pilih skenario 1, 2, atau 3");
        }

        System.out.println("Selesai.");
    }
}
