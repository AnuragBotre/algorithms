package com.trendcore.state.refactoredCode.types;

public class A implements State{

    private IndividualStateDerivedClassBasedFSM fsmContext;

    public A(IndividualStateDerivedClassBasedFSM context) {
        fsmContext = context;
    }

    @Override
    public void on() {
        System.out.println("A + on = C");
        fsmContext.setCurrentState(fsmContext.getC());
    }

    @Override
    public void off() {
        System.out.println("A + off = B");
        fsmContext.setCurrentState(fsmContext.getB());
    }

    @Override
    public void ack() {
        System.out.println("A + ack = A");
        fsmContext.setCurrentState(fsmContext.getA());
    }
}
