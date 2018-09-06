package com.trendcore.problems.learning;

public class CombinationOfNumbers {

    public static void main(String[] args) {
        CombinationOfNumbers c = new CombinationOfNumbers();

        c.printCombinations(3);
    }

    private void printCombinations(int noOfCombinations) {
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
    }

}
