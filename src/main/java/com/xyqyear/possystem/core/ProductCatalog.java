package com.xyqyear.possystem.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class ProductCatalog {
    private Connection connection;

    public ProductDescription getProductDesc(int id) {
        try {
            ResultSet rs = connection.createStatement().executeQuery("select name, price from catalog where id=" + id);
            return new ProductDescription(id, rs.getString("name"), rs.getDouble("price"));
        } catch (SQLException e) {
            return null;
        }
    }

    public ProductCatalog() {
        loadDB();
    }

    private void loadDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:catalog.sqlite3");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
