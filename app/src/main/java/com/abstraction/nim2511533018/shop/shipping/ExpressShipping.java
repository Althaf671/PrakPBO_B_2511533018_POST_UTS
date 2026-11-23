package com.abstraction.nim2511533018.shop.shipping;

public class ExpressShipping extends ShippingService {
    public ExpressShipping() { super("Ekspres"); }
    
    @Override
    protected long ratePerKg() { return 18_000; }
}
