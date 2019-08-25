package strandkart.ecommerce.menu;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import strandkart.ecommerce.book.Datamodel.Books;
import strandkart.ecommerce.product.DataModel.Product;

import java.io.*;
import java.util.*;

public class Menu {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("books.txt"));
        JSONParser jsonParser = new JSONParser();
        JSONArray booksList = null;
        try {
            FileReader reader = new FileReader("Books.json");
            Object obj = jsonParser.parse(reader);
            booksList = (JSONArray) obj;
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("WELCOME TO STRANDKART.\n" +
                "Select Option to Continue" +
                "1. List all Products." +
                "2. List all Books." +
                "3. Search a particular book." +
                "4. Store a new book." +
                "5. Purchase a book.");
        Scanner input = new Scanner(System.in);
        Integer choice = input.nextInt();
        switch (choice) {
            case 1:
                break;
            case 2:
                if(booksList.size()>0) {
                    System.out.println(booksList);
                }
                else System.out.println("No books in stock.");
                break;
            case 3:
                String bookName = input.next();
                break;
            case 4:
                System.out.println("Please Enter the following details : ");
                System.out.print("Title of the Book : ");
                String title = input.next();
                System.out.print("\n Author of the Book : ");
                String author = input.next();
                System.out.print("\nISBN : ");
                String isbn = input.next();
                System.out.print("\n");

        }
    }
}
