package com.trendcore.agent;

import com.trendcore.Profiler;
import com.trendcore.StorageService;
import com.trendcore.agent.storage.StorageServiceFactory;
import com.trendcore.agent.storage.StorageServiceFactory.Type;

import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ClassTransformerLoader {

    public static void go(String agentArgs, Instrumentation inst) {
        ClassTransformer classTransformer = new ClassTransformer();

        Class[] allLoadedClasses = inst.getAllLoadedClasses();

        List<Class> keyClasses = new ArrayList();

        Arrays.stream(allLoadedClasses)
                .filter(aClass -> {
                    if(aClass.getName().equalsIgnoreCase("java.lang.Thread")) {
                        keyClasses.add(aClass);
                    }
                    return true;
                })
                .filter(aClass -> aClass.getName().contains("com.trendcore"))
                .filter(aClass -> ClassTransformationUtil.skipTransformationForClasses(aClass))
                .forEach(aClass -> redefineClass(inst, classTransformer, aClass));

        /*JavaClassTransformer javaClassTransformer = new JavaClassTransformer();
        keyClasses.forEach(aClass -> {
            redefineClass(inst, javaClassTransformer, aClass);
        });*/

        inst.addTransformer(classTransformer,true);

        Map<String, String> args = ArgumentParserUtil.parserArgs(agentArgs);
        Type storageType = Type.valueOf(args.getOrDefault("storage", Type.IN_MEMORY.toString()));
        StorageService storageService = StorageServiceFactory.getStorageService(args, storageType);
        if(storageService != null) {
            Profiler.registerStorageService(storageService);
        }
    }



    private static void redefineClass(Instrumentation inst, ClassFileTransformer classTransformer, Class aClass) {
        try {
            if (!aClass.isInterface()) {
                String name = aClass.getName().replace('.', '/');
                InputStream is = aClass.getResourceAsStream("/" + name + ".class");
                if(is != null) {
                    //need to read from byte buffer
                    byte[] bytes = ClassTransformationUtil.getBytes(is);
                    byte[] transform = classTransformer.transform(aClass.getClassLoader(), aClass.getName(), aClass, aClass.getProtectionDomain(), bytes);
                    if (transform != null) {
                        inst.redefineClasses(new ClassDefinition(aClass, transform));
                    }
                }
            }
        } catch (Throwable e) {
            System.out.println("Error occurred while transforming class " + aClass.getName());
        }
    }

}
