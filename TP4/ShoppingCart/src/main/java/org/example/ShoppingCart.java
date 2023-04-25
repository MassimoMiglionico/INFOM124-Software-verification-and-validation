package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;
    private double totalPrice;
    private String currency;
    private double exchangeRate;

    public ShoppingCart(String currency, double exchangeRate) {
        this.items = new ArrayList<>();
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }
    public void addItem(Item item) {
        this.items.add(item);
    }
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    public void calculateTotalPrice() {
        double tempPrice = 0.0;
        for (Item item : items) {
            tempPrice += item.getPrice();
        }
        this.totalPrice = tempPrice * this.exchangeRate;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopping Cart Details:\n");
        sb.append("Currency: ").append(this.currency).append("\n");
        sb.append("Exchange rate: ").append(this.exchangeRate).append("\n");
        sb.append("Items:\n");

        for (Item item : this.items) {
            sb.append("- ").append(item.getName()).append(", ").append(item.getPrice()).append(" ").append(this.currency).append("\n");
        }
        sb.append("The total price in ").append(this.currency).append(" is: ").append(this.totalPrice);
        return sb.toString();
    }
}

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart("USD", 1.0);

        Item item1 = new Item("Shirt", 20.0);
        Item item2 = new Item("Pants", 30.0);

        cart.addItem(item1);
        cart.addItem(item2);

        cart.setExchangeRate(0.85);
        cart.setCurrency("EUR");
        cart.calculateTotalPrice();

        System.out.println(cart);
    }
}
