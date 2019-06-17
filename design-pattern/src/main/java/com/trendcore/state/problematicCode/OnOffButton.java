package com.trendcore.state.problematicCode;

public class OnOffButton {

    private int currentState;

    public OnOffButton(int currentState) {
        this.currentState = currentState;
    }

    public void push() {
        if (currentState == 0) {
            System.out.println("Switching On");
            currentState = 1;
        } else {
            System.out.println("Switching Off");
            currentState = 0;
        }
    }

    public static void main(String[] args) {
        OnOffButton onOffButton = new OnOffButton(0);
        onOffButton.push();
        onOffButton.push();
        onOffButton.push();
    }


}
