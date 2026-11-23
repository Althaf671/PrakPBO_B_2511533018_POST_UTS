package com.abstraction.nim2511533018.shop.model;

public abstract class Product {
    
    // attribut
    private final String sku;
    private final String name;
    private final long price;

    // constructor
    protected Product(
        String sku, 
        String name, 
        long price) 
    {
        if (sku == null || sku.isBlank())
            throw new IllegalArgumentException("SKU tidak boleh kosong");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Nama tidak boleh kosong");

        if (price < 0)
            throw new IllegalArgumentException("Price tidak boleh dibawah 0");

        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    // getter-setter
    public String getSku() { return this.sku; }
    public String getName() { return this.name; }
    public long getPrice() { return this.price; }

    /**
     * price yang dibayar customer.
     * method mungkin akan di override children.
     * 
     * @return
     */
    public long getFinalPrice() { return this.price; }

    /**
     * Semua produk konkret harus menandakan
     * atribut kategori miliknya.
     * 
     * @return
     */
    public abstract String category();

    /**
     * Method konkret yang dapat digunakan semua
     * chidlren.
     * 
     * @return
     */
    public String getInfo() {
        return "[" + category() + "] " + name + " - Rp" + getFinalPrice();
    }
}
