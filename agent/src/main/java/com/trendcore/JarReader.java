package com.trendcore;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;

public class JarReader {

    public static void main(String[] args) throws IOException, NotFoundException {
        //read jar and show visit each class

        String resource = JarReader.class.getResource(".").getFile();
        String outpath = resource + "../../../../lib/";
        File file = new File(resource + "../../../../lib/data-structures-1.0-SNAPSHOT.jar");
        System.out.println(file.isFile());
        JarFile jarFile = new JarFile(file);
        ClassPool pool = ClassPool.getDefault();
        String pathname = outpath + "profiler-1.0-SNAPSHOT.jar";
        File profilerJarFile = new File(pathname);
        System.out.println(profilerJarFile.isFile());
        pool.appendClassPath(pathname);

        JarOutputStream tempJar =
                new JarOutputStream(new FileOutputStream(resource + "../../../../lib/data-structures-1.0-SNAPSHOT-profiled.jar"));

        jarFile.stream().forEach(jarEntry -> {
            if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class")) {
                InputStream classFileInputStream = getClassFileInputStream(jarFile, jarEntry);
                processEntry(jarEntry, pool, classFileInputStream, tempJar, jarFile);
            } else {
                if (!jarEntry.isDirectory()) {
                    copyFile(jarFile, tempJar, jarEntry);
                }
            }
        });

        tempJar.close();
        jarFile.close();
    }

    private static void copyFile(JarFile jarFile, JarOutputStream tempJar, JarEntry jarEntry) {
        try {

            tempJar.putNextEntry(jarEntry);
            InputStream is = getClassFileInputStream(jarFile, jarEntry);
            tempJar.write(getBytes(is));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private static void processEntry(JarEntry jarEntry, ClassPool pool, InputStream classFileInputStream, JarOutputStream outputJar, JarFile jarFile) {
        try {
            CtClass ctClass = pool.makeClass(classFileInputStream);
            Object annotation = ctClass.getAnnotation(Profile.class);


            System.out.println("processing " + jarEntry.getName());

            //if (annotation != null)
            {
                Arrays.stream(ctClass.getMethods()).filter(ctMethod -> !Modifier.isNative(ctMethod.getModifiers())).forEach(ctMethod -> {
                    //
                    try {
                        String collect = Arrays.stream(ctMethod.getParameterTypes()).map(ctClass1 -> "\"" + ctClass1.getName() + "\"").collect(Collectors.joining(","));

                        String args =  collect;
                        if (collect.equals("")) {
                            args = null;
                        }
                        args = null;

                        String methodName = "\" Entering :- " + ctMethod.getName() + "\"";
                        String methodName1 = "\" Exiting :- " + ctMethod.getName() + "\"";
                        /*ctMethod.insertBefore("com.trendcore.Profiler.pushMethod(" + methodName + ",System.currentTimeMillis(), " + args + " );");
                        ctMethod.insertAfter("com.trendcore.Profiler.popMethod(System.currentTimeMillis());", true);*/

                        /*ctMethod.insertBefore("System.out.println(  "+methodName+");");
                        ctMethod.insertAfter("System.out.println("+methodName+");",true);*/
                        //ctMethod.insertAfter("System.out.println("+methodName+");",true);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });


                byte[] b = ctClass.toBytecode();
                JarEntry outputJarEntry = new JarEntry(jarEntry.getName());
                outputJarEntry.setSize(b.length);
                outputJar.putNextEntry(outputJarEntry);
                outputJar.write(b);

            } /*else {
                copyFile(jarFile, outputJar, jarEntry);
            }*/


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getClassFileInputStream(JarFile jarFile, JarEntry jarEntry) {
        try {
            return jarFile.getInputStream(jarEntry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
