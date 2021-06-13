package com.xyqyear.possystem;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainGUIController implements Initializable {
    private FXApp app;

    @FXML
    private TableView<TableSaleItem> table;
    @FXML
    private TableColumn<TableSaleItem, String> orderTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> idTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> nameTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> priceTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> numberTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> totalPriceTableColumn;

    public MainGUIController(FXApp app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("order"));
        idTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("price"));
        numberTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("number"));
        totalPriceTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("totalPrice"));
    }

    @FXML
    private void onCheckoutButtonClicked() {
        app.startCheckout();
    }

    @FXML
    private void OnInsertButtonClicked() {
        updateTable();
    }

    private void updateTable() {
        table.getItems().add(new TableSaleItem("1", "1", "1", "1", "1", "1"));
    }
}
