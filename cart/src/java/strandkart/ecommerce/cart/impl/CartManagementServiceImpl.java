package strandkart.ecommerce.cart.impl;

import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.cart.service.CartManagementService;
import strandkart.ecommerce.orders.DataModels.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartManagementServiceImpl implements CartManagementService {

    public CartManagementServiceImpl() {}

    private List<Order> cart = new ArrayList<Order>();

    public void addToCart(Order order) {
        cart.add(order);
    }

    public List<Order> showCart() {
        return cart;
    }

    public void orderCart() {
        for (Order order : cart) {

            //Add from order service
        }
    }
}
