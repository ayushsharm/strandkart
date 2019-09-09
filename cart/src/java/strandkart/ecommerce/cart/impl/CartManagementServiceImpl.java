package strandkart.ecommerce.cart.impl;

import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.cart.service.CartManagementService;

import java.util.Collections;
import java.util.List;

public class CartManagementServiceImpl implements CartManagementService {

    public CartManagementServiceImpl() {}

    private List<Book> cart = Collections.emptyList();

    public void addToCart(Book book) {
        cart.add(book);
    }

    public List<Book> showCart() {
        return cart;
    }

    public void orderCart() {
        for (Book book : cart) {
            //Add from order service
        }
    }
}
