package com.trendcore.facade;

public class CodecFactory {

    public static Codec getCodec(String extract) {
        if(extract.equals("mp4")){
            return new MP4Code();
        }else{
            return new AVICode();
        }
    }
}
