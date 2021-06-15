package com.xyqyear.possystem.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckoutGUIController {
    private static CheckoutGUIController singleton;

    @FXML
    private Button printButton;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private TextField paymentTextField;

    @FXML
    private Label balanceLabel;

    @FXML
    private Label printInfoLabel;

    @FXML
    private void onConfirmButtonClicked() {
        try {
            double payment = Double.parseDouble(paymentTextField.getText());
            if (FXApp.getInstance().getPos().getTotal() < payment) {
                FXApp.getInstance().getPos().makePayment(payment);
                balanceLabel.setText(String.format("%.2f", FXApp.getInstance().getPos().getBalance()));
                printButton.setDisable(false);
            } else {
                FXApp.getInstance().showAlert("付款不足");
            }
        } catch (NumberFormatException e) {
            FXApp.getInstance().showAlert("请填写正确的数量");
        }
    }

    @FXML
    private void onPrintButtonClicked() {
        printInfoLabel.setText("正在打印");
        FXApp.getInstance().getPos().finishASale();
        printInfoLabel.setText("打印完成");
    }

    @FXML
    private void onNewSaleButtonClicked() {
        MainGUIController.getInstance().startNewSale();
        FXApp.getInstance().finishCheckout();
    }

    public static CheckoutGUIController getInstance() {
        if (singleton == null) {
            singleton = new CheckoutGUIController();
        }
        return singleton;
    }

    private CheckoutGUIController() {
    }

    public void startNewCheckout() {
        printButton.setDisable(true);
        totalPriceLabel.setText(String.format("%.2f", FXApp.getInstance().getPos().getTotal()));
        paymentTextField.clear();
        balanceLabel.setText("");
        printInfoLabel.setText("");
    }
}
