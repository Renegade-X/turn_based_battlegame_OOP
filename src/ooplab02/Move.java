package ooplab02;

// Move class to hold move data

public class Move {
    private String name;
    private int damage;
    private String type;

    public Move(String name, int damage, String type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }
}



    

