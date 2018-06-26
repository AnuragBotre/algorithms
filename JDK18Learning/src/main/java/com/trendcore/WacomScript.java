package com.trendcore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WacomScript {

    public static void main(String[] args) throws IOException {

        //Configuration information
        //Enter here the active area of your tablet in centimeters (mesure in physical world) :
        int correctionscalefactor=1;
        float XTabletactiveareaCM = 30.5f;
        float YTabletactiveareaCM = 22.7f;

        String tabletstylus="Wacom Bamboo One M Pen stylus";
        String tableteraser="Wacom Bamboo One M Pen eraser";

        //executeCommand("xsetwacom --set "+tabletstylus+" ResetArea");
        executeCommand("xsetwacom","--set",tabletstylus,"ResetArea");
        executeCommand("xsetwacom","--set",tableteraser,"ResetArea");

        //String fulltabletarea = executeCommand("xsetwacom get "+tabletstylus+" Area");
        String fulltabletarea = executeCommand("xsetwacom","get",tabletstylus,"Area");

        System.out.println(fulltabletarea);

        int xtabletmaxarea = Integer.parseInt(getTabletXMaxArea(fulltabletarea));
        int ytabletmaxarea = Integer.parseInt(getTabletYMaxArea(fulltabletarea));

        System.out.println(xtabletmaxarea);
        System.out.println(ytabletmaxarea);

        //get Screen Co-ordinates
        String screenAreaString = executeCommand("xrandr","--current");

        List<String> screenArea = getScreenArea(screenAreaString);
        float xScreenpix = Float.parseFloat(screenArea.get(0));
        float yScreenpix = Float.parseFloat(screenArea.get(1));
        System.out.println(xScreenpix + " " + yScreenpix);

        //get screen PPI
        Integer screenPPI = getScreenPPI();
        System.out.println(screenPPI);

        float xScreenPPI = xScreenpix / screenPPI;
        float yScreenPPI = yScreenpix / screenPPI;

        int xScreenCM = (int) (xScreenpix * 0.0254);
        int yScreenCM = (int) (yScreenpix * 0.0254);

        //Precise Mode + Ratio
        int yTabletmaxarearatiosized = (int)(yScreenpix * xtabletmaxarea / xScreenpix);
        float xtabletactiveareaPIX = (float) (XTabletactiveareaCM * screenPPI / 2.54 * correctionscalefactor);
        float ytabletactiveareaPIX = (float) (YTabletactiveareaCM * screenPPI / 2.54 * correctionscalefactor);

        int xtabletactiveareaPIXInt = (int) ((xtabletactiveareaPIX + 0.5) / 1);
        int ytabletactiveareaPIXInt = (int) ((ytabletactiveareaPIX + 0.5) / 1);

        int xOffsettabletactiveareaPIX = (int) ((xScreenpix - xtabletactiveareaPIXInt) / 2);
        int yOffsettabletactiveareaPIX = (int) ((yScreenpix - ytabletactiveareaPIXInt) / 2);

        System.out.println(xOffsettabletactiveareaPIX + " " + yOffsettabletactiveareaPIX);
    }

    private static Integer getScreenPPI() throws IOException {
        String xdpyinfo = executeCommand("xdpyinfo");
        String dots = xdpyinfo.split("dots")[0];
        String resolution = dots.split("resolution")[1];
        String x = resolution.split("x")[1];
        return Integer.parseInt(x.trim());
    }

    private static List<String> getScreenArea(String screenArea) {
        Optional<String> current = Arrays.stream(screenArea.split(","))
                .map(s -> s.trim())
                .filter(s -> !s.isEmpty())
                .filter(s -> s.contains("current"))
                .findFirst();

        if(current.isPresent()){
            System.out.println(current.get());
            List<String> collect = Arrays.stream(current.get().split(" ")).filter(s -> {
                try {
                    Integer.parseInt(s);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }).collect(Collectors.toList());

            collect.forEach(System.out::println);

            return collect;
        }
        return null;
    }

    private static String getTabletXMaxArea(String fulltabletarea) {
        return fulltabletarea.split(" ")[2];
    }

    private static String getTabletYMaxArea(String fulltabletarea) {
        return fulltabletarea.split(" ")[3];
    }

    public static String executeCommand(String... commands) throws IOException {

        System.out.println();
        for(String command : commands){
            System.out.print(" " + command);
        }
        System.out.println();

        Runtime rt = Runtime.getRuntime();
        Process process = null;
        try {

            process = new ProcessBuilder(commands)
                    .redirectErrorStream(true)
                    .start();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        StringBuilder output = new StringBuilder();
        while (true) {
            line = b.readLine();
            if (line == null) { break; }
            output.append(line);
        }
        return output.toString();
    }
}
