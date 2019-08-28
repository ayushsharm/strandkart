package strandkart.ecommerce.book.FileHandler;

import strandkart.ecommerce.book.Binding;
import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.product.productstype.ProductType;

import java.io.*;
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
            Binding binding = null;
            String bindingName = dataArray.get(6);
            if(bindingName.equals("Hardbound")){
                binding = Binding.HARDBOUND;
            }
            else if(bindingName.equals("Paperback")){
                binding = Binding.PAPERBACK;
            }
            else if(bindingName.equals("Digital")){
                binding = Binding.DIGITAL;
            }
            Book book = new Book(ProductType.BOOKS, dataArray.get(0), dataArray.get(1), dataArray.get(2),
                    dataArray.get(3), dataArray.get(4), dataArray.get(5), binding, Double.parseDouble(dataArray.get(7)));
            books.add(book);
            line = reader.readLine();
        }
        return books;
    }

    public void writeToFile(List<Book> books) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for(Book book : books){
            writer.write(book.getTitle()+"\t"+book.getAuthor()+"\t"+book.getISBN()+"\t" +
                    book.getPublisher()+"\t"+book.getLanguage()+"\t"+book.getYear()+"\t"+
                    book.getBinding().name()+"\t"+book.getPrice().toString()+"\n");
        }

    }

}
