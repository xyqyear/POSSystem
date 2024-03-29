package com.xyqyear.possystem.core;

import java.util.List;

public class POSSystem {
    private Sale currentSale;
    private ProductCatalog catalog;
    private static POSSystem singleton = null;

    public static POSSystem getInstance() {
        if (singleton == null) {
            singleton = new POSSystem();
        }
        return singleton;
    }

    public void startUp() {
        catalog = new ProductCatalog();
    }

    public void makeNewSale() {
        currentSale = new Sale();
        currentSale.setReceipt(new SimpleReceipt());
        currentSale.setPrinter(new TxtPrinter());
    }

    public void enterItem(int id, int quantity) {
        ProductDescription desc = catalog.getProductDesc(id);
        currentSale.makeLineItem(quantity, desc);
    }

    public void removeItem(int index) {
        currentSale.removeItem(index);
    }

    public void endPurchase() {
        currentSale.beComplete();
    }

    public void makePayment(double cash) {
        currentSale.makePayment(cash);
    }

    public void finishASale() {
        currentSale.finish();
    }

    public List<SaleLineItem> getLineItems() {
        return currentSale.getLineItems();
    }

    public double getTotal() {
        return currentSale.getTotal();
    }

    public double getBalance() {
        return currentSale.getBalance();
    }
}