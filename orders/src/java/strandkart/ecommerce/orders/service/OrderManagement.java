package strandkart.ecommerce.orders.service;

import strandkart.ecommerce.book.datamodel.Book;
import strandkart.ecommerce.orders.datamodels.Order;

import java.util.List;

public interface OrderManagement {

    void orderBook(Book book, int quantity, Double orderAmount);

    List<Order> showAllOrders();

    void orderBook(Order order);

}
