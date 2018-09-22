package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/sudoku-solver/description/
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of
 * the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 */
public class SudokuSolver {

    private ValidSudoku v;

    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver();
        char board1[][] = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        /*char board1[][] = new char[][]{
                {'5', '3', '.',},
                {'6', '.', '.',},
                {'.', '9', '8',}
        };*/
        s.solveSudoku(board1);

        s.printBoard(board1);
    }

    private void printBoard(char[][] board) {

        System.out.println("Is Board valid :- " + v.isValidSudoku(board));

        for (char arr[] : board) {
            for (char c : arr) {
                System.out.print(" " + c);
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {

        char numbers[] = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        List<List<Character>> list = new ArrayList<>();

        //Analyze row
        for (int i = 0; i < board.length; i++) {
            //for given row analyze missing characters
            Map map = new HashMap<>();
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    map.put(board[i][j], true);
                }
            }

            List tempList = new ArrayList();
            list.add(tempList);
            for (int k = 0; k < numbers.length; k++) {
                if (!map.containsKey(numbers[k])) {
                    tempList.add(numbers[k]);
                }
            }
        }

        insert(list, board);

        //print(list);
    }

    private void insert(List<List<Character>> list, char[][] board) {

        v = new ValidSudoku();

        int rowCounter = 0;

        //we need to store each and every position on the stack hence this for loop need to be replaced by some fn

        /*for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    insertCharacter(list, board, v, i, j,0);
                }
            }
        }*/
        recurse(0, 0, 0, board, list);
    }

    private void recurse(int row, int column, int listIndex, char[][] board, List<List<Character>> list) {
        if (row >= board.length) {
            return;
        }

        if (column >= board.length) {
            //System.out.println();
            recurse(row + 1, 0, 0, board, list);
            return;
        }

        if (listIndex >= list.get(row).size()) {
            //recurse(row, column + 1, 0, board, list);
            return;
        }

        if (board[row][column] == '.') {

            List<Character> originalList = list.get(row);
            List<Character> newList = new ArrayList<>(originalList);

            list.add(row,newList);

            //List<List<Character>> tempList = new ArrayList(list);
            board[row][column] = newList.remove(listIndex);
            //System.out.print(" " + board[row][column]);
            //TODO : Need to create function which will take row,col,char which will check if the board is valid.
            if (v.isValidSudoku(board)) {
                recurse(row, column + 1, 0, board, list);
            } else {
                list.add(row,originalList);
                board[row][column] = '.';
                return;
                //recurse(row, column, listIndex + 1, board, list);
            }


            //add only for testing
            //recurse(row, column + 1, listIndex, board, list);

        } else {
            //System.out.print(" " + board[row][column]);
            recurse(row, column + 1, listIndex, board, list);
        }


        /*ValidSudoku v = new ValidSudoku();
        Character c = list.get(row).get(listIndex);
        board[row][column] = c;
        //isBoardValid
        if (!v.isValidSudoku(board)) {
            recurse(row, column + 1, listIndex++, board, list);
        }


        recurse(row, column + 1, listIndex, board, list);*/
    }

    private void insertCharacter(List<List<Character>> list, char[][] board, int row, int col, int listIndex) {


        //need to replace this . with one of the numbers from the list

        /*for (int l = 0; l < list.get(row).size(); l++) {
            checkAndInsert(list, board, v, row, col, 0);
        }*/

        //checkAndInsert(list, board, v, row, col, listIndex);

    }

    private void checkAndInsert(List<List<Character>> list, char[][] board, ValidSudoku v, int row, int col, int listIndex) {
        if (listIndex >= list.get(row).size()) {
            return;
        }

        Character character = list.get(row).get(listIndex);
        board[row][col] = character;
        //isBoardValid
        if (!v.isValidSudoku(board)) {
            board[row][col] = '.';
            checkAndInsert(list, board, v, row, col, listIndex++);
        }
    }

    private void print(List<List<Character>> list) {
        for (List<Character> chars : list) {
            for (Character c : chars) {
                System.out.print(" " + c);
            }
            System.out.println();
        }
    }
}
