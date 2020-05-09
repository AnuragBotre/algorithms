package com.trendcore;

import com.trendcore.asm.ClassAdapter;
import javassist.NotFoundException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.*;
import java.util.Arrays;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.util.jar.JarFile.MANIFEST_NAME;

/**
 * http://modularity.info/conference/2007/program/industry/I5-UsingASMFramework.pdf
 * 3.2.3 Insert Code before Method Exit
 * Look for try / finally
 * <p>
 * http://web.cs.ucla.edu/~msb/cs239-tutorial/
 *
 * https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/sun/tools/jar/Main.java
 */

public class AsmReader {

    public static void main(String[] args) throws IOException, NotFoundException {
        //read jar and show visit each class

        String resource = JarReader.class.getResource(".").getFile();
        String outpath = resource + "../../../../lib/";
        File file = new File(resource + "../../../../lib/data-structures-1.0-SNAPSHOT.jar");
        System.out.println(file.isFile());
        JarFile jarFile = new JarFile(file);

        //processJar(resource, jarFile);

        /*String dir = "E:\\profilation";
        scanDirectory(dir);*/

        Manifest manifest = jarFile.getManifest();

        JarOutputStream dest = new JarOutputStream(new FileOutputStream(outpath+"data-structures-1.0-SNAPSHOT-profiled.jar"),manifest);

        /*ZipEntry e = new ZipEntry("META-INF/");
        e.setTime(System.currentTimeMillis());
        e.setSize(0);
        e.setCrc(0);
        dest.putNextEntry(e);
        e = new ZipEntry(MANIFEST_NAME);
        e.setTime(System.currentTimeMillis());

        dest.putNextEntry(e);
        manifest.write(dest);
        dest.closeEntry();*/

        processJar(jarFile, dest);
    }

    private static void scanDirectory(String dir) {
        File jarDir = new File(dir);
        Arrays.stream(jarDir.listFiles()).forEach(file -> {
            try {
                JarFile source = new JarFile(file);
                String destNameJarFileName = getDestName(file);
                JarOutputStream dest = new JarOutputStream(new FileOutputStream(destNameJarFileName));
                processJar(source, dest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static String getDestName(File file) {
        StringBuilder newName = new StringBuilder();
        String[] split = file.getName().split("\\.");
        for (int i = 0; i < split.length - 1; i++) {
            newName.append(split[i]);
            if (i < split.length - 2)
                newName.append(".");
        }
        newName.append("-profiled.jar");
        return newName.toString();
    }

    private static void processJar(JarFile jarFile, JarOutputStream tempJar) throws IOException {

        jarFile.stream().forEach(jarEntry -> {
            try {
                System.out.println(jarEntry.getName()+" "+jarEntry.getAttributes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(jarEntry.isDirectory()) {
                addDirectoryEntryToJar(jarEntry,tempJar);
            }

            if (!jarEntry.isDirectory() && jarEntry.getName().endsWith(".class")) {
                InputStream classFileInputStream = getClassFileInputStream(jarFile, jarEntry);
                processEntry(jarEntry, classFileInputStream, tempJar, jarFile);
            } else {
                if (!jarEntry.isDirectory() && !jarEntry.getName().equals(MANIFEST_NAME)) {
                    copyFile(jarFile, tempJar, jarEntry);
                }
            }
        });

        tempJar.close();
        jarFile.close();
    }

    private static void addDirectoryEntryToJar(JarEntry jarEntry, JarOutputStream outputJar) {
        try {
            outputJar.putNextEntry(jarEntry);
            outputJar.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void copyFile(JarFile jarFile, JarOutputStream tempJar, JarEntry jarEntry) {
        try {

            tempJar.putNextEntry(jarEntry);
            InputStream is = getClassFileInputStream(jarFile, jarEntry);
            tempJar.write(getBytes(is));
            tempJar.closeEntry();
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
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            ClassAdapter classAdapter = new ClassAdapter(Opcodes.ASM8, cw, jarEntry.getName());

            cr.accept(classAdapter, ClassReader.EXPAND_FRAMES);

            byte[] b = cw.toByteArray();
            JarEntry outputJarEntry = new JarEntry(jarEntry.getName());
            outputJarEntry.setTime(System.currentTimeMillis());

            //need to directory entries as well

            if(b.length == 0) {
                outputJarEntry.setMethod(ZipEntry.STORED);
                outputJarEntry.setSize(0);
                outputJarEntry.setCrc(0);
            } else {
                crc32File(outputJarEntry, b);
            }

            //outputJarEntry.setSize(b.length);
            outputJar.putNextEntry(outputJarEntry);
            outputJar.write(b);
            outputJar.closeEntry();
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

    /**
     * Computes the crc32 of a File.  This is necessary when the
     * ZipOutputStream is in STORED mode.
     */
    private static void crc32File(ZipEntry e, byte[] buffer) throws IOException {
        CRC32OutputStream os = new CRC32OutputStream();
        os.write(buffer,0,buffer.length);
        os.updateEntry(e);
    }

    private static class CRC32OutputStream extends java.io.OutputStream {
        final CRC32 crc = new CRC32();
        long n = 0;

        CRC32OutputStream() {}

        public void write(int r) throws IOException {
            crc.update(r);
            n++;
        }

        public void write(byte[] b, int off, int len) throws IOException {
            crc.update(b, off, len);
            n += len;
        }

        /**
         * Updates a ZipEntry which describes the data read by this
         * output stream, in STORED mode.
         */
        public void updateEntry(ZipEntry e) {
            e.setMethod(ZipEntry.STORED);
            e.setSize(n);
            e.setCrc(crc.getValue());
        }
    }

    /**
     * Generates class index file for the specified root jar file.
     */
    /*void genIndex(String rootjar, String[] files) throws IOException {
        List<String> jars = getJarPath(rootjar);
        int njars = jars.size();
        String[] jarfiles;

        if (njars == 1 && files != null) {
            // no class-path attribute defined in rootjar, will
            // use command line specified list of jars
            for (int i = 0; i < files.length; i++) {
                jars.addAll(getJarPath(files[i]));
            }
            njars = jars.size();
        }
        jarfiles = jars.toArray(new String[njars]);
        JarIndex index = new JarIndex(jarfiles);
        dumpIndex(rootjar, index);
    }*/

    /*private void addIndex(JarIndex var1, ZipOutputStream var2) throws IOException {
        ZipEntry indexFile = new ZipEntry("META-INF/INDEX.LIST");
        indexFile.setTime(System.currentTimeMillis());

        CRC32OutputStream var4 = new CRC32OutputStream();
        var1.write(var4);
        var4.updateEntry(indexFile);

        var2.putNextEntry(indexFile);
        var1.write(var2);
        var2.closeEntry();
    }*/
}
