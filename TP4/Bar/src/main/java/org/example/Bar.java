package org.example;

import static org.example.Bar.*;

public class Bar {

    public Bar() {
    }

    public double computeCost(Drink drink, boolean student, int amount) {
        if (amount > 2 && drink == Drink.BACARDI_SPECIAL) {
            throw new RuntimeException("Too many drinks");
        }
        double price = drink.getPrice();
        if (student) {
            price -= price/10;
        }
        return price * amount;
    }
}

enum Drink{
    PILS,
    MAZOUT,
    CIDER,
    CHIMAY,
    VAL_DIEU,
    HOUPPE,
    BACARDI_SPECIAL;

    public double getPrice(){
        switch (this){
            case PILS -> {
                return 2;
            }
            case MAZOUT -> {
                return 2 + Ingredient.COKE.getPrice();
            }
            case CIDER -> {
                return 2.5;
            }
            case CHIMAY -> {
                return 4;
            }
            case VAL_DIEU, HOUPPE -> {
                return 3.5;
            }
            case BACARDI_SPECIAL -> {
                return 2 + Ingredient.GIN.getPrice()/2
                        + Ingredient.RUM.getPrice()
                        + Ingredient.GRENADINE.getPrice()
                        + Ingredient.LIME_JUICE.getPrice();
            }
        }
        return 0;
    }
}

enum Ingredient{
    COKE,
    GIN,
    RUM,
    LIME_JUICE,
    GRENADINE,
    ICE;

    public double getPrice(){
        switch (this){
            case COKE, LIME_JUICE, GRENADINE -> {
                return 0.5;
            }
            case GIN -> {
                return 1;
            }
            case RUM -> {
                return 2;
            }
            case ICE -> {
                return 0.75;
            }
        }
        return 0;
    }
}

class Main {
    public static void main(String[] args) {
        Bar bar = new Bar();
        System.out.println(bar.computeCost(Drink.BACARDI_SPECIAL, true, 2));
        System.out.println(bar.computeCost(Drink.PILS, false, 5));
        System.out.println(bar.computeCost(Drink.HOUPPE, true, 3));
    }
}
