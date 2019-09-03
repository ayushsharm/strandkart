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
            int i=0;
            Book book = new Book(dataArray.get(i++), dataArray.get(i++), dataArray.get(i++),
                    dataArray.get(i++), dataArray.get(i++), dataArray.get(i++), binding, Double.parseDouble(dataArray.get(++i)));
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
