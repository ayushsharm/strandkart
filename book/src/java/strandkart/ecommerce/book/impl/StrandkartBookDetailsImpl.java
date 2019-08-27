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

    public StrandkartBookDetailsImpl(){
    }

    private static List<Book> allBooks = new ArrayList<Book>(Arrays.asList(
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
            new Book(ProductType.BOOKS, "Harry Potter 22", "J. K. Rowling", "isbn333", "Scholastic", "English ", "1997", Binding.HARDBOUND, 232.66)
    ));

    private static HashMap<String, Book> isbnTitleMap = new HashMap<String, Book>(){{
        put("Harry Potter 1", allBooks.get(0));
        put("Harry Potter 2", allBooks.get(1));
        put("Harry Potter 3", allBooks.get(2));
        put("Harry Potter 4", allBooks.get(3));
        put("Harry Potter 5", allBooks.get(4));
        put("Harry Potter 6", allBooks.get(5));
        put("Harry Potter 7", allBooks.get(6));
        put("Harry Potter 8", allBooks.get(7));
        put("Harry Potter 9", allBooks.get(8));
        put("Harry Potter 10", allBooks.get(9));
        put("Harry Potter 11", allBooks.get(10));
        put("Harry Potter 12", allBooks.get(11));
        put("Harry Potter 13", allBooks.get(12));
        put("Harry Potter 14", allBooks.get(13));
        put("Harry Potter 15", allBooks.get(14));
        put("Harry Potter 16", allBooks.get(15));
        put("Harry Potter 17", allBooks.get(16));
        put("Harry Potter 18", allBooks.get(17));
        put("Harry Potter 19", allBooks.get(18));
        put("Harry Potter 20", allBooks.get(19));
        put("Harry Potter 21", allBooks.get(20));
        put("Harry Potter 22", allBooks.get(21));
    }};


    public List<Book> getAllBooks() {
        System.out.println(allBooks.size());
        return allBooks;
    }

    public Book searchBookUsingTitle(String bookName) {
        if(isbnTitleMap.containsKey(bookName)){
            return isbnTitleMap.get(bookName);
        }
        return null;
    }

    public Book searchBookUsingIsbn(List<Book> books, String isbn) {
        for(Book book : books){
            if(book.getISBN().equals(isbn)){
                return book;
            }
        }
        return null;
    }

    public void addNewBook(ProductType productType, String title, String author, String isbn, String publisher, String language, String year, Binding binding, Double price){
        if(isbnTitleMap.containsKey(title)){
            System.out.println("Book already present");
            return;
        }
        allBooks.add(new Book(productType, title, author, isbn, publisher, language, year, binding, price));
    }

}
