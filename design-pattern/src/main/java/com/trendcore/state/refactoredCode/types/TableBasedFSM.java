package com.trendcore.state.refactoredCode.types;

/**
 * Class representing FSM.
 */
// "wrapper" class that models the state machine
public class TableBasedFSM {

    // 2. states
    private State[] states = {new A(), new B(), new C()};

    //4. transition
    private int[][] transition = {{2, 1, 0}, {0, 2, 1}, {1, 2, 2}};

    // 3. current
    private int current = 0;

    interface State {
        void on();

        void off();

        void ack();
    }

    class A implements State {
        @Override
        public void on() {
            System.out.println("A + on = C");
        }

        @Override
        public void off() {
            System.out.println("A + on = B");
        }

        @Override
        public void ack() {
            System.out.println("A + ack = A");
        }
    }

    class B implements State {
        @Override
        public void on() {
            System.out.println("B + on  = A");
        }

        @Override
        public void off() {
            System.out.println("B + off = C");
        }

        @Override
        public void ack() {
            System.out.println("Error.");
        }
    }

    class C implements State {
        @Override
        public void on() {
            System.out.println("C + on  = B");
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


    public static void main(String[] args) {
        TableBasedFSM fsm = new TableBasedFSM();
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

    private void ack() {
        states[current].ack();
        next(2);
    }

    private void off() {
        states[current].off();
        next(1);
    }

    private void on() {
        states[current].on();
        next(0);
    }

    private void next(int msg) {
        //lookup from transition table.
        current = transition[current][msg];
    }

}
