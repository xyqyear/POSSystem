package com.xyqyear.possystem.gui.states;

public interface IState {
    public void startUp();

    public void makeNewSale();

    public void enterItem(int id, int quantity);

    public void endPurchase();

    public void makePayment(double payment);

    public void finishASale();
}
