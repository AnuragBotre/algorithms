package com.trendcore.com.trendcore.problems.leetcode;

import com.trendcore.problems.leetcode.PlusOne;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class PlusOneTest {

    private PlusOne p;

    @Before
    public void setUp() throws Exception {
        p = new PlusOne();
    }

    @Test
    public void basicInput() {
        assertResult(new int[]{1, 2, 3},"124");
        assertResult(new int[]{4,3,2,1},"4322");
        assertResult(new int[]{3,2,1},"322");
        assertResult(new int[]{3,2,8},"329");
    }

    @Test
    public void endingWithNine() {
        assertResult(new int[]{1, 2, 9},"130");
    }

    @Test
    public void allNine() {
        assertResult(new int[]{9},"10");
        assertResult(new int[]{9, 9, 9},"1000");
    }

    public void assertResult(int[] digits, String expected) {
        int[] ints = p.plusOne(digits);
        StringBuilder c = new StringBuilder();
        Arrays.stream(ints).forEach(value -> c.append(value));

        Assert.assertEquals(expected,c.toString());
    }
}
