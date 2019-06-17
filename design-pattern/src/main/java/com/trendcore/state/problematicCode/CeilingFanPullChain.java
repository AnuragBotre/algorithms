package com.trendcore.state.problematicCode;

public class CeilingFanPullChain {

    private int currentState;

    CeilingFanPullChain() {
        currentState = 0;
    }

    public void pull() {

        if (currentState == 0) {

            currentState = 1;
            System.out.println("Low Speed");

        } else if (currentState == 1) {

            currentState = 2;
            System.out.println("Medium Speed");

        } else if (currentState == 2) {

            currentState = 3;
            System.out.println("High Speed");

        } else {

            currentState = 0;
            System.out.println("Switching off Fan.");
        }
    }

    public static void main(String[] args) {
        CeilingFanPullChain ceilingFanPullChain = new CeilingFanPullChain();

        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();

    }

}
