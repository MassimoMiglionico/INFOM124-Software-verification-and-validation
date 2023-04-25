package org.example;

import java.sql.Array;
import java.util.List;

enum ItemType {
    SWORD,
    ARMOR,
    POTION,
    FOOD
}

public class Hero {
    private String name;
    private int age;
    private int hp;
    private final ItemType[] inventory;
    private final int mana;
    private final String heroClass;
    private final int attack;
    private final int defend;

    public Hero(String name, int age, int hp, ItemType[] inventory, int mana, String heroClass, int attack, int defend) {
        this.name = name;
        this.age = age;
        this.hp = hp;
        this.inventory = inventory;
        this.mana = mana;
        this.heroClass = heroClass;
        this.attack = attack;
        this.defend = defend;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getDefend() {
        return defend;
    }

    public void attack(Hero enemy) {
        enemy.setHp(enemy.getHp() - (attack - enemy.getDefend()));
    }

    public String getInventory() {
        StringBuilder str = new StringBuilder();
        for (ItemType itemType : inventory) {
            str.append(itemType).append(" ");
        }
        return str.toString();
    }
}

class Main {
    public static void main(String[] args) {
        ItemType[] inventory = {ItemType.SWORD, ItemType.FOOD};
        Hero hero = new Hero("Zarakaï", 35, 100, inventory, 50, "Nain", 8, 4);
        System.out.println(hero.getInventory());
        Hero enemy = new Hero("Enoriel", 30, 80, new ItemType[]{}, 80, "Elfe", 6, 3);
        hero.attack(enemy);
        System.out.println(enemy.getHp());

    }
}