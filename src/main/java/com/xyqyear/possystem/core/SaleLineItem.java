package com.xyqyear.possystem.core;

public class SaleLineItem {
    private int quantity;
    private ProductDescription description;

    public SaleLineItem(int quantity, ProductDescription description) {
        this.quantity = quantity;
        this.description = description;
    }

    public double getSubtotal() {
        return quantity * description.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDescription getDescription() {
        return description;
    }
}
