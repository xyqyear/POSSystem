package com.xyqyear.possystem.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckoutGUIController {
    private FXApp app;

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
            if (app.getPos().getTotal() < payment) {
                app.getPos().makePayment(payment);
                balanceLabel.setText(String.format("%.2f", app.getPos().getBalance()));
                printButton.setDisable(false);
            } else {
                app.showAlert("付款不足");
            }
        } catch (NumberFormatException e) {
            app.showAlert("请填写正确的数量");
        }
    }

    @FXML
    private void onPrintButtonClicked() {
        printInfoLabel.setText("正在打印");
        app.getPos().finishASale();
        printInfoLabel.setText("打印完成");
    }

    @FXML
    private void onNewSaleButtonClicked() {
        MainGUIController.getInstance().startNewSale();
        app.finishCheckout();
    }

    public static void init(FXApp app) {
        singleton = new CheckoutGUIController(app);
    }

    public static CheckoutGUIController getInstance() {
        return singleton;
    }

    private CheckoutGUIController(FXApp app) {
        this.app = app;
    }

    public void startNewCheckout() {
        printButton.setDisable(true);
        totalPriceLabel.setText(String.format("%.2f", app.getPos().getTotal()));
        paymentTextField.clear();
        balanceLabel.setText("");
        printInfoLabel.setText("");
    }
}
