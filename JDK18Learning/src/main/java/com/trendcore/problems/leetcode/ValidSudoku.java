package com.trendcore.problems.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-sudoku/description/
 * <p>
 * 36. Valid Sudoku
 * Determine if a 9x9 Sudoku board is valid.
 * Only the filled cells need to be validated according to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * <p>
 * A partially filled sudoku which is valid.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 * <p>
 * Input:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 * modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */
public class ValidSudoku {

    public static void main(String[] args) {
        ValidSudoku v = new ValidSudoku();

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
        v.testCase(board1);

        char board2[][] = new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        v.testCase(board2);

        char board3[][] = new char[][]{
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        v.testCase(board3);

        System.out.println(v.oneOfLeetCodeSolutionIsValidSudoku(board3));
    }

    private void testCase(char[][] board) {
        System.out.println(isValidSudoku(board));
    }

    //TODO : Optmize me
    public boolean isValidSudoku(char[][] board) {

        //check for columns
        for (int i = 0; i < board.length; i++) {

            Map duplicate = new HashMap();
            for (int j = 0; j < board[i].length; j++) {
                //cases
                //find duplicate in a row
                if (duplicate.containsKey(board[i][j])) {
                    return false;
                } else {
                    if (board[i][j] != '.') {
                        duplicate.put(board[i][j], true);
                    }

                }
            }
        }

        //check for rows
        for (int i = 0; i < board.length; i++) {
            Map duplicate = new HashMap();
            for (int j = 0; j < board[i].length; j++) {
                //cases
                //find duplicate in a row
                if (duplicate.containsKey(board[j][i])) {
                    return false;
                } else {
                    if (board[j][i] != '.') {
                        duplicate.put(board[j][i], true);
                    }

                }
            }
        }

        for (int i = 0; i < board.length; i = i + 3) {
            for (int j = 0; j < board[i].length; j = j + 3) {

                Map map = new HashMap();

                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        if (map.containsKey(board[k][l])) {
                            return false;
                        } else {
                            if (board[k][l] != '.') {
                                map.put(board[k][l], true);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }


    public boolean oneOfLeetCodeSolutionIsValidSudoku(char[][] board) {
        for (int i = 0;i < 9; i ++){
            if (!isRowValid(i,board) || !isColumnValid(i,board) || !isSquareValid(3*(i % 3), 3*(i / 3),board)){
                return false;
            }
        }
        return true;
    }

    private boolean isRowValid(int y,char[][] board){
        boolean [] alreadyUsed = new boolean[9];
        for(int x = 0;x<9;x++){
            char c = board[x][y];
            int cValue = c-'1';
            if(c!='.' && alreadyUsed[cValue] == true){
                return false;
            } else if (c!='.'){
                alreadyUsed[cValue] = true;
            }
        }
        return true;
    }


    private boolean isColumnValid(int x,char[][] board){
        boolean [] alreadyUsed = new boolean[9];
        for(int y = 0;y<9;y++){
            char c = board[x][y];
            int cValue = c-'1';
            if(c!='.' && alreadyUsed[cValue] == true){
                return false;
            } else if (c!='.'){
                alreadyUsed[cValue] = true;
            }
        }
        return true;
    }

    private boolean isSquareValid(int x,int y,char[][] board){
        boolean [] alreadyUsed = new boolean[9];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                char c = board[x+i][y+j];
                int cValue = c-'1';
                if(c!='.' && alreadyUsed[cValue] == true){
                    return false;
                } else if (c!='.'){
                    alreadyUsed[cValue] = true;
                }
            }
        }
        return true;
    }

}
