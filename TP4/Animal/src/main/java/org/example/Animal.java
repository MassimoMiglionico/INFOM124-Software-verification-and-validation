package org.example;

public class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
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

    public Bird(String name, boolean canFly) {
        super(name);
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


class Main {
    public static void main(String[] args) {
        Animal ostrich = new Animal("Ostrich le tich");
        ostrich.makeSound();

        Bird chicken = new Bird("Lapin", true);
        chicken.fly();
    }
}