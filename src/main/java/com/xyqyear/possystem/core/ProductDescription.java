package com.xyqyear.possystem.core;

public class ProductDescription {
    int id;
    String name;
    double price;

    public ProductDescription(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}