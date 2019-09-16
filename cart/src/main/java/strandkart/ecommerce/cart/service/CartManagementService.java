package strandkart.ecommerce.cart.service;

import strandkart.ecommerce.orders.datamodels.Order;

import java.util.List;

public interface CartManagementService {

    void addToCart(Order order);

    List<Order> showCart();

}
