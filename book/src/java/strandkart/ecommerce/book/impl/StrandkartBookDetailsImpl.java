package strandkart.ecommerce.book.impl;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.book.FileHandler.BookFileReaderWriter;
import strandkart.ecommerce.book.Sorting;
import strandkart.ecommerce.book.SortingOrder;
import strandkart.ecommerce.book.service.StrandKartBookDetails;

import java.io.IOException;
import java.util.*;

public class StrandkartBookDetailsImpl implements StrandKartBookDetails {


    private static List<Book> allBooks = new ArrayList<Book>();
    private static TreeMap<String, List<Book>> titleBookMap = new TreeMap<String, List<Book>>();
    private static TreeMap<String, List<Book>> yearBookMap = new TreeMap<String, List<Book>>();
    private static TreeMap<String, List<Book>> authorBookMap = new TreeMap<String, List<Book>>();

    public StrandkartBookDetailsImpl(String fileName) throws IOException {
        init(fileName);
    }

    private void init(String fileName) throws IOException {

        long readStartTIme = System.currentTimeMillis();
        BookFileReaderWriter bookFileReaderWriter = new BookFileReaderWriter(fileName);
        allBooks = bookFileReaderWriter.readFromFile();
        long readEndTIme = System.currentTimeMillis();
        System.out.println(String.format("Read time = %d", readEndTIme - readStartTIme));

        long mapInitStartTime = System.currentTimeMillis();
        addToMaps(allBooks);
        long mapInitEndTime = System.currentTimeMillis();
        System.out.println(String.format("Map init time = %d", mapInitEndTime - mapInitStartTime));
    }

    public List<Book> getAllBooks() {
        System.out.println(allBooks.size());
        return allBooks;
    }

    public List<Book> searchBookUsingTitle(String bookName) {
        if (titleBookMap.containsKey(bookName)) {
            return titleBookMap.get(bookName);
        }
        return null;
    }

    public Book searchBookUsingIsbn(List<Book> books, String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public void addNewBook(Book book) {
        allBooks.add(book);
        addToMaps(Collections.singletonList(book));
    }

    private void addToMaps(List<Book> books) {
        List<Book> books1 = new ArrayList<Book>();
        for (Book book : books) {
            books1.add(book);
            List<Book> tempBookList = new ArrayList<Book>();
            if (titleBookMap.containsKey(book.getTitle())) {
                List<Book> bookfromMap = titleBookMap.get(book.getTitle());
                tempBookList.addAll(bookfromMap);
                tempBookList.add(book);
                titleBookMap.put(book.getTitle(), tempBookList);
                tempBookList.clear();
            } else {
                titleBookMap.put(book.getTitle(), books1);
            }

            if (authorBookMap.containsKey(book.getAuthor())) {
                List<Book> bookfromMap = authorBookMap.get(book.getAuthor());
                tempBookList.addAll(bookfromMap);
                tempBookList.add(book);
                authorBookMap.put(book.getAuthor(), tempBookList);
                tempBookList.clear();
            } else {
                authorBookMap.put(book.getAuthor(), books1);
            }

            if (yearBookMap.containsKey(book.getYear())) {
                List<Book> bookfromMap = yearBookMap.get(book.getYear());
                tempBookList.addAll(bookfromMap);
                tempBookList.add(book);
                yearBookMap.put(book.getYear(), tempBookList);
                tempBookList.clear();
            } else {
                yearBookMap.put(book.getYear(), books1);
            }
            books1.clear();
        }
    }

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

    public TreeMap<String, List<Book>> getReverseMap(TreeMap<String, List<Book>> map) {
        TreeMap<String, List<Book>> reverseMap = new TreeMap<String, List<Book>>(Collections.<String>reverseOrder());
        for (Map.Entry<String, List<Book>> entry : map.entrySet()) {
            reverseMap.put(entry.getKey(), entry.getValue());
        }
        return reverseMap;
    }

    public void close(String fileName) throws IOException {
        BookFileReaderWriter bookFileReaderWriter = new BookFileReaderWriter(fileName);
        bookFileReaderWriter.writeToFile(allBooks);
    }

    private List<Book> createListWithTreeMap(TreeMap<String, List<Book>> map) {
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        List<Book> booksSorted = new ArrayList<Book>();
        int counter = 0;
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            List<Book> tempBookList = map.get(me.getKey());
            if (tempBookList.size() > 1) {
                counter++;
            }
            for (Book book : tempBookList) {
                booksSorted.add(book);
            }
        }
        System.out.println("Counter for 0 = " + counter);
        System.out.println(booksSorted.size());
        return booksSorted;
    }
}
