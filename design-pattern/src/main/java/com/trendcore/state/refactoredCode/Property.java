package com.trendcore.state.refactoredCode;

import com.trendcore.state.problematicCode.Player;

public class Property {

    private String name;
    private int price;
    private int rent;
    private Player owner;

    private State state;

    public Property(String name) {
        this.name = name;
        price = 100;
        rent = 10;
        state = new NonOwnedProperty();
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
        state.rentProperty(this, p);
    }

    public void boughtProperty(Player p, State state) {
        p.debit(getPrice());
        setOwner(p);
        setState(state);
    }

    private void setState(State state) {
        this.state = state;
    }

    public void rentToPlayer(Player p) {
        p.debit(getRent());
        getOwner().credit(getRent());
    }
}
