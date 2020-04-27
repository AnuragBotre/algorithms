package com.trendcore;

import org.objectweb.asm.util.ASMifier;

import java.io.IOException;
import java.net.URL;

public class AsmNotifierUtil {

    public static void main(String[] args) throws IOException {
        String path = AsmNotifierUtil.class.getResource(".").getPath();
        System.out.println(path + ProfileMe.class.getName());
        //ASMifier.main(new String[]{ProfileMe.class.getName()});
        ASMifier.main(new String[]{TryFinallyBlockASM.class.getName()});

        //ASMifier.main(new String[]{UsingLambda.class.getName()});
    }

}
