package com.xyqyear.possystem;

import javafx.fxml.FXML;

public class CheckoutGUIController {
    private FXApp app;

    public CheckoutGUIController(FXApp app) {
        this.app = app;
    }

    @FXML
    private void onConfirmButtonClicked() {
        app.showAlert("shit");
    }

    @FXML
    private void onPrintButtonClicked() {
        app.showAlert("fuck");
    }

    @FXML
    private void onNewSaleButtonClicked() {

    }
}