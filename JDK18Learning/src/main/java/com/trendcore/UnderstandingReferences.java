package com.trendcore;

/**
 * Created by Anurag
 */
public class UnderstandingReferences {

    public static void main(String[] args) {
        StringBuilder x = new StringBuilder("M");
        StringBuilder y = new StringBuilder("N");
        merge(x,y);
        System.out.println(x + " ," + y);
    }

    private static void merge(StringBuilder x, StringBuilder y) {
        x.append("N");
        y = x;
    }

}
