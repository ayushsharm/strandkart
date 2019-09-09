package strandkart.ecommerce.cart.service;


import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.orders.DataModels.Order;

import java.util.List;

public interface CartManagementService {

    void addToCart(Order order);

    List<Order> showCart();

    void orderCart();


}
