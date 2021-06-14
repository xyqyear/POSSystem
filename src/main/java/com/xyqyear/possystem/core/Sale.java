package com.xyqyear.possystem.core;

import java.util.ArrayList;
import java.util.List;

class Sale {
    private List<SaleLineItem> lineItems = new ArrayList<SaleLineItem>();
    private boolean isComplete = false;
    private Payment pay;

    public void makeLineItem(int quantity, ProductDescription description) {
        lineItems.add(new SaleLineItem(quantity, description));
    }

    public void removeItem(int index) {
        lineItems.remove(index);
    }

    public List<SaleLineItem> getLineItems() {
        return lineItems;
    }

    public void beComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public double getTotal() {
        double sumUp = 0;
        for (SaleLineItem sli : lineItems) {
            sumUp += sli.getSubtotal();
        }
        return sumUp;
    }

    public double getCash() {
        return pay.getCash();
    }

    public void finish() {
        Receipt receipt = new Receipt();
        receipt.print(this);
    }

    public void makePayment(double cash) {
        pay = new Payment(cash);
    }

    public double getBalance() {
        return pay.getCash() - getTotal();
    }

}
