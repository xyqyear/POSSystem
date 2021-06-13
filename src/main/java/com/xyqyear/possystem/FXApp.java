package com.xyqyear.possystem;

import java.io.IOException;

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

    private Scene welcomeScene;
    private Scene mainScene;
    private Scene checkoutScene;

    public FXApp() throws IOException {
        FXMLLoader welcomeLoader = new FXMLLoader();
        welcomeLoader.setLocation(getClass().getResource("/welcome.fxml"));
        welcomeLoader.setController(new WelcomeGUIController(this));
        Parent welcomeRoot = welcomeLoader.load();
        welcomeScene = new Scene(welcomeRoot);

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("/main.fxml"));
        mainLoader.setController(new MainGUIController(this));
        Parent mainRoot = mainLoader.load();
        mainScene = new Scene(mainRoot);

        FXMLLoader checkoutLoader = new FXMLLoader();
        checkoutLoader.setLocation(getClass().getResource("/checkout.fxml"));
        checkoutLoader.setController(new CheckoutGUIController(this));
        Parent checkoutRoot = checkoutLoader.load();
        checkoutScene = new Scene(checkoutRoot);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        startWelcome();
    }

    public void startWelcome() {
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public void startNewSale() {
        primaryStage.setScene(mainScene);
    }

    public void startCheckout() {
        Stage checkoutStage = new Stage();
        checkoutStage.setScene(checkoutScene);
        checkoutStage.setResizable(false);
        checkoutStage.initModality(Modality.WINDOW_MODAL);
        checkoutStage.initOwner(primaryStage);
        checkoutStage.show();
    }

    public void showAlert(String message) {
        Alert alert = new Alert(AlertType.NONE, message, ButtonType.YES);
        alert.showAndWait();
    }
}
