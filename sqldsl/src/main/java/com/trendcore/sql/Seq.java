package com.trendcore.sql;

public class Seq {

    private int val;

    public Seq() {

    }

    public Seq(int i) {
        this.val = i;
    }

    public Integer next(){
        return val++;
    }

    public Integer val(){
        return val;
    }

}
