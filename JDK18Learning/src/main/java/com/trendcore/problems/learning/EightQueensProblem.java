package com.trendcore.problems.learning;

public class EightQueensProblem {

    public static void main(String[] args) {
        char board[][] = new char[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '0';
            }
        }

        printBoard(board);

        backtrack(board, 0, 0);

    }

    private static void backtrack(char[][] board, int rows, int cols) {
        if (isValid(board, rows, cols)) {
            board[rows][cols] = '1';
        }
    }

    private static boolean isValid(char[][] board, int rows, int cols) {

        //check verticals
        int rowCount = 0;
        int colCount = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[rows][i] == '1') {
                rowCount++;
            }

            if (board[i][cols] == '1') {
                colCount++;
            }
        }

        if (rowCount > 0 || colCount > 0)
            return false;

        //check diagonals
        /*int r = rows;
        int c = cols;
        while(true){

        }*/

        return false;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

}
