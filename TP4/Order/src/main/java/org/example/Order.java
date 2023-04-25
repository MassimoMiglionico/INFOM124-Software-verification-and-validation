package org.example;

public class Order {
    private Item[] items;
    private String idClient;
    private Offer offer;

    public Order(Item[] items, String idClient, Offer offer) {
        this.items = items;
        this.idClient = idClient;
        this.offer = offer;
    }

    public double getOrderTotal() {
        double totalPrice = 0;
        for (Item item : items) {
            totalPrice = totalPrice + item.getPrice();
        }
        int shippingCharge = 0;
        if (totalPrice < 500) {
            shippingCharge = 50;
        } else if (totalPrice >= 500 && totalPrice < 1000) {
            shippingCharge = 30;
        } else {
            shippingCharge = 10;
        }
        totalPrice = totalPrice + shippingCharge;
        double discount = 0;
        if (offer.isValid()) {
            discount = offer.getDiscountAmount();
        }
        totalPrice = totalPrice - discount;
        return totalPrice;
    }
}

class Item {
    private double price;
    private String name;
    private String idNumber;

    public Item(double price, String name, String idNumber) {
        this.price = price;
        this.name = name;
        this.idNumber = idNumber;
    }

    public double getPrice() {
        return price;
    }
}

class Offer {
    private boolean isValid;
    private double discountAmount;

    public Offer(boolean isValid, double discountAmount) {
        this.isValid = isValid;
        this.discountAmount = discountAmount;
    }

    public boolean isValid() {
        return isValid;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }
}

class Main {
    public static void main(String[] args) {
        Item computer = new Item(500, "Lenovo computer", "12345");
        Item banana = new Item(2, "Banana", "23456");
        Item screen = new Item(150, "Philips screen", "34567");
        Item oak = new Item(2000, "150 hundred years oak tree", "150");
        Item[] itemsUser1 = new Item[] {computer, banana};
        Item[] itemsUser2 = new Item[] {screen, oak};

        Offer offerUser1 = new Offer(true, 0.1);
        Offer offerUser2 = new Offer(true, 0);
        Order orderUser1 = new Order(itemsUser1, "0", offerUser1);
        System.out.println(orderUser1.getOrderTotal());
        Order orderUser2 = new Order(itemsUser2, "1", offerUser2);
        System.out.println(orderUser2.getOrderTotal());
    }
}