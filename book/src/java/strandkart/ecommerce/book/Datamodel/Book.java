package strandkart.ecommerce.book.Datamodel;

import strandkart.ecommerce.book.Bindings;
import strandkart.ecommerce.product.DataModel.Product;
import strandkart.ecommerce.product.productstype.ProductType;

public class Book extends Product {
    private String title;

    private String author;

    private String ISBN;

    private String publisher;

    private String language;

    private String year;

    private Bindings bindings;

    private Double price;

    public Book(String title, String author, String ISBN, String publisher, String language, String year, Bindings bindings, Double price) {
        super(ProductType.BOOKS);
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.language = language;
        this.year = year;
        this.bindings = bindings;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLanguage() {
        return language;
    }

    public String getYear() {
        return year;
    }

    public Bindings getBindings() {
        return bindings;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Title : %s\n" +
                "Author : %s\n" +
                "ISBN : %s\n" +
                "Publisher : %s\n" +
                "Language : %s\n" +
                "Year : %s\n" +
                "Bindings : %s\n" +
                "Price : %2f\n", title, author, ISBN, publisher, language, year, bindings.name(), price);
    }
}
