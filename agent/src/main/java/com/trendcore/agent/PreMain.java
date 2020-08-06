package com.trendcore.agent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class PreMain {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        System.out.println("Initializing Agent.");
        instrumentation = inst;

    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("Initializing Agent agent main.");
        instrumentation = inst;

        Map<String, String> args = ArgumentParserUtil.parserArgs(agentArgs);

        try {
            attachLibrariesToBootStrapClassLoader(args,inst);
            ClassLoader agentLibClassLoader = PreMain.class.getClassLoader();
            //AgentLibClassLoader agentLibClassLoader = AgentLibClassLoader.getClassLoader(args.get("dependenciesPath"));
            //AgentLibClassLoader agentLibClassLoader = new AgentLibClassLoader(urls,classLoader);
            Class<?> agentClass = agentLibClassLoader.loadClass("com.trendcore.agent.loader.Main");
            agentClass.getMethod("go", String.class,Instrumentation.class).invoke(null, agentArgs,inst);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //addTransformer(inst);

        /*Class<?> agentClass = null;
        try {
            String name = "com.trendcore.agent.ClassTransformerLoader";

            URL[] urls = AgentLibClassLoader.getUrls();
            AgentLibClassLoader agentLibClassLoader = new AgentLibClassLoader(PreMain.class.getClassLoader());
            agentClass = agentLibClassLoader.loadClass(name);
            Object agent;
            try {
                agentClass.getMethod("go").invoke(null,new Object[]{agentArgs,inst});
            } catch (Exception e) {
                throw new IllegalArgumentException("Cannot start agent " + name, e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private static void attachLibrariesToBootStrapClassLoader(Map<String, String> args, Instrumentation inst) throws IOException {
        String dependenciesPath = args.get("dependenciesPath");
        File dependenciesDir = new File(dependenciesPath);

        List<URL> listUrls = new ArrayList<>();

        for(File f : dependenciesDir.listFiles()) {
            if(!f.isDirectory() && f.getName().endsWith(".jar")) {
                JarFile jarFile = new JarFile(f);
                inst.appendToBootstrapClassLoaderSearch(jarFile);
            }
        }
    }

    private static void addTransformer(Instrumentation inst) {
        TestTransformer testTransformer = new TestTransformer();
        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class allLoadedClass : allLoadedClasses) {

            try {
                String name = allLoadedClass.getName().replace('.', '/');
                try (InputStream is = allLoadedClass.getResourceAsStream("/" + name + ".class");){
                    if(is != null) {

                        byte[] newBytes = testTransformer.transform(allLoadedClass.getClassLoader(),
                                allLoadedClass.getName(),
                                allLoadedClass,
                                allLoadedClass.getProtectionDomain(), getBytes(is));

                        if (newBytes != null) {
                            inst.redefineClasses(new ClassDefinition(allLoadedClass, newBytes));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        inst.addTransformer(testTransformer, true);
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
