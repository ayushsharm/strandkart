package strandkart.ecommerce.book.impl;

import strandkart.ecommerce.book.datamodel.Book;
import strandkart.ecommerce.book.FileHandler.BookFileReaderWriter;
import strandkart.ecommerce.book.Sorting;
import strandkart.ecommerce.book.SortingOrder;
import strandkart.ecommerce.book.service.StrandKartBookDetails;

import java.io.IOException;
import java.util.*;

public class StrandkartBookDetailsImpl implements StrandKartBookDetails {


    private List<Book> allBooks = new ArrayList<Book>();
    private TreeMap<String, List<Book>> titleBookMap = new TreeMap<String, List<Book>>();
    private TreeMap<String, List<Book>> yearBookMap = new TreeMap<String, List<Book>>();
    private TreeMap<String, List<Book>> authorBookMap = new TreeMap<String, List<Book>>();

    public StrandkartBookDetailsImpl(){
    }

    public StrandkartBookDetailsImpl(String fileName) throws IOException {
        init(fileName);
    }

    private void init(String fileName) throws IOException {

        long readStartTIme = System.currentTimeMillis();
        BookFileReaderWriter bookFileReaderWriter = new BookFileReaderWriter(fileName);
        allBooks = bookFileReaderWriter.readFromFile();
        System.out.println(allBooks.size());
        long readEndTIme = System.currentTimeMillis();
        System.out.println(String.format("Read time = %d", readEndTIme - readStartTIme));

        long mapInitStartTime = System.currentTimeMillis();
        addToMaps(allBooks);
        System.out.println(titleBookMap.size() + "\n" + authorBookMap.size() + "\n" + yearBookMap.size());
        long mapInitEndTime = System.currentTimeMillis();
        System.out.println(String.format("Map init time = %d", mapInitEndTime - mapInitStartTime));
    }

    @Override
    public List<Book> getAllBooks() {
        System.out.println(allBooks.size());
        return allBooks;
    }

    @Override
    public List<Book> searchBookUsingTitle(String bookName) {
        if (titleBookMap.containsKey(bookName)) {
            return titleBookMap.get(bookName);
        }
        return null;
    }

    @Override
    public Book searchBookUsingIsbn(List<Book> books, String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void addNewBook(Book book) {
        allBooks.add(book);
        addToMaps(Collections.singletonList(book));
    }

    private void addToMaps(List<Book> books) {
        for (Book book : books) {
            String title = book.getTitle();
            String author = book.getAuthor();
            String year = book.getYear();
            addToMaps(book, titleBookMap, title);
            addToMaps(book, authorBookMap, author);
            addToMaps(book, yearBookMap, year);
        }
    }

    private void addToMaps(Book book, TreeMap<String, List<Book>> map, String key){
        if(map.containsKey(key)){
            List<Book> bookListFromMap = map.get(key);
            List<Book> tempBookList = new ArrayList<Book>(bookListFromMap);
            tempBookList.add(book);
            map.put(key, tempBookList);
        }
        else{
            map.put(key, Collections.singletonList(book));
        }
    }

    @Override
    public TreeMap<String, List<Book>> getSortedBookList(Sorting sorting, SortingOrder sortingOrder) {

        if (sorting == Sorting.AUTHOR) {
            if (sortingOrder == SortingOrder.ASCENDING) {
                return authorBookMap;
            } else {
                return getReverseMap(authorBookMap);
            }
        } else if (sorting == Sorting.TITLE) {
            if (sortingOrder == SortingOrder.ASCENDING) {
                return titleBookMap;
            } else {
                return getReverseMap(titleBookMap);
            }
        } else {
            if (sortingOrder == SortingOrder.ASCENDING) {
                return yearBookMap;
            } else {
                return getReverseMap(yearBookMap);
            }
        }
    }

    private TreeMap<String, List<Book>> getReverseMap(TreeMap<String, List<Book>> map) {
        TreeMap<String, List<Book>> reverseMap = new TreeMap<String, List<Book>>(Collections.<String>reverseOrder());
        for (Map.Entry<String, List<Book>> entry : map.entrySet()) {
            reverseMap.put(entry.getKey(), entry.getValue());
        }
        return reverseMap;
    }

    @Override
    public void close(String fileName) throws IOException {
        BookFileReaderWriter bookFileReaderWriter = new BookFileReaderWriter(fileName);
        bookFileReaderWriter.writeToFile(allBooks);
    }

}
