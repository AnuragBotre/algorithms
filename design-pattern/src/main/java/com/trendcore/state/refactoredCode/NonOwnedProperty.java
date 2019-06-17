package com.trendcore.state.refactoredCode;

import com.trendcore.state.problematicCode.Player;

public class NonOwnedProperty implements State {

    @Override
    public void rentProperty(Property property, Player p) {
        if (p.getWorth() < property.getPrice()) {
            System.out.println(" does not have enough money to purchase");
        } else {
            property.boughtProperty(p,new OwnedProperty());
            System.out.println(" bought " + property.getName());
        }
    }
}
