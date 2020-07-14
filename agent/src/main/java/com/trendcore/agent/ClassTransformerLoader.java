package com.trendcore.agent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;

public class ClassTransformerLoader {

    public static void go(String agentArgs, Instrumentation inst) {
        ClassTransformer classTransformer = new ClassTransformer();

        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        Arrays.stream(allLoadedClasses)
                .filter(aClass -> aClass.getName().contains("com.trendcore"))
                .filter(aClass -> !(aClass.getName().startsWith("com.trendcore.agent") ||
                                    aClass.getName().startsWith("com.trendcore.asm") ||
                                    aClass.getName().startsWith("com.trendcore.classloader") ) )
                .forEach(aClass -> {
                    try {
                        if (!aClass.isInterface()) {
                            String name = aClass.getName().replace('.', '/');
                            InputStream is = aClass.getResourceAsStream("/" + name + ".class");
                            if(is != null) {
                                //need to read from byte buffer
                                byte[] bytes = getBytes(is);
                                byte[] transform = classTransformer.transform(aClass.getClassLoader(), aClass.getName(), aClass, aClass.getProtectionDomain(), bytes);
                                if (transform != null) {
                                    inst.redefineClasses(new ClassDefinition(aClass, transform));
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error occurred while transforming class " + aClass.getName());
                    }
                });

        inst.addTransformer(classTransformer,true);
    }


    private static byte[] getBytes(InputStream is) throws IOException {

            try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
                byte[] buffer = new byte[0xFFFF];
                for (int len; (len = is.read(buffer)) != -1; )
                    os.write(buffer, 0, len);
                os.flush();
                return os.toByteArray();
            }


    }
}
