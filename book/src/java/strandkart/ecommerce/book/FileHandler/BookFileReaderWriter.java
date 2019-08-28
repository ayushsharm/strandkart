package strandkart.ecommerce.book.FileHandler;

import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.product.productstype.ProductType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BookFileReaderWriter {

    private String filename;

    public BookFileReaderWriter(String filename){
        this.filename = filename;
    }

    public List<Book> readFromFile() throws IOException {

        List<Book> books = new ArrayList<Book>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        StringTokenizer tokenizer;
        while(line!=null){
            tokenizer = new StringTokenizer(line, "\t");
            List<String> dataArray = new ArrayList<String>();
            while(tokenizer.hasMoreElements()){
                dataArray.add(tokenizer.nextElement().toString());
            }
            System.out.println(dataArray.size());
            Book book = new Book(ProductType.BOOKS, );
        }
        return books;
    }

    public void writeToFile() {

    }

}
