package com.trendcore.facade;

import java.io.File;

public class ClientForFacade {

    public static void main(String[] args) {
        VideoConverter converter = new VideoConverter();
        File convert = converter.convert("filename", "mp4");
        converter.save(convert);
    }

}
