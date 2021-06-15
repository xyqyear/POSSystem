package com.xyqyear.possystem.gui;

import javafx.fxml.FXML;

public class WelcomeGUIController {
    private static WelcomeGUIController singleton = null;

    private WelcomeGUIController() {
    }

    public static WelcomeGUIController getInstance() {
        if (singleton == null) {
            singleton = new WelcomeGUIController();
        }
        return singleton;
    }

    // !
    @FXML
    private void onConfirmButtonClicked() {
        Context.getInstance().makeNewSale();
    }
}
