package item;

import dungeon.Monster;

public abstract class Weapon {
    private String name;
    private int price;
    private Monster target;

    public Weapon(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public abstract void attack();



    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Monster getTarget() {
        return target;
    }

    public void setTarget(Monster target) {
        this.target = target;
    }
}
