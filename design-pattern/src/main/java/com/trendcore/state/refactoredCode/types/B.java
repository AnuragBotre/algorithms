package com.trendcore.state.refactoredCode.types;

public class B implements State{

    private IndividualStateDerivedClassBasedFSM fsm;

    public B(IndividualStateDerivedClassBasedFSM context) {
        fsm = context;
    }

    @Override
    public void on() {
        System.out.println("B + on = A");
        fsm.setCurrentState(fsm.getA());
    }

    @Override
    public void off() {
        System.out.println("B + off = C");
        fsm.setCurrentState(fsm.getC());
    }

    @Override
    public void ack() {
        System.out.println("Error.");
    }
}
