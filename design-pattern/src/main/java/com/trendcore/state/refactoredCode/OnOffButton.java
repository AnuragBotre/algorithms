package com.trendcore.state.refactoredCode;

public class OnOffButton {

    private State currentState;

    public OnOffButton(State currentState) {
        this.currentState = currentState;
    }


    interface State {

        void push(OnOffButton onOffButton);
    }


    private static class Off implements State {

        @Override
        public void push(OnOffButton onOffButton) {
            System.out.println("Moving to On state.");
            onOffButton.setState(new On());
        }


    }

    private static class On implements State {

        @Override
        public void push(OnOffButton onOffButton) {
            System.out.println("Moving to Off state.");
            onOffButton.setState(new Off());
        }
    }

    private void setState(State newState) {
        this.currentState = newState;
    }

    private void push() {
        currentState.push(this);
    }

    public static void main(String[] args) {
        OnOffButton onOffButton = new OnOffButton(new Off());

        onOffButton.push();
        onOffButton.push();
        onOffButton.push();
        onOffButton.push();
        onOffButton.push();
    }
}
