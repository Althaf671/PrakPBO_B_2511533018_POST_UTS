package com.abstraction.nim2511533018.shop.shipping;

/**
 * RelugarShipping
 * 
 * Tugas:
 * Tulis Product p = new Product("X", "X", 1000); di sebuah main, 
 * kompilasi, lalu catat pesan error-nya untuk laporan.
 */
public class RegularShipping extends ShippingService {
    public RegularShipping() {
        super("Reguler");
    }
    
    @Override
    protected long ratePerKg() {
        return 10_000;
    }
}
