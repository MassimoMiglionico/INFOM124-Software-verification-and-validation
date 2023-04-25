package org.example;

public class User {
    private int id;
    private String name;
    private String[] privileges;
    private ContactDetails contactDetails;

    public User(int id, String name, String[] privileges, ContactDetails contactDetails) {
        this.id = id;
        this.name = name;
        this.privileges = privileges;
        this.contactDetails = contactDetails;
    }


}

class ContactDetails {
    private String streetNumber;
    private String streetName;
    private String phoneNumber;

    public ContactDetails(String streetNumber, String streetName, String phoneNumber) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.phoneNumber = phoneNumber;
    }
}