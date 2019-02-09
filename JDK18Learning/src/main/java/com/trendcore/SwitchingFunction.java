package com.trendcore;

import java.util.function.Function;

public class SwitchingFunction {

    public static class Algorithm {

        int condition;

        Function function;

        public Algorithm(int condition){
            this.condition = condition;
        }

        public Algorithm function(Function function) {
            this.function = function;
            return this;
        }

        public void doIfConditionSatisfies() {
            checkConditionAndExecuteFunction.apply("test");
        }

        public Function checkConditionAndExecuteFunction =  (test) -> {
            if (condition == 2) {
                //once the condition is met
                //then do not check this
                System.out.println("Inside if");
                //condition = 0;
                checkConditionAndExecuteFunction = function;
                return function.apply("test");
            }
            return 0;
        };

    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm(2);
        algorithm.function(o -> {
            System.out.println(o);
            return "test1";
        });

        algorithm.doIfConditionSatisfies();
        algorithm.doIfConditionSatisfies();
        algorithm.doIfConditionSatisfies();
    }

}
