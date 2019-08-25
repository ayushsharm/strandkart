package strandkart.ecommerce.product.productstype;

import java.util.ArrayList;
import java.util.List;

public enum ProductType {
    BOOKS("Books"),
    ;

    String displayname;

    ProductType(String displayname) {
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public static List<ProductType> getAllProducts() {
        List<ProductType> allProducts = new ArrayList<ProductType>();
        allProducts.add(BOOKS);

        return allProducts;
    }
}
