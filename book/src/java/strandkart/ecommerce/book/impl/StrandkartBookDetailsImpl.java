package strandkart.ecommerce.book.impl;

import strandkart.ecommerce.book.Binding;
import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.book.service.StrandKartBookDetails;
import strandkart.ecommerce.product.productstype.ProductType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StrandkartBookDetailsImpl implements StrandKartBookDetails {

    private List<Book> allBooks = new ArrayList<Book>(Arrays.asList(
            new Book(ProductType.BOOKS, "Harry Potter 1", "J. K. Rowling", "isbn123", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 2", "J. K. Rowling", "isbn234", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 3", "J. K. Rowling", "isbn345", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 4", "J. K. Rowling", "isbn456", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 5", "J. K. Rowling", "isbn567", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 6", "J. K. Rowling", "isbn458", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 7", "J. K. Rowling", "isbn754", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 8", "J. K. Rowling", "isbn842", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 9", "J. K. Rowling", "isbn451", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 10", "J. K. Rowling", "isbn412", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 11", "J. K. Rowling", "isbn542", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 12", "J. K. Rowling", "isbn874", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 13", "J. K. Rowling", "isbn321", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 14", "J. K. Rowling", "isbn654", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 15", "J. K. Rowling", "isbn777", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 16", "J. K. Rowling", "isbn888", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 17", "J. K. Rowling", "isbn555", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 18", "J. K. Rowling", "isbn000", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 19", "J. K. Rowling", "isbn007", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 20", "J. K. Rowling", "isbn008", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 21", "J. K. Rowling", "isbn413", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
            new Book(ProductType.BOOKS, "Harry Potter 22", "J. K. Rowling", "isbn333", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66),
    ));

    private HashMap<String, String> isbnTitleMap = new HashMap<String, String>(){{
        put("Harry Potter 1", "isbn123");
        put("Harry Potter 2", "isbn234");
        put("Harry Potter 3", "isbn345");
        put("Harry Potter 4", "isbn456");
        put("Harry Potter 5", "isbn567");
        put("Harry Potter 6", "isbn458");
        put("Harry Potter 7", "isbn754");
        put("Harry Potter 8", "isbn842");
        put("Harry Potter 9", "isbn451");
        put("Harry Potter 10", "isbn412");
        put("Harry Potter 11", "isbn542");
        put("Harry Potter 12", "isbn874");
        put("Harry Potter 13", "isbn321");
        put("Harry Potter 14", "isbn654");
        put("Harry Potter 15", "isbn777");
        put("Harry Potter 16", "isbn888");
        put("Harry Potter 17", "isbn555");
        put("Harry Potter 18", "isbn000");
        put("Harry Potter 19", "isbn007");
        put("Harry Potter 20", "isbn008");
        put("Harry Potter 21", "isbn413");
        put("Harry Potter 22", "isbn333");
    }};
    public List<Book> getAllBooks() {
        return allBooks;
    }

    public List<Book> searchBookUsingTitle(String bookName) {
        if(isbnTitleMap.containsKey(isbnTitleMap)){
            return
        }

    }

}
