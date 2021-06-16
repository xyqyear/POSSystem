package com.xyqyear.possystem.core;

class ProductCatalog {
    public ProductDescription getProductDesc(int id) {
        return DatabaseProxy.getInstance().getProductDesc(id);
    }
}
