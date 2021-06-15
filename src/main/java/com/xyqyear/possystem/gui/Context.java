package com.xyqyear.possystem.gui;

import com.xyqyear.possystem.gui.states.IState;
import com.xyqyear.possystem.gui.states.WelcomeState;

public class Context {
    private IState state;

    private static Context singleton = null;

    public static Context getInstance() {
        if (singleton == null) {
            singleton = new Context();
        }
        return singleton;
    }

    private Context() {
        setState(new WelcomeState());
    }

    public void setState(IState s) {
        state = s;
    }

    public void startUp() {
        state.startUp();
    }

    public void makeNewSale() {
        state.makeNewSale();
    }

    public void enterItem(int id, int quantity) {
        state.enterItem(id, quantity);
    }

    public void endPurchase() {
        state.endPurchase();
    }

    public void makePayment(double payment) {
        state.makePayment(payment);
    }

    public void finishASale() {
        state.finishASale();
    }
}
