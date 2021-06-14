package com.xyqyear.possystem.gui;

public class TableSaleItem {
    private String index;
    private String id;
    private String name;
    private String price;
    private String quantity;
    private String totalPrice;

    public TableSaleItem(String index, String id, String name, String price, String quantity, String totalPrice) {
        this.index = index;
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
