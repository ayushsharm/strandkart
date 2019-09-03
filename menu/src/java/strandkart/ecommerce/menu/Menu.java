package strandkart.ecommerce.menu;

import strandkart.ecommerce.book.Binding;
import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.book.Sorting;
import strandkart.ecommerce.book.SortingOrder;
import strandkart.ecommerce.book.impl.StrandkartBookDetailsImpl;
import strandkart.ecommerce.book.service.StrandKartBookDetails;
import strandkart.ecommerce.product.productstype.ProductType;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws IOException {
//        RandomFileGenerator randomFileGenerator = new RandomFileGenerator();
//        randomFileGenerator.createCsvFileWithRandomValues();

        final String fileName = "books.txt";
        StrandKartBookDetails strandKartBookDetails = new StrandkartBookDetailsImpl(fileName);

        System.out.println("WELCOME TO STRAND-KART.\n" +
                "Select Option to Continue\n" +
                "1. List all Products.\n" +
                "2. List all Book.\n" +
                "3. Get Sorted List\n" +
                "4. Search a particular book.\n" +
                "5. Store a new book.\n" +
                "6. Purchase a book.\n" +
                "7. Exit.");

        Scanner input = new Scanner(System.in);

        int choice = 0;
        while (choice != 6) {

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    List<ProductType> products = ProductType.getAllProducts();
                    System.out.println("List of Products : ");
                    for (ProductType product : products) {
                        System.out.println(product.name());
                    }
                    break;
                case 2:
                    List<Book> allBooks = strandKartBookDetails.getAllBooks();
                    int counter = 0;
                    for (Book book : allBooks) {
                        System.out.println(book.toString());
                        counter++;
                        if (counter % 10 == 0) {
                            System.out.println("Press 0 to end display\n Press any other key to continue.");
                            if (input.nextInt() == 0) {
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    input.nextLine();
                    System.out.println("Select the column with which you want the sorted list : \n" +
                            "1. Title of the book.\n" +
                            "2. Author of the book.\n" +
                            "3. Year of publishing");
                    int option = input.nextInt();
                    Sorting sorting = null;
                    if (option == 1) {
                        sorting = Sorting.TITLE;
                    } else if (option == 2) {
                        sorting = Sorting.AUTHOR;
                    } else if (option == 3) {
                        sorting = Sorting.YEAR;
                    } else {
                        System.out.println("Invalid option selected!!!!!!!!!!!!!");
                    }
                    SortingOrder sortingOrder = null;
                    System.out.println("Please specify the sorting option :\n" +
                            "1. Ascending.\n" +
                            "2. Descending");
                    option = input.nextInt();
                    if (option == 1) {
                        sortingOrder = SortingOrder.ASCENDING;
                    }
                    if (option == 2) {
                        sortingOrder = SortingOrder.DESCENDING;
                    }
                    List<Book> sortedBookList = strandKartBookDetails.getSortedBookList(sorting, sortingOrder);
                    counter = 0;
                    for (Book book : sortedBookList) {
                        System.out.println(book);
                        counter++;
                        if (counter % 10 == 0) {
                            System.out.println("Press 0 to end display\n Press any other key to continue.");
                            if (input.nextInt() == 0) {
                                break;
                            }
                        }
                    }
                    break;
                case 4:
                    input.nextLine();
                    System.out.println("Please enter the name of the book");
                    String bookName = input.nextLine();
                    List<Book> books = strandKartBookDetails.searchBookUsingTitle(bookName);
                    if (books == null) {
                        System.out.println("No books available with this title.");
                        break;
                    } else if (books.size() == 1) {
                        System.out.println(books.get(0));
                    } else {
                        System.out.println(String.format("%d books found with the title %s. Please provide with the ISBN number :", books.size(), bookName));
                        String isbn = input.nextLine();
                        Book book = strandKartBookDetails.searchBookUsingIsbn(books, isbn);
                        System.out.println(book);
                    }
                    break;
                case 5:
                    input.nextLine();
                    System.out.print("Please Enter the following details :\n Title of the Book : ");
                    String title = input.nextLine();
                    System.out.print("Author of the Book : ");
                    String author = input.nextLine();
                    System.out.print("ISBN : ");
                    String isbn = input.nextLine();
                    System.out.print("Publisher : ");
                    String publisher = input.nextLine();
                    System.out.print("Language : ");
                    String language = input.nextLine();
                    System.out.print("Year : ");
                    String year = input.nextLine();
                    System.out.print("Binding (1. PaperBack, 2. Hardbound, 3. Digital) : ");
                    Binding binding = null;
                    option = input.nextInt();
                    if (option == 1) {
                        binding = Binding.PAPERBACK;
                    } else if (option == 2) {
                        binding = Binding.HARDBOUND;
                    } else if (option == 3) {
                        binding = Binding.DIGITAL;
                    }
                    System.out.print("Price : ");
                    Double price = input.nextDouble();
                    ProductType productType = ProductType.BOOKS;
                    Book book = new Book(title, author, isbn, publisher, language, year, binding, price);
                    strandKartBookDetails.addNewBook(book);
                    break;
                case 6:
                    input.nextLine();
                    System.out.println("Welcome to StrandKart Order placement.\n Please enter the book Title you want to purchase");
                    title = input.nextLine();
                    books = strandKartBookDetails.searchBookUsingTitle(title);
                    if (books == null) {
                        System.out.println("No book with this title is available on StrandKart.");
                        break;
                    } else if (books.size() == 1) {
                        book = books.get(0);
                    } else {
                        System.out.println(String.format("%d books found with the title %s. Please provide with the ISBN number :", books.size(), title));
                        isbn = input.nextLine();
                        book = strandKartBookDetails.searchBookUsingIsbn(books, isbn);
                        if (book == null) {
                            System.out.println("No such books found");
                            break;
                        }
                    }
                    System.out.println("How many books would you like to purchase");
                    int quantity = input.nextInt();
                    double orderAmount = quantity * book.getPrice();
                    System.out.println("Order placed for book : " + book.toString() + "Total amount : ₹" + orderAmount + "\n");
                    break;
                case 7:
                    System.out.println("Thank you for using StrandKart");
                    strandKartBookDetails.close(fileName);
                    break;
            }
            if (choice != 7) {
                System.out.println("Select Option to Continue\n" +
                        "1. List all Products.\n" +
                        "2. List all Book.\n" +
                        "3. Get Sorted List\n" +
                        "4. Search a particular book.\n" +
                        "5. Store a new book.\n" +
                        "6. Purchase a book.\n" +
                        "7. Exit.");
            }
        }
    }
}
