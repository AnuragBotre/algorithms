package com.trendcore.state.refactoredCode;

import com.trendcore.state.problematicCode.Player;

public class ClientForPropertyRent {

    public static void main(String[] args) {
        Player p1 = new Player("Tom", 50);
        Player p2 = new Player("   Dick", 500);
        Property prop = new Property("Boardwalk");
        prop.landOnBy(p1);
        prop.landOnBy(p2);
        prop.landOnBy(p1);
        prop.landOnBy(p2);
        prop.landOnBy(p1);
    }

}
