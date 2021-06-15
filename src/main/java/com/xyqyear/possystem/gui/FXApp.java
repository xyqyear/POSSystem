package com.xyqyear.possystem.gui;

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
    private Stage checkoutStage;

    private Scene welcomeScene;
    private Scene mainScene;
    private Scene checkoutScene;

    private static FXApp singleton;

    // FXApp will only be initialized by main function, so this is fine.
    public static FXApp getInstance() {
        return singleton;
    }

    @Override
    public void start(Stage primaryStage) {
        singleton = this;
        this.primaryStage = primaryStage;

        Context.getInstance().startUp();
    }

    // !
    public void startUp() {
        try {
            FXMLLoader welcomeLoader = new FXMLLoader();
            welcomeLoader.setLocation(getClass().getResource("/welcome.fxml"));
            welcomeLoader.setController(WelcomeGUIController.getInstance());
            Parent welcomeRoot = welcomeLoader.load();
            welcomeScene = new Scene(welcomeRoot);

            FXMLLoader mainLoader = new FXMLLoader();
            mainLoader.setLocation(getClass().getResource("/main.fxml"));
            mainLoader.setController(MainGUIController.getInstance());
            Parent mainRoot = mainLoader.load();
            mainScene = new Scene(mainRoot);

            FXMLLoader checkoutLoader = new FXMLLoader();
            checkoutLoader.setLocation(getClass().getResource("/checkout.fxml"));
            checkoutLoader.setController(CheckoutGUIController.getInstance());
            Parent checkoutRoot = checkoutLoader.load();
            checkoutScene = new Scene(checkoutRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        checkoutStage = new Stage();
        checkoutStage.setScene(checkoutScene);
        checkoutStage.setResizable(false);
        checkoutStage.initModality(Modality.WINDOW_MODAL);
        checkoutStage.initOwner(primaryStage);

        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public void setMainScene() {
        primaryStage.setScene(mainScene);
    }

    public void showCheckoutStage(boolean b) {
        if (b) {
            checkoutStage.show();
        } else {
            checkoutStage.hide();
        }
    }

    public void showAlert(String message) {
        Alert alert = new Alert(AlertType.NONE, message, ButtonType.YES);
        alert.showAndWait();
    }
}
