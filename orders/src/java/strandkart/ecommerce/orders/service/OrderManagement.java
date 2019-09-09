package strandkart.ecommerce.orders.service;

import strandkart.ecommerce.book.Datamodel.Book;
import strandkart.ecommerce.orders.DataModels.Order;

import java.util.Date;
import java.util.List;

public interface OrderManagement {

    void orderBook(Book book, int quantity, Date purchaseDate, Float orderAmount);

    List<Order> showAllOrders();

}
