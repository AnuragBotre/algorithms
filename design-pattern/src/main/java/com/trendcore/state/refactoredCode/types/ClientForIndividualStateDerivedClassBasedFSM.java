package com.trendcore.state.refactoredCode.types;

public class ClientForIndividualStateDerivedClassBasedFSM {

    public static void main(String[] args) {
        IndividualStateDerivedClassBasedFSM fsm = new IndividualStateDerivedClassBasedFSM();

        int msgs[] = {2, 1, 2, 1, 0, 2, 0, 0};

        for (int msg : msgs) {
            if (msg == 0) {
                fsm.on();
            } else if (msg == 1) {
                fsm.off();
            } else {
                fsm.ack();
            }
        }

    }

}
