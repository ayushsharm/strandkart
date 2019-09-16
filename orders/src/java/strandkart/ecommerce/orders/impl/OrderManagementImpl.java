package strandkart.ecommerce.orders.impl;

import strandkart.ecommerce.book.datamodel.Book;
import strandkart.ecommerce.orders.datamodels.Order;
import strandkart.ecommerce.orders.service.OrderManagement;

import java.util.ArrayList;
import java.util.List;


public class OrderManagementImpl implements OrderManagement {


    private List<Order> orders = new ArrayList<Order>();

    public void orderBook(Book book, int quantity, Double orderAmount) {
        Order order = new Order(book, quantity, orderAmount);
        orders.add(order);
    }

    public void orderBook(Order order){
        orders.add(order);
    }

    public List<Order> showAllOrders() {
        return orders;
    }
}
