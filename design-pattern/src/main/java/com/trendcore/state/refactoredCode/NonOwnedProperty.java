package com.trendcore.state.refactoredCode;

import com.trendcore.state.problematicCode.Player;

public class NonOwnedProperty implements State {

    @Override
    public void rentProperty(Property property, Player p) {
        if (p.getWorth() < property.getPrice()) {
            System.out.println(" does not have enough money to purchase");
        } else {
            p.debit(property.getPrice());
            property.setOwner(p);
            System.out.println(" bought " + property.getName());
        }
    }
}
