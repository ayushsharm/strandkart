package strandkart.ecommerce.orders.impl;

import strandkart.ecommerce.book.datamodel.Book;
import strandkart.ecommerce.orders.datamodels.Order;
import strandkart.ecommerce.orders.service.OrderManagement;

import java.util.ArrayList;
import java.util.List;


public class OrderManagementImpl implements OrderManagement {


    private List<Order> orders = new ArrayList<Order>();

    @Override
    public void orderBook(Book book, int quantity, Double orderAmount) {
        Order order = new Order(book, quantity, orderAmount);
        Order order1 = containsOrder(order);
        if (order1 != null) {
            order1.increaseQuantity(order.getQuantity());
        } else {
            orders.add(order);
        }
    }

    private Order containsOrder(Order order1) {
        for (Order order : orders) {
            if (order.getBook().getTitle().equals(order1.getBook().getTitle()) &&
                    order.getBook().getISBN().equals(order1.getBook().getISBN())){
                return order;
            }
        }
        return null;
    }

    @Override
    public void orderBook(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> showAllOrders() {
        return orders;
    }
}
