package com.trendcore.problems.learning;

import java.util.Scanner;

public class EightQueensProblem {

    public static final char BLANK_SQUARE = '0';
    public static final char QUEEN = '1';

    public static void main(String[] args) {

        EightQueensProblem e = new EightQueensProblem();

        char board[][] = new char[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = BLANK_SQUARE;
            }
        }

        board[0][0] = QUEEN;
        board[1][2] = QUEEN;
        board[2][4] = QUEEN;
        board[3][6] = QUEEN;

        for (int col = 0; col < board.length; col++) {
            System.out.println(" " + col + " " + e.isValid(board, 4, col));
        }

        /*
         1 0 0 0 0 0 0 0
         0 0 1 0 0 0 0 0
         0 0 0 0 1 0 0 0
         0 0 0 0 0 0 1 0
         0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0
         0 0 0 0 0 0 0 0*/

        /*printBoard(board);

        board[0][0] = '1';
        board[1][2] = '1';
        System.out.println(e.isValid(board, 2, 1));*/

        /*for (int i = 0; i < board.length; ) {
            boolean backtrack = e.backtrack(board, 0, i);
            if (!backtrack) {
                i++;
            }
        }*/

        /*for(int i = 0 ; i < board.length ; i++){
            e.backtrack(board, i, 0);
        }*/


        e.backtrack(board, 0, 0);
        System.out.println(" ");

        printBoard(board);

        e.isValid(board);
        /*System.out.println(e.isValid(board, 1, 1));
        System.out.println(e.isValid(board, 7, 0));
        System.out.println(e.isValid(board, 0, 7));*/
    }

    private void isValid(char[][] board) {
        for (int i = 0; i < board.length; i++) {

        }
    }

    private boolean backtrack(char[][] board, int rows, int cols) {
        if (rows >= board.length) {
            return false;
        }

        return findCol(board, rows, cols);
    }

    private boolean findCol(char[][] board, int rows, int cols) {
        if (cols >= board.length) {
            return false;
        }

        if (isValid(board, rows, cols)) {
            board[rows][cols] = QUEEN;

            //debug(board);
            if(rows +1 >= board.length){
                return true;
            }else{
                boolean backtrack = backtrack(board, rows + 1, 0);
                //System.out.println(" " + backtrack);
                if (!backtrack && cols < board.length) {
                    board[rows][cols] = BLANK_SQUARE;
                    return findCol(board, rows, cols + 1);
                }
                return backtrack;
            }

        } else {
            board[rows][cols] = BLANK_SQUARE;
            boolean col = findCol(board, rows, cols + 1);
            //System.out.println(" " + col);
            return col;
        }

    }

    private void debug(char[][] board) {
        Scanner s = new Scanner(System.in);
        s.next();
        printBoard(board);
    }

    private boolean isValid(char[][] board, int rows, int cols) {

        board[rows][cols] = QUEEN;

        try {

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

            if (rowCount > 1 || colCount > 1)
                return false;

            //check diagonals
            int r = rows;
            int c = cols;
            int diagonalCount = 0;
            int counter = 0;
            boolean flag = true;
            while (flag) {

                if (rows + counter >= board.length || cols + counter >= board.length) {
                    break;
                }

                if (counter != 0 && board[rows + counter][cols + counter] == '1') {
                    diagonalCount++;
                }

                counter++;
            }

            counter = 0;
            while (flag) {

                if ((rows + counter) < 0 || (cols + counter) < 0) {
                    break;
                }

                if (counter != 0 && board[rows + counter][cols + counter] == '1') {
                    diagonalCount++;
                }

                counter--;
            }

            int decCounter = 0;
            int incCounter = 0;
            while (flag) {

                if ((rows + decCounter) < 0 || (cols + incCounter) >= board.length) {
                    break;
                }

                if (incCounter != 0 && board[rows + decCounter][cols + incCounter] == '1') {
                    diagonalCount++;
                }

                decCounter--;
                incCounter++;
            }

            decCounter = 0;
            incCounter = 0;
            while (flag) {

                if ((rows + incCounter) >= board.length || (cols + decCounter) < 0) {
                    break;
                }

                if (incCounter != 0 && board[rows + incCounter][cols + decCounter] == '1') {
                    diagonalCount++;
                }

                decCounter--;
                incCounter++;
            }

            //TODO : Fix me only one diagonal is checked cnt++ and cnt--
            //need to check other combination
            //

            if (diagonalCount > 0) {
                return false;
            }

            board[rows][cols] = '0';

            return true;
        } finally {
            board[rows][cols] = BLANK_SQUARE;
        }
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
