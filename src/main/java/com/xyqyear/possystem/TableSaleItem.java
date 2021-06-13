package com.xyqyear.possystem;

public class TableSaleItem {
    private String order;
    private String id;
    private String name;
    private String price;
    private String number;
    private String totalPrice;

    public TableSaleItem(String order, String id, String name, String price, String number, String totalPrice) {
        this.order = order;
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public String getOrder() {
        return order;
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

    public String getNumber() {
        return number;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
