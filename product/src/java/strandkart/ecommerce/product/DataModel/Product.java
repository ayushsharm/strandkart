package strandkart.ecommerce.product.DataModel;

import strandkart.ecommerce.product.productstype.ProductType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Product {
    private ProductType type;

    public Product(ProductType type) {
        this.type = type;
    }

    public ProductType getProductType() {
        return type;
    }

}
