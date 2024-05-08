package item;

import dungeon.Monster;

import java.util.Objects;

public abstract class Weapon {
    private String name;
    private int price;
    private int damage;



    public Weapon(String name, int price, int damage) {
        this.name = name;
        this.price = price;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void attack(Monster monster, boolean battleOver);
}
