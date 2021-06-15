package com.xyqyear.possystem.gui.states;

import com.xyqyear.possystem.core.POSSystem;
import com.xyqyear.possystem.gui.CheckoutGUIController;
import com.xyqyear.possystem.gui.Context;
import com.xyqyear.possystem.gui.FXApp;

public class CheckoutState implements IState {
    @Override
    public void startUp() {
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
        if (POSSystem.getInstance().getTotal() < payment) {
            POSSystem.getInstance().makePayment(payment);
            CheckoutGUIController.getInstance().updateLabel();
        } else {
            FXApp.getInstance().showAlert("付款不足");
        }
    }

    @Override
    public void finishASale() {
        POSSystem.getInstance().finishASale();
        Context.getInstance().setState(new ReadyState());
        Context.getInstance().makeNewSale();

        FXApp.getInstance().showCheckoutStage(false);
    }
}
