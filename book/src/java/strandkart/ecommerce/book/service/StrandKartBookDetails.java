package strandkart.ecommerce.book.service;

import strandkart.ecommerce.book.Datamodel.Book;

import java.util.List;

public interface StrandKartBookDetails {

    List<Book> getAllBooks();

    List<Book> searchBookUsingTitle(String bookName);
    }

}
