package com.xyqyear.possystem.gui.states;

import com.xyqyear.possystem.core.POSSystem;
import com.xyqyear.possystem.gui.CheckoutGUIController;
import com.xyqyear.possystem.gui.Context;
import com.xyqyear.possystem.gui.FXApp;
import com.xyqyear.possystem.gui.MainGUIController;

public class MainState implements IState {
    @Override
    public void startUp() {
    }

    @Override
    public void makeNewSale() {
    }

    @Override
    public void enterItem(int id, int quantity) {
        POSSystem.getInstance().enterItem(id, quantity);
        MainGUIController.getInstance().updateTable();
    }

    @Override
    public void endPurchase() {
        Context.getInstance().setState(new CheckoutState());
        CheckoutGUIController.getInstance().clearDisplay();
        FXApp.getInstance().showCheckoutStage(true);
    }

    @Override
    public void makePayment(double payment) {
    }

    @Override
    public void finishASale() {
    }
}
