package com.trendcore.state.refactoredCode.types;

public class C implements State {

    private IndividualStateDerivedClassBasedFSM fsm;

    public C(IndividualStateDerivedClassBasedFSM context) {
        this.fsm = context;
    }

    @Override
    public void on() {
        System.out.println("C + on = B");
        this.fsm.setCurrentState(fsm.getB());
    }

    @Override
    public void off() {
        System.out.println("Error.");
    }

    @Override
    public void ack() {
        System.out.println("Error.");
    }
}
