package org.example;

import static org.example.Bar.*;

public class Bar {
    public static final String PILS = "Maes";
    public static final String MAZOUT = "Mazout";
    public static final String CIDER = "Strongbow";
    public static final String CHIMAY = "Chimay bleue";
    public static final String VAL_DIEU = "Triple Val-Dieu";
    public static final String HOUPPE = "Houppe";

    public static final String BACARDI_SPECIAL = "Bacardi special";

    public Bar() {
    }

    public double computeCost(String drink, boolean student, int amount) {
        if (amount > 2 && drink.equals(BACARDI_SPECIAL)) {
            throw new RuntimeException("Too many drinks");
        }
        double price;
        switch (drink) {
            case PILS:
                price = 2;
                break;
            case MAZOUT:
                price = ingredient1() + 2;
                break;
            case CIDER:
                price = 2.5;
                break;
            case CHIMAY:
                price = 4;
                break;
            case VAL_DIEU:
                price = 3.5;
                break;
            case HOUPPE:
                price = 3.5;
                break;
            case BACARDI_SPECIAL:
                price = ingredient2()/2 + ingredient3() + ingredient5() + ingredient4();
                break;
            default:
                price = 0.0;
        }
        if (student) {
            price = price - price/10;
        }
        return price * amount;
    }

    // one unit of coca-cola
    private double ingredient1() {
        return 0.5;
    }
    // one unit of gin
    private double ingredient2() {
        return 1;
    }
    // one unit of rum
    private double ingredient3() {
        return 2;
    }
    // one unit of lime juice
    private double ingredient4() {
        return 0.5;
    }
    // one unit of grenadine
    private double ingredient5() {
        return 0.5;
    }

    // one unit of ice
    private double ingredient6() {
        return 0.75;
    }
}

class Main {
    public static void main(String[] args) {
        Bar bar = new Bar();
        System.out.println(bar.computeCost(BACARDI_SPECIAL, true, 2));
        System.out.println(bar.computeCost(PILS, false, 5));
        System.out.println(bar.computeCost(HOUPPE, true, 3));
    }
}
