package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/word-search/
 * <p>
 * 79. Word Search
 * <p>
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 */
public class WordSearch {


    class Traversal {

        char[][] board;

        String word;

        int prevI = -1;
        int prevJ = -1;

        int i = 0;
        int j = 0;

        String container = "";

        int index;

        int visitedCell[][];
        private int m;
        private int n;

        //private String path = "";

        public Traversal(char[][] board, String word) {
            this.board = board;
            this.word = word;

            m = this.board.length;
            n = this.board[0].length;
            visitedCell = new int[m][n];
        }

        private boolean findWord() {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        visitedCell = new int[m][n];
                        container = "";
                        boolean b = traverseInternal(i, j, 0);
                        if (word.equals(container)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private boolean traverseInternal(int i, int j, int index) {
            if (container.length() > word.length()) {
                return false;
            } else {
                if (word.equals(container)) {
                    return true;
                }

                if (board[i][j] == word.charAt(index)) {
                    //found word
                    visitedCell[i][j] = 1;

                    container = container + board[i][j];

                    /*path = path + "{"+i+","+j+"}";
                    System.out.println(container + " " + path);*/

                    if (word.equals(container)) {
                        return true;
                    }

                    boolean t = true;

                    t = traversePath(i, j - 1, index + 1);
                    t = traversePath(i, j + 1, index + 1);
                    t = traversePath(i - 1, j, index + 1);
                    t = traversePath(i + 1, j, index + 1);

                    if (!word.equals(container)) {
                        container = container.substring(0, container.length() - 1);
                        visitedCell[i][j] = 0;
                    }

                    return t;
                }
            }
            return false;
        }

        private boolean traversePath(int i, int j, int index) {
            boolean t = false;
            if (!isValidCell(i, j)) {
                t = traverseInternal(i, j, index);
            }
            return t;
        }

        private boolean isValidCell(int i, int j) {
            if (i < m && i >= 0 && j < n && j >= 0) {
                return visitedCell[i][j] == 1;
            }
            return true;
        }

    }


    public boolean exist(char[][] board, String word) {

        Traversal t = new Traversal(board, word);
        return t.findWord();
    }


}
