package com.trendcore.sql;

public class Seq {

    private int i;

    public Integer next(){
        return i++;
    }

    public Integer val(){
        return i;
    }

}
