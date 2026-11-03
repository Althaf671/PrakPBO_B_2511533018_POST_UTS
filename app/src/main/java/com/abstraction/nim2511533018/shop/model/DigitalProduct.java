package com.abstraction.nim2511533018.shop.model;

import com.abstraction.nim2511533018.shop.discount.Discountable;

public class DigitalProduct extends Product implements Discountable {

    private static final int DIGITAL_DISCOUNT_PERCENT = 10;

    // attribute
    private String downloadUrl;

    // constructor
    public DigitalProduct(
        String sku, 
        String name, 
        long price,
        String downloadUrl) 
    {
        super(sku, name, price);

        if (downloadUrl == null || downloadUrl.isBlank())
            throw new IllegalArgumentException("Download Url tidak boleh kosong");

        this.downloadUrl = downloadUrl;
    }

    @Override
    public int discountPercent() { return DIGITAL_DISCOUNT_PERCENT; }

    @Override
    public long getFinalPrice() { return applyDiscount(getPrice()); }

    // getter-setter

    public String getDownloadUrl() { return this.downloadUrl; }

    @Override
    public String category() {
        return "Digital";
    }
}
