package strandkart.ecommerce.book.service;

import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.book.Sorting;
import strandkart.ecommerce.book.SortingOrder;

import java.io.IOException;
import java.util.List;

public interface StrandKartBookDetails {

    List<Book> getAllBooks();

    List<Book> searchBookUsingTitle(String bookName);

    Book searchBookUsingIsbn(List<Book> books, String isbn);

    void addNewBook(Book book);

    void close(String filename) throws IOException;

    List<Book> getSortedBookList(Sorting sorting, SortingOrder sortingOrder);


}
