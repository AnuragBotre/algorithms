package com.trendcore.problems.learning;

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

        printBoard(board);

        //board[0][0] = '1';
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

    private void backtrack(char[][] board, int rows, int cols) {

        if (cols >= board.length) {
            //backtrack(board, rows + 1, 0);
            return;
        }

        if (rows >= board.length) {
            return;
        }

        for(int i = 0 ; i < board.length ; i++){
            if(isValid(board, rows, i)){
                board[rows][i] = QUEEN;
                backtrack(board, rows + 1, 0);
            }else{
                board[rows][cols] = BLANK_SQUARE;

            }
        }

        /*if (isValid(board, rows, cols)) {
            board[rows][cols] = '1';
            backtrack(board, rows, cols + 1);
        } else {
            board[rows][cols] = BLANK_SQUARE;
            backtrack(board, rows, cols + 1);
        }*/
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

                if ((rows + counter) < 0 || (cols + counter) < 0) {
                    break;
                }

                if (incCounter != 0 && board[rows + decCounter][cols + incCounter] == '1') {
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
        }finally {
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
