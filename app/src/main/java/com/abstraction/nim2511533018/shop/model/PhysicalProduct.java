package com.abstraction.nim2511533018.shop.model;

import com.abstraction.nim2511533018.shop.shipping.Shippable;

public class PhysicalProduct extends Product implements Shippable {
    
    // atrbiut
    private int weightGrams;

    // constructor
    public PhysicalProduct(
        String sku, 
        String name, 
        long price,
        int weightGrams) 
    {
        super(sku, name, price);
        if (weightGrams <= 0 )
            throw new IllegalArgumentException("Weight grams tidak boleh dibawah 0");

        this.weightGrams = weightGrams;
    }

    // getter-setter
    @Override
    public int getWeightGrams() { return this.weightGrams; }

    // behavior methods
    @Override
    public String category() {
        return "Fisik";
    }
}
