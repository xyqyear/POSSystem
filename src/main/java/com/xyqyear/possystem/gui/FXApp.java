package com.xyqyear.possystem.gui;

import java.io.IOException;

import com.xyqyear.possystem.core.POSSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXApp extends Application {
    private Stage primaryStage;
    private Stage checkoutStage;

    private Scene welcomeScene;
    private Scene mainScene;
    private Scene checkoutScene;

    private POSSystem pos;

    public FXApp() throws IOException {
        pos = new POSSystem();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        FXMLLoader welcomeLoader = new FXMLLoader();
        welcomeLoader.setLocation(getClass().getResource("/welcome.fxml"));
        welcomeLoader.setController(new WelcomeGUIController(this));
        Parent welcomeRoot = welcomeLoader.load();
        welcomeScene = new Scene(welcomeRoot);

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("/main.fxml"));
        MainGUIController.init(this);
        mainLoader.setController(MainGUIController.getInstance());
        Parent mainRoot = mainLoader.load();
        mainScene = new Scene(mainRoot);

        FXMLLoader checkoutLoader = new FXMLLoader();
        checkoutLoader.setLocation(getClass().getResource("/checkout.fxml"));
        CheckoutGUIController.init(this);
        checkoutLoader.setController(CheckoutGUIController.getInstance());
        Parent checkoutRoot = checkoutLoader.load();
        checkoutScene = new Scene(checkoutRoot);

        checkoutStage = new Stage();
        checkoutStage.setScene(checkoutScene);
        checkoutStage.setResizable(false);
        checkoutStage.initModality(Modality.WINDOW_MODAL);
        checkoutStage.initOwner(primaryStage);

        startWelcome();
    }

    public void startWelcome() {
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public void startMain() {
        MainGUIController.getInstance().startNewSale();
        primaryStage.setScene(mainScene);
    }

    public void startCheckout() {
        CheckoutGUIController.getInstance().startNewCheckout();
        checkoutStage.show();
    }

    public void finishCheckout() {
        checkoutStage.hide();
    }

    public void showAlert(String message) {
        Alert alert = new Alert(AlertType.NONE, message, ButtonType.YES);
        alert.showAndWait();
    }

    public POSSystem getPos() {
        return pos;
    }
}
