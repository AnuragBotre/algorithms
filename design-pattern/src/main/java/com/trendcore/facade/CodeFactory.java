package com.trendcore.facade;

import java.io.File;

public class CodeFactory {

    public static String extract(File file) {

        if (file.getName().equals("mp4")) {
            return "mp4";
        } else {
            return "avi";
        }
    }

}
