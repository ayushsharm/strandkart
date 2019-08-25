package strandkart.ecommerce.product.productstype;

import java.util.ArrayList;
import java.util.List;

public enum Products {
    BOOKS("Books"),
    ;

    String displayname;

    Products(String displayname) {
        this.displayname = displayname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public static List<Products> getAllProducts() {
        List<Products> allProducts = new ArrayList<Products>();
        allProducts.add(BOOKS);

        return allProducts;
    }
}
