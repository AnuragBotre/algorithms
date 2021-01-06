package com.trendcore.spring.beans;

public class PrintableFactory {

    public Printable getPrintable(){
        //return new PrintableImpl2()
        //or
        return new PrintableImpl1();
    }
}
