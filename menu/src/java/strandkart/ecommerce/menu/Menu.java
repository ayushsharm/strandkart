package strandkart.ecommerce.menu;

import com.google.inject.Inject;
import strandkart.ecommerce.book.Binding;
import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.book.impl.StrandkartBookDetailsImpl;
import strandkart.ecommerce.book.service.StrandKartBookDetails;
import strandkart.ecommerce.product.productstype.ProductType;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws IOException {
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
                    for(Book book : allBooks){
                        System.out.println(book.toString());
                    }
                    break;
                case 3:

                case 4:
                    input.nextLine();
                    System.out.println("Please enter the name of the book");
                    String bookName = input.nextLine();
                    Book book = strandKartBookDetails.searchBookUsingTitle(bookName);
                    if(book==null){
                        System.out.println("No books available with this title.");
                        break;
                    }
                    System.out.println(book.toString());
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
                    int option = input.nextInt();
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
                    strandKartBookDetails.addNewBook(productType, title, author, isbn, publisher, language, year, binding, price);
                    break;
                case 6:
                    input.nextLine();
                    System.out.println("Welcome to StrandKart Order placement.\n Please enter the book Title you want to purchase");
                    title = input.nextLine();
                    book = strandKartBookDetails.searchBookUsingTitle(title);
                    if(book==null){
                        System.out.println("No book with this title is available on StrandKart.");
                        break;
                    }
                    System.out.println("How many books would you like to purchase");
                    int quantity = input.nextInt();
                    double orderAmount = quantity*book.getPrice();
                    System.out.println("Order placed for book : " + book.toString()+"Total amount : ₹" + orderAmount+"\n");
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
