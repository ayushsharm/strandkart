package strandkart.ecommerce.book;

import java.util.ArrayList;
import java.util.List;

public enum Bindings {
    PAPERBACK("Paperback"),
    HARDBOUND("Hardbound"),
    DIGITAL("Digital"),
    ;

    String displayName;
    Bindings(String displayName) {
        this.displayName = displayName;
    }

    public static List<Bindings> getAllBindings() {
        List<Bindings> allBindings = new ArrayList<Bindings>();
        allBindings.add(PAPERBACK);
        allBindings.add(HARDBOUND);
        allBindings.add(DIGITAL);
        return allBindings;
    }

}
