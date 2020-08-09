package com.trendcore.agent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassTransformationUtil {

    public static byte[] getBytes(InputStream is) throws IOException {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[0xFFFF];
            for (int len; (len = is.read(buffer)) != -1; )
                os.write(buffer, 0, len);
            os.flush();
            return os.toByteArray();
        }
    }

    public static boolean skipTransformationForClasses(Class aClass) {

        return !(
                aClass.getName().startsWith("sun") ||
                aClass.getName().startsWith("java") ||
                aClass.getName().startsWith("javax") ||
                aClass.getName().startsWith("com.sun") ||
                aClass.getName().startsWith("org.openjdk") ||
                aClass.getName().startsWith("jdk") ||
                aClass.getName().startsWith("org.objectweb.asm") ||
                aClass.getName().startsWith("org.aeonbits.owner") ||
                // Do not transform maven
                aClass.getName().startsWith("org.apache.maven") ||
                aClass.getName().startsWith("com.mongodb") ||
                aClass.getName().startsWith("com.trendcore.agent") ||
                aClass.getName().startsWith("com.trendcore.asm") ||
                aClass.getName().startsWith("com.trendcore.classloader")
        );
    }
}
