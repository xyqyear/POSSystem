package com.xyqyear.possystem.gui;

import com.xyqyear.possystem.core.POSSystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckoutGUIController {
    private static CheckoutGUIController singleton;

    // TODO change print button position
    // TODO delete new sale button
    @FXML
    private Button printButton;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private TextField paymentTextField;

    @FXML
    private Label balanceLabel;

    @FXML
    private void onConfirmButtonClicked() {
        Context.getInstance().makePayment(Double.parseDouble(paymentTextField.getText()));
    }

    public void updateLabel() {
        balanceLabel.setText(String.format("%.2f", POSSystem.getInstance().getBalance()));
        printButton.setDisable(false);
    }

    @FXML
    private void onPrintButtonClicked() {
        Context.getInstance().finishASale();
    }

    public static CheckoutGUIController getInstance() {
        if (singleton == null) {
            singleton = new CheckoutGUIController();
        }
        return singleton;
    }

    private CheckoutGUIController() {
    }

    public void clearDisplay() {
        printButton.setDisable(true);
        totalPriceLabel.setText(String.format("%.2f", POSSystem.getInstance().getTotal()));
        paymentTextField.clear();
        balanceLabel.setText("");
    }
}
