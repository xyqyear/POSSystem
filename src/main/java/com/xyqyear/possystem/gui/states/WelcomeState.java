package com.xyqyear.possystem.gui.states;

import com.xyqyear.possystem.core.POSSystem;
import com.xyqyear.possystem.gui.Context;
import com.xyqyear.possystem.gui.FXApp;

public class WelcomeState implements IState {
    @Override
    public void startUp() {
        POSSystem.getInstance().startUp();
        FXApp.getInstance().startUp();
        Context.getInstance().setState(new ReadyState());
    }

    @Override
    public void makeNewSale() {
    }

    @Override
    public void enterItem(int id, int quantity) {
    }

    @Override
    public void endPurchase() {
    }

    @Override
    public void makePayment(double payment) {
    }

    @Override
    public void finishASale() {
    }
}
