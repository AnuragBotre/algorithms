package com.trendcore.state.refactoredCode;

public class CeilingFanPullChain {

    private State state;

    public CeilingFanPullChain() {
        this.state = new Off();
    }

    public void setState(State chain) {
        this.state = chain;
    }

    public void pull() {
        state.pull(this);
    }

    /*----------------------------   Consider This is in another files.  ------------------------------------------------*/
    interface State{

        void pull(CeilingFanPullChain context);

    }

    private class Off implements State {
        @Override
        public void pull(CeilingFanPullChain ceilingFanPullChain) {
            System.out.println("Moving to Low Speed");
            ceilingFanPullChain.setState(new LowSpeed());
        }
    }


    private class LowSpeed implements State {

        @Override
        public void pull(CeilingFanPullChain ceilingFanPullChain) {
            System.out.println("Moving to Medium Speed");
            ceilingFanPullChain.setState(new MediumSpeedState());
        }
    }

    private class MediumSpeedState implements State {
        @Override
        public void pull(CeilingFanPullChain ceilingFanPullChain) {
            System.out.println("Moving to High Speed");
            ceilingFanPullChain.setState(new HighSpeedState());
        }
    }

    private class HighSpeedState implements State {
        @Override
        public void pull(CeilingFanPullChain ceilingFanPullChain) {
            System.out.println("Moving to Off Speed");
            ceilingFanPullChain.setState(new Off());
        }
    }

    public static void main(String[] args) {
        CeilingFanPullChain ceilingFanPullChain = new CeilingFanPullChain();
        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();
        ceilingFanPullChain.pull();
    }


}
