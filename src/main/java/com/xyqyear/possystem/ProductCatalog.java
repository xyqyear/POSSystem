package com.xyqyear.possystem;

import java.util.HashMap;
import java.util.Map;

class ProductCatalog {
    private Map<Integer, ProductDescription> descriptions = new HashMap<Integer, ProductDescription>();

    public ProductDescription getProductDesc(int id) {
        return descriptions.get(id);
    }

    public ProductCatalog() {
        loadCatalog();
    }

    private void loadCatalog() {
        ProductDescription desc1 = new ProductDescription(1, "辣条", 2.5);
        descriptions.put(1, desc1);
    }
}
