package com.trendcore.problems.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimplifyPathTest {

    SimplifyPath simplifyPath = new SimplifyPath();

    @Test
    public void tryWithGivenInput() {
        act("/home/", "/home");
        act("/home", "/home");
    }

    @Test
    public void example_2() {
        act("/../", "/");
    }

    @Test
    public void example_3() {
        act("/home//foo/", "/home/foo");
    }

    @Test
    public void example_4() {
        act("/a/./b/../../c/", "/c");
    }

    @Test
    public void example_5() {
        act("/a/../../b/../c//.//", "/c");
    }

    @Test
    public void example_6() {
        act("/a//b////c/d//././/..", "/a/b/c");
    }

    @Test
    public void example_7() {
        act("/...", "/");
    }

    public void act(String path, String expected) {
        System.out.println(path);
        String s = simplifyPath.simplifyPath(path);
        assertEquals(expected, s);
    }
}