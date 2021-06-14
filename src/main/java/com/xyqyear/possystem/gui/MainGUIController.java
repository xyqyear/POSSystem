package com.xyqyear.possystem.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
    private FXApp app;

    private static MainGUIController singleton;

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
        app.startCheckout();
    }

    @FXML
    private void OnInsertButtonClicked() {
        try {
            int quantity = Integer.parseInt(quantityTextField.getText());
            if (quantity > 0) {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    if (app.getPos().enterItem(id, quantity)) {
                        updateTable();
                    } else {
                        app.showAlert("请填写正确的编号");
                    }
                } catch (NumberFormatException e) {
                    app.showAlert("请填写正确的编号");
                }
            } else {
                app.showAlert("请填写正确的数量");
            }
        } catch (NumberFormatException e) {
            app.showAlert("请填写正确的数量");
        }

        clearInput();
    }

    public static void init(FXApp app) {
        singleton = new MainGUIController(app);
    }

    public static MainGUIController getInstance() {
        return singleton;
    }

    private MainGUIController(FXApp app) {
        this.app = app;
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

        table.setRowFactory((tableView) -> {
            final TableRow<TableSaleItem> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("删除");
            removeItem.setOnAction((event) -> {
                int index = table.getItems().indexOf(row.getItem());
                removeItemFromPos(index);
                repopulateTable();
            });
            rowMenu.getItems().add(removeItem);

            // only display context menu for non-empty rows:
            row.contextMenuProperty()
                    .bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(rowMenu));
            return row;
        });
    }

    public void startNewSale() {
        app.getPos().makeNewSale();
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

    private void repopulateTable() {
        clearTable();
        List<SaleLineItem> saleLineItems = app.getPos().getLineItems();
        for (SaleLineItem sli : saleLineItems) {
            addSaleLineItemToTable(sli);
        }

        updateTotalText();
    }

    private void updateTable() {
        List<SaleLineItem> saleLineItems = app.getPos().getLineItems();
        if (saleLineItems.isEmpty()) {
            clearTable();
        } else {
            addSaleLineItemToTable(saleLineItems.get(saleLineItems.size() - 1));
        }

        updateTotalText();
    }

    private void addSaleLineItemToTable(SaleLineItem sli) {
        ProductDescription desc = sli.getDescription();

        String index = Integer.toString(table.getItems().size() + 1);
        String id = Integer.toString(desc.getId());
        String name = desc.getName();
        String price = String.format("%.2f", desc.getPrice());
        String quantity = Integer.toString(sli.getQuantity());
        String totalPrice = String.format("%.2f", sli.getSubtotal());

        table.getItems().add(new TableSaleItem(index, id, name, price, quantity, totalPrice));
    }

    private void updateTotalText() {
        totalPriceLabel.setText(String.format("%.2f", app.getPos().getTotal()));
    }

    private void removeItemFromPos(int index) {
        app.getPos().removeItem(index);
    }
}
