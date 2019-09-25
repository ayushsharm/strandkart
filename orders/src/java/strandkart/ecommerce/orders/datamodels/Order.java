package strandkart.ecommerce.orders.datamodels;

import strandkart.ecommerce.book.datamodel.Book;
//import strandkart.ecommerce.user.datamodel.User;


public class Order {

    private Book book;

//    private User user;

    private Integer quantity;

//    private Date purchaseDate;

    private Double orderAmount;

    public Order(Book book, int quantity, Double orderAmount) {
        this.book = book;
//        this.user = user;
        this.quantity = quantity;
//        this.purchaseDate = purchaseDate;
        this.orderAmount = orderAmount;
    }

    public Book getBook() {
        return book;
    }

//    public User getUser() {
//        return user;
//    }

    public Integer getQuantity() {
        return quantity;
    }

//    public Date getPurchaseDate() {
//        return purchaseDate;
//    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public Order increaseQuantity(int quantity) {
        this.quantity += quantity;
        return this;
    }

    @Override
    public String toString() {
        return book.toString() + "Quantity : " + quantity + "\nOrder Amount : ₹" + orderAmount;
    }

}
