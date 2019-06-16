package com.trendcore.facade;


import java.io.File;

public class VideoConverter {

    public File convert(String filename, String ext) {
        File file = new File(filename);

        String extract = CodeFactory.extract(file);
        System.out.println("Extracting codec Factory ");

        Codec codec = CodecFactory.getCodec(extract);
        System.out.println("received codec Factory ");

        return BitrateReader.convert(codec);
    }

    public void save(File convert) {
        System.out.println("save file " + convert.getName());
    }
}
