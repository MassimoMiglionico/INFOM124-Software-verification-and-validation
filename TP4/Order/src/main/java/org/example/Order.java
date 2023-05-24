package org.example;

public class Order {
    private Item[] items;
    private Offer offer;

    public Order(Item[] items, Offer offer) {
        this.items = items;
        this.offer = offer;
    }

    public double getOrderTotal() {
        double totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        totalPrice += shippingCharge(totalPrice);

        if (offer.isValid()) {
            totalPrice -= offer.getDiscountAmount();
        }
        return totalPrice;
    }

    private int shippingCharge(double price){
        if(price < 500){
            return 50;
        }
        else if(price < 1000){
            return 30;
        }
        else {
            return 10;
        }
    }
}

class Item {
    private double price;

    public Item(double price) {
        this.price = price;
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
        Item computer = new Item(500);
        Item banana = new Item(2);
        Item screen = new Item(150);
        Item oak = new Item(2000);
        Item[] itemsUser1 = new Item[] {computer, banana};
        Item[] itemsUser2 = new Item[] {screen, oak};

        Offer offerUser1 = new Offer(true, 0.1);
        Offer offerUser2 = new Offer(true, 0);
        Order orderUser1 = new Order(itemsUser1, offerUser1);
        System.out.println(orderUser1.getOrderTotal());
        Order orderUser2 = new Order(itemsUser2, offerUser2);
        System.out.println(orderUser2.getOrderTotal());
    }
}