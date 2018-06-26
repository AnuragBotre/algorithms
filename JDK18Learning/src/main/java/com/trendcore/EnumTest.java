package com.trendcore;

public enum EnumTest implements SomeInterface{

    USER{
        @Override
        public void method1() {
            System.out.println("User");
        }
    };



}
