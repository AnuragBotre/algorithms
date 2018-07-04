package com.trendcore;

import org.junit.Test;

import java.io.IOException;

import static com.trendcore.WacomScript.executeCommand;

public class WacomScriptTest {

    @Test
    public void getScreenPPI() throws IOException {
        String xdpyinfo = executeCommand("xdpyinfo");
        System.out.println(xdpyinfo);

        String dots = xdpyinfo.split("dots")[0];
        String resolution = dots.split("resolution")[1];

        String x = resolution.split("x")[1];
        System.out.println(" " + x);
    }

    @Test
    public void executeBCCommand() throws IOException {
        String output = executeCommand("bc","<<<","\"12+5\"");
        System.out.println(output);
    }
}
