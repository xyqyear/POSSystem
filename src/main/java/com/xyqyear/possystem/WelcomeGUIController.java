package com.xyqyear.possystem;

import javafx.fxml.FXML;

public class WelcomeGUIController {
    private FXApp app;

    public WelcomeGUIController(FXApp app) {
        this.app = app;
    }

    @FXML
    private void onConfirmButtonClicked() {
        app.startNewSale();
    }
}
