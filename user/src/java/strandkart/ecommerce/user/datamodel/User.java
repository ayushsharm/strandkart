package strandkart.ecommerce.user.datamodel;

public class User {

    private String name;

    private String contactNumber;

    public User(String name, String contactNumber) {
        this.contactNumber = contactNumber;
        this.name = name;
    }

    public String getUserName() {
        return name;
    }

    public String getUserContactNumber() {
        return contactNumber;
    }
}
