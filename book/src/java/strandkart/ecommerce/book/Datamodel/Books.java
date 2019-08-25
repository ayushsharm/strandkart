package strandkart.ecommerce.book.Datamodel;

import strandkart.ecommerce.product.DataModel.Product;
import strandkart.ecommerce.product.productstype.ProductType;
import strandkart.ecommerce.book.Binding;

public class Books extends Product {
    private String title;

    private String author;

    private String ISBN;

    private String publisher;

    private String language;

    private String year;

    private Binding binding;

    private Float price;

    public Books(ProductType type, String title, String author, String ISBN, String publisher, String language, String year, Binding binding, Float price) {
        super(type);
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.language = language;
        this.year = year;
        this.binding = binding;
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

    public Binding getBinding() {
        return binding;
    }

    public Float getPrice() {
        return price;
    }

}
