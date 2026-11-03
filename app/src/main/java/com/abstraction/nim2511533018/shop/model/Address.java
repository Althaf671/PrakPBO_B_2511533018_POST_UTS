package com.abstraction.nim2511533018.shop.model;

public final class Address {
    private final String street;
    private final String city;
    private final String postalCode;

    public Address(String street, String city, String postalCode) {
        if (isNullOrWhitespace(street) 
            && isNullOrWhitespace(city) 
            && isNullOrWhitespace(postalCode)) 
            throw new IllegalArgumentException("Alamat tidak lengkap");

        this.street = street; 
        this.city = city; 
        this.postalCode = postalCode;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }

    @Override 
    public String toString() { return street + ", " + city + " " + postalCode; }

    private boolean isNullOrWhitespace(String value) {

        if (value == null || value.isBlank())
            return false;

        return true;
    }
}
