package com.trendcore.state.refactoredCode;

import com.trendcore.state.problematicCode.Player;

public class OwnedProperty implements State {

    @Override
    public void rentProperty(Property property, Player p) {
        System.out.println(" - owned by " + property.getOwner().getName());
        if (p != property.getOwner()) {
            p.debit(property.getRent());
            property.getOwner().credit(property.getRent());
            System.out.println(property.getOwner().getName() + " now has "
                    + property.getOwner().getWorth() + " dollars");
        }
    }
}
