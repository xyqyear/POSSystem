package com.xyqyear.possystem.gui.states;

import com.xyqyear.possystem.core.POSSystem;
import com.xyqyear.possystem.gui.Context;
import com.xyqyear.possystem.gui.FXApp;
import com.xyqyear.possystem.gui.MainGUIController;

public class ReadyState implements IState {
    @Override
    public void startUp() {
    }

    @Override
    public void makeNewSale() {
        FXApp.getInstance().setMainScene();
        POSSystem.getInstance().makeNewSale();
        MainGUIController.getInstance().clearDisplay();
        Context.getInstance().setState(new MainState());
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
