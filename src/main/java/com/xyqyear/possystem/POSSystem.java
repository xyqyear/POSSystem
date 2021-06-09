package com.xyqyear.possystem;

class POSSystem {
    private Sale currentSale;
    private ProductCatalog catalog;

    public void startUp() {
        catalog = new ProductCatalog();
    }

    public void makeNewSale() {
        currentSale = new Sale();
    }

    public void enterItem(int id, int quantity) {
        ProductDescription desc = catalog.getProductDesc(id);
        currentSale.makeLineItem(quantity, desc);
    }

    public void endPurchase() {
        currentSale.beComplete();
    }

    public void makePayment(float cash) {
        currentSale.makePayment(cash);
    }

    public void finishASale() {
        currentSale.finish();
    }
}
