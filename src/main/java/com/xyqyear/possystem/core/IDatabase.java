package com.xyqyear.possystem.core;

public interface IDatabase {
    public ProductDescription getProductDesc(int id);

    public boolean saveSale(Sale s);
}
