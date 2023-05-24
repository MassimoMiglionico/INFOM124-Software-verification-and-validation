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
    private int hp;
    private final ItemType[] inventory;
    private final int attack;
    private final int defend;

    public Hero(int hp, ItemType[] inventory, int attack, int defend) {
        this.hp = hp;
        this.inventory = inventory;
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
        Hero hero = new Hero(35, inventory, 8, 4);
        System.out.println(hero.getInventory());
        Hero enemy = new Hero(30, new ItemType[]{}, 6, 3);
        hero.attack(enemy);
        System.out.println(enemy.getHp());

    }
}