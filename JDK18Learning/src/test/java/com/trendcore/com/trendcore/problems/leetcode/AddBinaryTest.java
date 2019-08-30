package com.trendcore.com.trendcore.problems.leetcode;

import com.trendcore.problems.leetcode.AddBinary;
import org.junit.Assert;
import org.junit.Test;

public class AddBinaryTest {

    AddBinary addBinary = new AddBinary();

    @Test
    public void basicInput() {
        /*execute("10", "1", "11");
        execute("0", "1", "1");*/
        execute("101", "1", "110");

    }

    @Test
    public void whenInputIsMaxThenOutputShouldGetAdjusted() {
        execute("1", "1", "10");
        execute("11", "1", "100");
    }

    public void execute(String a, String b, String expected) {
        String s = addBinary.addBinary(a, b);
        Assert.assertEquals(expected,s);
    }
}
