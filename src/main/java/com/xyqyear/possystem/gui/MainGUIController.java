package com.xyqyear.possystem.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.xyqyear.possystem.core.POSSystem;
import com.xyqyear.possystem.core.ProductDescription;
import com.xyqyear.possystem.core.SaleLineItem;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainGUIController implements Initializable {
    private static MainGUIController singleton = null;

    @FXML
    private TableView<TableSaleItem> table;
    @FXML
    private TableColumn<TableSaleItem, String> indexTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> idTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> nameTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> priceTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> quantityTableColumn;
    @FXML
    private TableColumn<TableSaleItem, String> totalPriceTableColumn;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private void onCheckoutButtonClicked() {
        if (table.getItems().isEmpty()) {
            FXApp.getInstance().showAlert("未输入任何商品!");
        } else {
            Context.getInstance().endPurchase();
        }
    }

    @FXML
    private void OnInsertButtonClicked() {
        try {
            int quantity = Integer.parseInt(quantityTextField.getText());
            if (quantity > 0) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    Context.getInstance().enterItem(id, quantity);
                } catch (NumberFormatException e) {
                    FXApp.getInstance().showAlert("请填写正确的编号");
                }
            } else {
                FXApp.getInstance().showAlert("请填写正确的数量");
            }
        } catch (NumberFormatException e) {
            FXApp.getInstance().showAlert("请填写正确的数量");
        }

        clearInput();
    }

    public static MainGUIController getInstance() {
        if (singleton == null) {
            singleton = new MainGUIController();
        }
        return singleton;
    }

    private MainGUIController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
    }

    private void initializeTable() {
        indexTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("index"));
        idTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("name"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("price"));
        quantityTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("quantity"));
        totalPriceTableColumn.setCellValueFactory(new PropertyValueFactory<TableSaleItem, String>("totalPrice"));
    }

    public void clearDisplay() {
        clearTable();
        clearInput();
        totalPriceLabel.setText("0.00");
    }

    private void clearTable() {
        table.getItems().clear();
    }

    private void clearInput() {
        idTextField.clear();
        quantityTextField.clear();
    }

    public void updateTable() {
        List<SaleLineItem> saleLineItems = POSSystem.getInstance().getLineItems();
        if (saleLineItems.isEmpty()) {
            clearTable();
        } else {
            SaleLineItem sli = saleLineItems.get(saleLineItems.size() - 1);
            ProductDescription desc = sli.getDescription();

            String index = Integer.toString(table.getItems().size() + 1);
            String id = Integer.toString(desc.getId());
            String name = desc.getName();
            String price = String.format("%.2f", desc.getPrice());
            String quantity = Integer.toString(sli.getQuantity());
            String totalPrice = String.format("%.2f", sli.getSubtotal());

            table.getItems().add(new TableSaleItem(index, id, name, price, quantity, totalPrice));
        }

        updateTotalText();
    }

    private void updateTotalText() {
        totalPriceLabel.setText(String.format("%.2f", POSSystem.getInstance().getTotal()));
    }
}
