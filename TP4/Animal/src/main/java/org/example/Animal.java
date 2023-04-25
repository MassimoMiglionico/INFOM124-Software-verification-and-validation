package org.example;

public class Animal {
    private String name;
    private String species;

    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }
    public void makeSound() {
        System.out.println("Animal sound");
    }

    public String getName() {
        return name;
    }
}

class Bird extends Animal {
    private boolean canFly;

    public Bird(String name, String species, boolean canFly) {
        super(name, species);
        this.canFly = canFly;
    }
    public void fly() {
        if (canFly) {
            System.out.println(this.getName() + " is flying!");
        } else {
            System.out.println(this.getName() + " cannot fly :/");
        }
    }
}

class Ostrich extends Animal {
    public Ostrich(String name, String species) {
        super(name, species);
    }
}

class Main {
    public static void main(String[] args) {
        Ostrich ostrich = new Ostrich("Ostrich le tich", "ostrich");
        ostrich.makeSound();

        Bird chicken = new Bird("Lapin", "chicken", true);
        chicken.fly();
    }
}