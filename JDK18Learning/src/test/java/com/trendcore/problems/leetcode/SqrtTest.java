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
        //4294836225
        execute(10, 3);
    }

    @Test
    public void forMaxInt() {
        execute(2147483647, 46340);
    }

    @Test
    public void for_2147395599() {
        execute(2147395599, 46339);
    }



    public void execute(int x, int expected) {
        int i = sqrt.mySqrt(x);
        Assert.assertEquals(expected,i);
    }

    @Test
    public void squareOf_536870912() {
        int i = 46341 * 46341;
        long l =46341 * 46341;
        long m =65535 * 65535;
        System.out.println(i + " " + l + " " + m);
        System.out.println((1/2));

        System.out.println((268424449*268424449) + " " + (268424449*268424449 > 2147395599));

        int i1 = (402636674+1) * (402636674+1);
        System.out.println(i1 + " " + (268424449*268424449 > 2147395599));

    }
}