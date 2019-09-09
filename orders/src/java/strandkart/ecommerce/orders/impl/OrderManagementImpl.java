package strandkart.ecommerce.orders.impl;

import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.orders.DataModels.Order;
import strandkart.ecommerce.orders.service.OrderManagement;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public class OrderManagementImpl implements OrderManagement {


    private List<Order> orders = Collections.emptyList();

    public void orderBook(Book book, int quantity, Date purchaseDate, Float orderAmount) {
        Order order = new Order(book, quantity, purchaseDate, orderAmount);
        orders.add(order);
    }

    public List<Order> showAllOrders() {
        return orders;
    }
}
