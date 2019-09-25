package strandkart.ecommerce.cart.impl;

import strandkart.ecommerce.cart.service.CartManagementService;
import strandkart.ecommerce.orders.datamodels.Order;

import java.util.ArrayList;
import java.util.List;

public class CartManagementServiceImpl implements CartManagementService {

    private List<Order> cart = new ArrayList<Order>();

    public CartManagementServiceImpl() {}

    @Override
    public void addToCart(Order order) {
        cart.add(order);
    }

    @Override
    public List<Order> showCart() {
        return cart;
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public void removeBook(int index) {
        cart.remove(index);
    }
}
