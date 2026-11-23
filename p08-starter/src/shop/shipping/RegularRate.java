package shop.shipping;

public class RegularRate implements ShippingRate
{
    private final long ratePerKg;

    public RegularRate(long ratePerKg)
    {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public long costFor(int grams)
    {
        if (grams <= 0)
        {
            return 0;
        }
        return ((grams + 999) / 1000) * ratePerKg;
    }
}
