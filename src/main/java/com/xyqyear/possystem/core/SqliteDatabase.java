package com.xyqyear.possystem.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SqliteDatabase implements IDatabase {
    private Connection connection;

    public SqliteDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:catalog.sqlite3");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductDescription getProductDesc(int id) {
        try {
            ResultSet rs = connection.createStatement().executeQuery("select name, price from catalog where id=" + id);
            return new ProductDescription(id, rs.getString("name"), rs.getDouble("price"));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean saveSale(Sale s) {
        List<SaleLineItem> saleLineItems = s.getLineItems();
        try {
            Statement statement = connection.createStatement();
            for (SaleLineItem sli : saleLineItems) {
                statement.executeUpdate(
                        "insert into sale values (" + sli.getDescription().getId() + ", " + sli.getQuantity() + ")");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
