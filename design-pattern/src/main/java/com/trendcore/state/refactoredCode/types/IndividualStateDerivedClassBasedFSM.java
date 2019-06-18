package com.trendcore.state.refactoredCode.types;

public class IndividualStateDerivedClassBasedFSM {

    private State states[];

    A a = new A(this);
    B b = new B(this);
    C c = new C(this);

    {
        states = new State[]{a, b, c};
    }

    private State currentState = a;

    public void on() {
        currentState.on();
    }

    public void off() {
        currentState.off();
    }

    public void ack() {
        currentState.ack();
    }

    public State getC() {
        return c;
    }

    public State getA() {
        return a;
    }

    public State getB() {
        return b;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
