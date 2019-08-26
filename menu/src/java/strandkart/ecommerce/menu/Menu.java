package strandkart.ecommerce.menu;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import strandkart.ecommerce.book.Binding;
import strandkart.ecommerce.book.Datamodel.Books;
import strandkart.ecommerce.product.DataModel.Product;
import strandkart.ecommerce.product.productstype.ProductType;

import java.io.*;
import java.util.*;

public class Menu {


    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("books.txt"));
        JSONParser jsonParser = new JSONParser();
        JSONArray booksList = null;
        try {
            FileReader reader = new FileReader("books.json");
            Object obj = jsonParser.parse(reader);
            booksList = (JSONArray) obj;
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Books> books = Collections.emptyList();
        for(int i=0;i<booksList.size();i++){
            //books.add(new Books());
            //Add according to the file.
        }

        System.out.println("WELCOME TO STRANDKART.\n" +
                "Select Option to Continue\n" +
                "1. List all Products.\n" +
                "2. List all Books.\n" +
                "3. Search a particular book.\n" +
                "4. Store a new book.\n" +
                "5. Purchase a book.\n");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                List<ProductType> products = ProductType.getAllProducts();
                System.out.println("List of Products : ");
                for(ProductType product: products) {
                    System.out.println(product.name());
                }
                break;
            case 2:
                if(booksList.size()>0) {
                    System.out.println(booksList);
                }
                else System.out.println("No books in stock.");
                break;
            case 3:
                System.out.println("Please enter the name of the book");
                String bookName = input.next();
                for(int i = 0;i<booksList.size();i++) {
                    //Condition for matching String

                }
                break;
            case 4:
                System.out.println("Please Enter the following details : ");
                System.out.print("Title of the Book : ");
                String title = input.next();
                System.out.print("\n Author of the Book : ");
                String author = input.next();
                System.out.print("\nISBN : ");
                String isbn = input.next();
                System.out.print("\n Publisher : ");
                String publisher = input.next();
                System.out.println("\n Language : ");
                String language = input.next();
                System.out.println("\n Year : ");
                String year = input.next();
                System.out.println("\n Binding (1. PaperBack, 2. Hardbound, 3. Digital) : ");
                Binding binding = null;
                int option = input.nextInt();
                if(option==1){
                    binding = Binding.PAPERBACK;
                }
                else if(option==2){
                    binding = Binding.HARDBOUND;
                }
                else if(option==3){
                    binding = Binding.DIGITAL;
                }
                
                break;

        }
    }
}
