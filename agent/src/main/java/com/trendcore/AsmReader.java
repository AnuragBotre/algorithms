package com.trendcore;

import com.trendcore.asm.ClassAdapter;
import javassist.NotFoundException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

/**
 * http://modularity.info/conference/2007/program/industry/I5-UsingASMFramework.pdf
 * 3.2.3 Insert Code before Method Exit
 * Look for try / finally
 *
 * http://web.cs.ucla.edu/~msb/cs239-tutorial/
 */

public class AsmReader {

    public static void main(String[] args) throws IOException, NotFoundException {
        //read jar and show visit each class

        String resource = JarReader.class.getResource(".").getFile();
        String outpath = resource + "../../../../lib/";
        File file = new File(resource + "../../../../lib/data-structures-1.0-SNAPSHOT.jar");
        System.out.println(file.isFile());
        JarFile jarFile = new JarFile(file);

        JarOutputStream tempJar =
                new JarOutputStream(new FileOutputStream(resource + "../../../../lib/data-structures-1.0-SNAPSHOT-profiled.jar"));

        jarFile.stream().forEach(jarEntry -> {
            if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class")) {
                InputStream classFileInputStream = getClassFileInputStream(jarFile, jarEntry);
                processEntry(jarEntry, classFileInputStream, tempJar, jarFile);
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

    private static void processEntry(JarEntry jarEntry, InputStream classFileInputStream, JarOutputStream outputJar, JarFile jarFile) {
        try {
            //byte[] bytes = getBytes(classFileInputStream);

            ClassReader cr = new ClassReader(classFileInputStream);
            ClassWriter cw = new ClassWriter(cr,ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            ClassAdapter classAdapter = new ClassAdapter(Opcodes.ASM5,cw,jarEntry.getName());

            cr.accept(classAdapter,  ClassReader.EXPAND_FRAMES);

            byte[] b = cw.toByteArray();
            JarEntry outputJarEntry = new JarEntry(jarEntry.getName());
            outputJarEntry.setSize(b.length);
            outputJar.putNextEntry(outputJarEntry);
            outputJar.write(b);

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
