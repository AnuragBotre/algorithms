package com.trendcore.problems.learning;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class CombinationOfNumbers {

    public static void main(String[] args) {
        CombinationOfNumbers c = new CombinationOfNumbers();

        Set<String> collector = new HashSet<>();

        c.printCombinations(3,collector);

        collector.forEach(System.out::println);
    }

    private void printCombinations(int noOfCombinations, Set<String> collector) {
        String s = "";
        for (int i = 0; i < noOfCombinations; i++) {
            s = i + ",";
            doForRemaining(s, noOfCombinations , collector);
        }
    }

    private void doForRemaining(String s, int noOfCombinations, Set<String> collector) {
        if (s.split(",").length == noOfCombinations) {
            //System.out.println(s);
            //Hash codes are not getting same.
            collector.add(s);
            return;
        }

        for (int i = 0; i < noOfCombinations; i++) {
            if (!contains(i, s)) {
                s = s + i + ",";
                doForRemaining(s, noOfCombinations, collector);
            }
        }


        //take out last element
        String s1 = s;
        String[] split = s1.split(",");
        if (split.length == noOfCombinations) {

            if(split[0].equals(""+(noOfCombinations-1))){
                return;
            }
            s = "";
            for (int i = 0; i < split.length; i++) {
                if (i == split.length - 2) {
                    s = s + split[i] + ",";
                }
            }
            s = s + split[split.length - 1] + ",";
            doForRemaining(s,noOfCombinations, collector);
        }
    }

    private boolean contains(int i, String s) {
        StringTokenizer tokenizer = new StringTokenizer(s, ",");
        while (tokenizer.hasMoreElements()) {
            String s1 = tokenizer.nextToken();
            if (s1.trim().equals("" + i)) {
                return true;
            }
        }

        return false;
    }

    /*private void printCombinations(int noOfCombinations) {
        for (int i = 0; i < noOfCombinations; i++) {
            //get ith element and insert in between except for i
            //insert xth at position
            //ith ele
            insert(i, noOfCombinations);

        }
    }

    private void insert(int i, int noOfCombinations) {
        for (int positions = 0; positions < noOfCombinations; positions++) {
            //insert at position
            printAtPosition(i, positions, noOfCombinations);
            System.out.println();
        }
    }

    private void printAtPosition(int i, int positions, int noOfCombinations) {
        int otherElements = i == 0 ? i + 1 : 0;
        for (int j = 0; j < noOfCombinations; j++) {

            if (otherElements == i) {
                otherElements = i + 1;
            }

            if (otherElements >= noOfCombinations) {
                otherElements = 0;
            }


            if (j == positions) {
                System.out.print(" " + i);
            } else {
                System.out.print(" " + otherElements);
                otherElements++;
            }
        }
    }*/

}
