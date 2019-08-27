package strandkart.ecommerce.book.service;

import strandkart.ecommerce.book.Binding;
import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.product.productstype.ProductType;

import java.util.List;

public interface StrandKartBookDetails {

    List<Book> getAllBooks();

    Book searchBookUsingTitle(String bookName);

    Book searchBookUsingIsbn(List<Book> books, String isbn);

    void addNewBook(ProductType productType, String title, String author, String isbn, String publisher, String language, String year, Binding binding, Double price);
}
