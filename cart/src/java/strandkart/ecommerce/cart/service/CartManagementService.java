package strandkart.ecommerce.cart.service;


import strandkart.ecommerce.book.Datamodel.Book;

import java.util.List;

public interface CartManagementService {

    void addToCart(Book book);

    List<Book> showCart();

    void orderCart();

}
