package com.abstraction.nim2511533018.shop.shipping;

public abstract class ShippingService {
    
    // attribut
    private final String name;

    // contructor
    protected ShippingService(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name tidak boleh kosong");

        this.name = name;
    }

    // getter
    public String getName() { return this.name; }

    /**
     * Rate in rupiah per started kilogram
     * 
     * @return
     */
    protected abstract long ratePerKg();

    /**
     * Weight dibulatkan ke atas, seperti
     * 700g -> 1kg, 1001g -> 2kg.
     * 
     * @return
     */
    public final long calculateCost(int weightGrams) {
        if (weightGrams <= 0) {
            return 0;
        }
        
        long kilograms = (weightGrams + 999L) / 1000;
        return kilograms * ratePerKg();
    }
}
