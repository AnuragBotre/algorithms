package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordSearchTest {

    WordSearch wordSearch = new WordSearch();

    @Test
    public void ip_1() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED", true);
    }

    @Test
    public void ip_2() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ASF", true);
    }

    @Test
    public void ip_3() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ASFDEE", true);
    }

    @Test
    public void ip_4() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABXYZ", false);
    }

    @Test
    public void ip_5() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABFDEX", false);
    }

    @Test
    public void ip_6() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCEESE", true);
    }

    @Test
    public void ip_7() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCFD", true);
    }

    @Test
    public void ip_8() {
        execute(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED", true);
    }

    @Test
    public void ip_9() {
        execute(new char[][]{
                {'A', 'B', 'B', 'E'},
                {'S', 'B', 'C', 'C'},
                {'A', 'D', 'C', 'E'}
        }, "ABBCCE", true);
    }

    @Test
    public void ip_10() {
        execute(new char[][]{
                {'A', 'B', 'B', 'E'},
                {'S', 'B', 'C', 'C'},
                {'A', 'D', 'C', 'E'}
        }, "BBCCE", true);
    }

    @Test
    public void ip_11() {
        execute(new char[][]{
                {'A', 'B', 'B', 'E'},
                {'S', 'B', 'C', 'C'},
                {'A', 'D', 'C', 'E'}
        }, "BECE", true);
    }

    public void execute(char[][] board, String word, boolean expected) {
        boolean output = wordSearch.exist(board, word);
        assertEquals(expected, output);
    }
}