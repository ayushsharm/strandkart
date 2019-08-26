package strandkart.ecommerce.menu;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import strandkart.ecommerce.book.Binding;
import strandkart.ecommerce.book.Datamodel.Books;
import strandkart.ecommerce.orders.DataModels.Order;
import strandkart.ecommerce.product.productstype.ProductType;
import strandkart.ecommerce.user.datamodel.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Menu {

    public static Set<HashMap<String, Object>> findMatchingBooksWithTitle(ArrayList<HashMap<String, Object>> bookMapList, String bookName) {
        Set<HashMap<String, Object>> booksFound = new HashSet<HashMap<String, Object>>();
        for (HashMap<String, Object> map : bookMapList) {
            if (bookName.equals(map.get("Title").toString())) {
                booksFound.add(map);
            }
        }
        return booksFound;
    }

    public static HashMap<String, Object> findMatchingBookWithISBN(Set<HashMap<String, Object>> set, String isbn) {
        HashMap<String, Object> bookFound = new HashMap<String, Object>();
        for(HashMap<String, Object> map1: set){
            if(map1.get("ISBN").toString().equals(isbn)){
                return bookFound;
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {

        JSONParser jsonParser = new JSONParser();
        List<JSONObject> bookJSONList = new ArrayList<JSONObject>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
            String line = reader.readLine();
            while (line != null) {
                Object obj = jsonParser.parse(line);
                JSONObject jsonObject = (JSONObject) obj;
                bookJSONList.add(jsonObject);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String, Object>> bookMapList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> bookMap = new HashMap<String, Object>();
        List<Books> allBooks = new ArrayList<Books>();

        List<Order> orderList = new ArrayList<Order>();

        for (JSONObject jsonObject : bookJSONList) {
            bookMap.clear();
            bookMap.put("Title", jsonObject.get("Title").toString());
            bookMap.put("Author", jsonObject.get("Author").toString());
            bookMap.put("ISBN", jsonObject.get("ISBN").toString());
            bookMap.put("Publisher", jsonObject.get("Publisher").toString());
            bookMap.put("Language", jsonObject.get("Language").toString());
            bookMap.put("Year", jsonObject.get("Year").toString());
            bookMap.put("Binding", jsonObject.get("Binding").toString());
            bookMap.put("Price", jsonObject.get("Price").toString());
            bookMapList.add(bookMap);
        }


        System.out.println("WELCOME TO STRANDKART.\n" +
                "Select Option to Continue\n" +
                "1. List all Products.\n" +
                "2. List all Books.\n" +
                "3. Search a particular book.\n" +
                "4. Store a new book.\n" +
                "5. Purchase a book.\n" +
                "6. Exit.");

        int choice = 0;
        while (choice != 6) {
            Scanner input = new Scanner(System.in);
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
                    if (bookMapList.size() > 0) {
                        System.out.println(bookMapList);
                    } else System.out.println("No books in stock.");
                    break;
                case 3:
                    input.nextLine();
                    System.out.println("Please enter the name of the book");
                    String bookName = input.nextLine();
                    System.out.println(bookName);
                    Set<HashMap<String, Object>> booksFound = findMatchingBooksWithTitle(bookMapList, bookName);
                    if(booksFound.size()==0){
                        System.out.println("No book with this title is available on StrandKart.");
                    }
                    else if(booksFound.size()>1){
                        System.out.println("Multiple books found with the same title. Please specify the ISBN number");
                        String isbn = input.nextLine();
                        HashMap<String, Object> bookFound = findMatchingBookWithISBN(booksFound, isbn);
                        if(bookFound==null){
                            System.out.println("No book with given ISBN number and Title is available on StrandKart.");
                        }
                        else{
                            System.out.println(bookFound);
                        }
                    }
                    break;
                case 4:
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
                    Float price = input.nextFloat();
                    Books book = new Books(ProductType.BOOKS, title, author, isbn, publisher, language, year, binding, price);
                    allBooks.add(book);
                    bookMap.put("Title", title);
                    bookMap.put("Author", author);
                    bookMap.put("ISBN", isbn);
                    bookMap.put("Publisher", publisher);
                    bookMap.put("Language", language);
                    bookMap.put("Year", year);
                    bookMap.put("Binding", binding);
                    bookMap.put("Price", price);
                    bookMapList.add(bookMap);
                    break;
                case 5:
                    System.out.println("Welcome to StrandKart Order placement.\n Please enter the book Title you want to purchase");
                    String title1 = input.nextLine();
                    Set<HashMap<String, Object>> booksfound = findMatchingBooksWithTitle(bookMapList, title1);
                    HashMap<String, Object> bookfound = null;
                    if(booksfound.size()==0){
                        System.out.println("No book with this title is available on StrandKart.");
                        break;
                    }
                    else if(booksfound.size()>1) {
                        System.out.println("Multiple books found with the same title. Please specify the ISBN number");
                        String Isbn = input.nextLine();
                        bookfound = findMatchingBookWithISBN(booksfound, Isbn);
                        if(bookfound==null){
                            System.out.println("No book with given ISBN number and Title is available on StrandKart.");
                            break;
                        }
                    }
                    System.out.println("Please Enter your Name : ");
                    String userName = input.nextLine();
                    System.out.println("Please Enter your contact Number : ");
                    String contactNumber = input.nextLine();
                    System.out.println("Please Enter the quantity of items you want to purchase.");
                    Integer quantity = input.nextInt();
                    if(quantity>0){
                        Binding binding1 = null;
                        if(bookfound.get("Binding").toString().equals("PAPERBACK")){
                            binding1 = Binding.PAPERBACK;
                        }
                        else if(bookfound.get("Binding").toString().equals("HARDBOUND")) {
                            binding1 = Binding.HARDBOUND;
                        }
                        else if(bookfound.get("Binding").toString().equals("DIGITAL")){
                            binding1 = Binding.DIGITAL;
                        }
                        Books bookToPurchase = new Books(ProductType.BOOKS, bookfound.get("Title").toString(),
                                bookfound.get("Author").toString(),bookfound.get("ISBN").toString(),
                                bookfound.get("Publisher").toString(), bookfound.get("Language").toString(),
                                bookfound.get("Year").toString(), binding1, (Float)bookfound.get("Price"));
                        User user = new User(userName, contactNumber);
                        Float orderAmount = quantity*((Float)bookfound.get("Price"));
                        Order order = new Order(bookToPurchase, user, quantity, Calendar.getInstance().getTime(), orderAmount);
                        orderList.add(order);
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using StrandKart");
                    for (HashMap<String, Object> bookmap : bookMapList) {
                        JSONObject bookJSON = new JSONObject(bookmap);
                        bookJSONList.add(bookJSON);
                    }
                    System.out.println(bookJSONList.size());
                    FileWriter fileWriter = new FileWriter("books.txt");
                    for (int i = 0; i < bookJSONList.size(); i++) {
                        fileWriter.write(bookJSONList.get(i).toJSONString());
                    }
                    fileWriter.close();
                    break;
            }
            if (choice != 6) {
                System.out.println("Select Option to Continue\n" +
                        "1. List all Products.\n" +
                        "2. List all Books.\n" +
                        "3. Search a particular book.\n" +
                        "4. Store a new book.\n" +
                        "5. Purchase a book.\n" +
                        "6. Exit.");
            }
        }
    }
}
