package com.trendcore.state.problematicCode;

public class Property {

    private String name;
    private int price;
    private int rent;
    private Player owner;

    public Property(String name) {
        this.name = name;
        price = 100;
        rent = 10;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player p) {
        owner = p;
    }

    void landOnBy(Player p) {
        System.out.print(p.getName() + " landed on " + name);
        if (getOwner() == null) {
            System.out.print(" - not owned\n" + p.getName());
            if (p.getWorth() < getPrice()) {
                System.out.println(" does not have enough money to purchase");
            } else {
                p.debit(getPrice());
                setOwner(p);
                System.out.println(" bought " + getName());
            }
        } else {
            System.out.println(" - owned by " + getOwner().getName());
            if (p != getOwner()) {
                p.debit(getRent());
                getOwner().credit(getRent());
                System.out.println(getOwner().getName() + " now has "
                        + getOwner().getWorth() + " dollars");
            }
        }
        System.out.println(p.getName() + " has " + p.getWorth()
                + " dollars");
    }

}
