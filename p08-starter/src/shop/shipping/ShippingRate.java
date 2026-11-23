package shop.shipping;

/** Kontrak perhitungan tarif ongkos kirim. */
public interface ShippingRate
{
    long costFor(int grams);
}
