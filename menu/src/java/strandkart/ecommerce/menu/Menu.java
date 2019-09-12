package strandkart.ecommerce.menu;

import com.google.inject.Inject;
import com.sun.deploy.util.OrderedHashSet;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import strandkart.ecommerce.book.Bindings;
import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.book.Sorting;
import strandkart.ecommerce.book.SortingOrder;
import strandkart.ecommerce.book.impl.StrandkartBookDetailsImpl;
import strandkart.ecommerce.book.service.StrandKartBookDetails;
import strandkart.ecommerce.cart.impl.CartManagementServiceImpl;
import strandkart.ecommerce.cart.service.CartManagementService;
import strandkart.ecommerce.orders.DataModels.Order;
import strandkart.ecommerce.orders.impl.OrderManagementImpl;
import strandkart.ecommerce.orders.service.OrderManagement;
import strandkart.ecommerce.product.productstype.ProductType;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class Menu {

    public static void main(String[] args) throws IOException {
//        RandomFileGenerator randomFileGenerator = new RandomFileGenerator();
//        randomFileGenerator.createCsvFileWithRandomValues();

        final String fileName = "books.txt";
        long initStartTime = System.currentTimeMillis();
        System.out.println("Starting Init");
        StrandKartBookDetails strandKartBookDetails = new StrandkartBookDetailsImpl(fileName);
        CartManagementService cartManagementService = new CartManagementServiceImpl();
        OrderManagement orderManagement = new OrderManagementImpl();
        long initEndTime = System.currentTimeMillis();
        System.out.println(String.format("Time taken to load 1 million book data and Initialize the list and maps : %d ms", initEndTime - initStartTime));
        System.out.println("Initialization done");

        System.out.println("WELCOME TO STRAND-KART.\n" +
                "Select Option to Continue\n" +
                "1. List all Products.\n" +
                "2. List all Book.\n" +
                "3. Get Sorted List\n" +
                "4. Search a particular book.\n" +
                "5. Store a new book.\n" +
                "6. Purchase a book.\n" +
                "7. Show cart.\n" +
                "8. Show all Orders\n" +
                "9. Exit.");

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
                    for (int i = 0; i < allBooks.size(); i++) {
                        System.out.println(allBooks.get(i).toString());
                        counter++;
                        if (counter % 10 == 0) {
                            System.out.println("0: End\n1 : Previous\nPress any other key to continue.");
                            int selection = input.nextInt();
                            if (selection == 0) {
                                break;
                            } else if (selection == 1) {
                                if (i >= 19) {
                                    i = i - 20;
                                } else {
                                    System.out.println("No previous records to display.");
                                    i = i - 10;
                                }
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
                        break;
                    }
                    SortingOrder sortingOrder = null;
                    System.out.println("Please specify the sorting option :\n" +
                            "1. Ascending.\n" +
                            "2. Descending");
                    int option1 = input.nextInt();
                    if (option1 == 1) {
                        sortingOrder = SortingOrder.ASCENDING;
                    } else if (option1 == 2) {
                        sortingOrder = SortingOrder.DESCENDING;
                    } else {
                        System.out.println("Invalid option selected!!!!!!!!!!!!!!!");
                        break;
                    }
                    long sortStartTime = System.currentTimeMillis();
                    long sortStartTimeNano = System.nanoTime();
                    TreeMap<String, List<Book>> sortedBookMap = strandKartBookDetails.getSortedBookList(sorting, sortingOrder);
                    System.out.println(sortedBookMap.size());
                    long sortEndTime = System.currentTimeMillis();
                    long sortEndTimeNano = System.nanoTime();
                    if (sortEndTime - sortStartTime > 0) {

                        System.out.println(String.format("Time taken to sort list : %d ms", sortEndTime - sortStartTime));
                    } else {
                        System.out.println(String.format("Time taken to sort list : %d microseconds", (sortEndTimeNano - sortStartTimeNano) / 1000));
                    }
                    counter = 0;
                    int flag = 1;
                    displayBooks(sortedBookMap, sortingOrder, input);
                    List<Book> booksToDisplay = new ArrayList<Book>();
                    for (Map.Entry<String, List<Book>> entry : sortedBookMap.entrySet()) {
                        List<Book> books = sortedBookMap.get(entry.getKey());
                        booksToDisplay.addAll(books);
                        if (booksToDisplay.size() % 10 == 0) {

                        }
                        if (flag == 0) {
                            break;
                        }
                    }
                    break;
                case 4:
                    input.nextLine();
                    Book book = null;
                    System.out.println("Please enter the name of the book");
                    String bookName = input.nextLine();
                    long searchStartTime = System.nanoTime();
                    List<Book> books = strandKartBookDetails.searchBookUsingTitle(bookName);
                    long searchEndTime = System.nanoTime();
                    System.out.println(String.format("Time taken for search operation = %d microseconds.", (searchEndTime - searchStartTime) / 1000));
                    if (books == null) {
                        System.out.println("No books available with this title.");
                        break;
                    } else if (books.size() == 1) {
                        System.out.println(books.get(0));
                        System.out.println("To add the book to the cart, press 1.\nPress any other key to continue.");
                        if (input.nextInt() == 1) {
                            System.out.println("How many books would you like to add to the cart : ");
                            int quantity = input.nextInt();
                            Double orderAmount = quantity * books.get(0).getPrice();
                            Order order = new Order(books.get(0), quantity, orderAmount);
                            cartManagementService.addToCart(order);
                        }
                    } else {
                        System.out.println(String.format("%d books found with the title %s. Please provide with the ISBN number :", books.size(), bookName));
                        String isbn = input.nextLine();
                        book = strandKartBookDetails.searchBookUsingIsbn(books, isbn);
                        System.out.println(book);

                        System.out.println("To add the book to the cart, press 1.\nPress any other key to continue.");

                        if (input.nextInt() == 1) {
                            System.out.println("How many books would you like to add to the cart : ");
                            int quantity = input.nextInt();
                            Double orderAmount = quantity * book.getPrice();
                            Order order = new Order(book, quantity, orderAmount);
                            cartManagementService.addToCart(order);
                        }
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
                    System.out.print("Bindings (1. PaperBack, 2. Hardbound, 3. Digital) : ");
                    Bindings bindings = null;
                    option = input.nextInt();
                    if (option == 1) {
                        bindings = Bindings.PAPERBACK;
                    } else if (option == 2) {
                        bindings = Bindings.HARDBOUND;
                    } else if (option == 3) {
                        bindings = Bindings.DIGITAL;
                    }
                    System.out.print("Price : ");
                    Double price = input.nextDouble();
                    ProductType productType = ProductType.BOOKS;
                    book = new Book(title, author, isbn, publisher, language, year, bindings, price);
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
                    orderManagement.orderBook(book, quantity, orderAmount);
                    System.out.println("Order placed for book : " + book.toString() + "Total amount : ₹" + orderAmount + "\n");
                    break;
                case 7:
                    List<Order> cart = cartManagementService.showCart();
                    for (Order order : cart) {
                        System.out.println(order);
                    }
                    System.out.println("Press 1 to purchase the cart : ");
                    if (input.nextInt() == 1) {
                        for (Order order : cart) {
                            orderManagement.orderBook(order);
                        }
                    }
                    break;
                case 8:
                    List<Order> orders = orderManagement.showAllOrders();
                    for (Order order : orders) {
                        System.out.println(order);
                    }
                    break;
                case 9:
                    //strandKartBookDetails.close(fileName);
                    System.out.println("Thank you for using StrandKart");
                    break;
            }
            if (choice == 9) {
                break;
            } else if (choice < 0 || choice > 9) {
                System.out.println("Invalid option selected");
            }
            System.out.println("Select Option to Continue\n" +
                    "1. List all Products.\n" +
                    "2. List all Book.\n" +
                    "3. Get Sorted List\n" +
                    "4. Search a particular book.\n" +
                    "5. Store a new book.\n" +
                    "6. Purchase a book.\n" +
                    "7. Show cart.\n" +
                    "8. Show all Orders.\n" +
                    "9. Exit.");
        }
    }

    private static void displayBooks(TreeMap<String, List<Book>> map, SortingOrder sortingOrder, Scanner input) {
        Set<String> iteratorSet;
        if (sortingOrder == SortingOrder.ASCENDING) {
            iteratorSet = map.keySet();
        } else {
            iteratorSet = map.descendingKeySet();
        }
        List<Book> booksToDisplay = new ArrayList<Book>();
        int currentBookIndex = 0;
        int indexLeftAt = 0;
        for (String key : iteratorSet) {
            booksToDisplay.addAll(map.get(key));
            if (booksToDisplay.size() - currentBookIndex >= 10) {
                currentBookIndex = iterateBookList(booksToDisplay, currentBookIndex, input);
                if (currentBookIndex == -1) {
                    return;
                }
            }
        }
    }


    private static int iterateBookList(List<Book> books, int currentBookIndex, Scanner input) {
        while (currentBookIndex != -1 && currentBookIndex < books.size()) {
            for (int i = currentBookIndex; i < currentBookIndex + 10; i++) {
                System.out.println(books.get(i));
            }
            currentBookIndex += 10;
            System.out.println("Press 0 to exit\nPress 1 to move forward\nPress 2 to move backward");
            int choice = input.nextInt();
            while (choice != 0 && choice != 1 && choice != 2) {
                System.out.println("Invalid choice. Please enter the correct option : ");
                choice = input.nextInt();
            }
            if (choice == 1) {
                if (currentBookIndex == books.size() || currentBookIndex + 10 < books.size()) {
                    return currentBookIndex;
                }
            } else if (choice == 2) {
                if (currentBookIndex - 20 >= 0) {
                    currentBookIndex -= 20;
                } else {
                    System.out.println("No previous books to Display.");
                    currentBookIndex -= 10;
                }
            } else {
                return -1;
            }

        }
        return currentBookIndex;
    }
}
