package com.trendcore.problems.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class SqrtTest {

    Sqrt sqrt = new Sqrt();

    @Test
    public void input1() {
        execute(1, 1);
    }

    @Test
    public void input2() {
        execute(2, 1);
    }

    @Test
    public void input3() {
        execute(3, 1);
    }

    @Test
    public void testWithGiveInput() {
        execute(4, 2);
        execute(8, 2);
    }

    @Test
    public void testForFailedInput() {
        execute(2147483647, 46340);
    }

    public void execute(int x, int expected) {
        int i = sqrt.mySqrt(x);
        Assert.assertEquals(expected,i);
    }
}