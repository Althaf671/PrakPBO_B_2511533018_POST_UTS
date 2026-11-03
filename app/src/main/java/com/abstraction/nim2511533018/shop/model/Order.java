package com.abstraction.nim2511533018.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private final String id;
    private final Address address;
    private final List<Line> lines = new ArrayList<>();

    public Order(String id, Address address) {
        if (id == null || id.isBlank()) 
            throw new IllegalArgumentException("ID kosong");

        this.id = id;
        this.address = Objects.requireNonNull(address);
    }

    public String getOrderId() { return id; }
    public Address getShippingAddress() { return address; }
    public String getId() { return id; }
    public Address getAddress() { return address; }
    public List<Line> getLines() { return List.copyOf(lines); }
    public void addLine(Product product, int quantity) { lines.add(new Line(product, quantity)); }
    public long getItemsTotal() { return lines.stream().mapToLong(Line::getTotal).reduce(0L, Math::addExact); }
    public Receipt createReceipt() { return new Receipt(); }

    public static class Line {
        private final Product product;
        private final int quantity;
        public Line(Product product, int quantity) {
            if (quantity <= 0) throw new IllegalArgumentException("Jumlah harus positif");
            this.product = Objects.requireNonNull(product); this.quantity = quantity;
        }
        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }
        public long getTotal() { return Math.multiplyExact(product.getFinalPrice(), quantity); }
    }
    
    public class Receipt {
        public String toText() {
            StringBuilder text = new StringBuilder("Pesanan " + id + "\n");
            for (Line line : lines) text.append(line.product.getInfo()).append(" x ").append(line.quantity).append("\n");
            return text.append("Total barang: Rp").append(getItemsTotal()).append("\nTujuan: ").append(address).toString();
        }
    }
}
