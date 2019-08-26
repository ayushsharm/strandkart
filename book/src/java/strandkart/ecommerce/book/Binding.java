package strandkart.ecommerce.book;

import java.util.ArrayList;
import java.util.List;

public enum Binding {
    PAPERBACK("Paperback"),
    HARDBOUND("Hardbound"),
    DIGITAL("Digital"),
    ;

    String displayName;
    Binding(String displayName) {
        this.displayName = displayName;
    }

    public static List<Binding> getAllBindings() {
        List<Binding> allBindings = new ArrayList<Binding>();
        allBindings.add(PAPERBACK);
        allBindings.add(HARDBOUND);
        allBindings.add(DIGITAL);
        return allBindings;
    }

}
