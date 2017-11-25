package com.trendcore;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest{

    @org.junit.Test
    public void scan() throws Exception {
        URL resource = App.class.getClassLoader().getResource(".");
        File file = new File(resource.getFile());
        System.out.println(file);

    }
}
