package com.trendcore.problems;

import java.util.*;

/**
 * Created by Anurag
 */
public class PrintArrayInSpiral {

    public static void main(String[] args) {

        int inputArray[][] = new int[4][4];

        inputArray[0] = new int[]{1, 2, 3, 4};
        inputArray[1] = new int[]{5, 6, 7, 8};
        inputArray[2] = new int[]{9, 10, 11, 12};
        inputArray[3] = new int[]{13, 14, 15, 16};


        printArrayInSpiral(inputArray);
    }

    private static void printArrayInSpiral(int[][] inputArray) {

        //traverse from left to right
        //at the end top to bottom
        //right to left
        //bottom to top
        //repeat the flush until all nodes are visited

        Map visitedNodes = new HashMap();

        String direction = "LR";

        print(inputArray, direction);

    }

    private static void print(int[][] inputArray, String direction) {


        int horizontalPointer = 0;
        int verticalPointer = 0;

        int horizontalWindowSize = inputArray.length;
        int verticalWindowSize = inputArray.length;

        for (int k = 0; horizontalWindowSize >= 0 && verticalWindowSize >= 0; k++) {
            switch (direction) {
                case "LR": {

                    int i, j;
                    for (i = horizontalPointer, j = 0; j < horizontalWindowSize; i++, j++) {
                        System.out.println(inputArray[verticalPointer][i]);
                    }
                    direction = "TB";
                    horizontalPointer = i - 1;
                    verticalPointer = verticalPointer + 1;
                    verticalWindowSize--;
                }
                break;
                case "TB": {
                    int i, j;
                    for (i = verticalPointer, j = 0; j < verticalWindowSize; i++, j++) {
                        System.out.println(inputArray[i][horizontalPointer]);
                    }
                    direction = "RL";
                    verticalPointer = i - 1;
                    horizontalPointer = horizontalPointer - 1;
                    horizontalWindowSize--;
                }
                break;
                case "RL": {
                    int i, j;
                    for (i = horizontalPointer, j = 0; j < horizontalWindowSize; i--, j++) {
                        System.out.println(inputArray[verticalPointer][i]);
                    }
                    direction = "BT";
                    horizontalPointer = i + 1;
                    verticalPointer = verticalPointer - 1;
                    verticalWindowSize--;
                }
                break;
                case "BT": {
                    int i, j;
                    for (i = verticalPointer, j = 0; j < verticalWindowSize; i--, j++) {
                        System.out.println(inputArray[i][horizontalPointer]);
                    }
                    direction = "LR";
                    verticalPointer = i + 1;
                    horizontalPointer = horizontalPointer + 1;
                    horizontalWindowSize--;
                }
                break;
            }
        }
    }

}