package com.trendcore.agent;

import com.trendcore.classloader.InMemoryJarStreamHandler;
import com.trendcore.classloader.InMemoryJarUrlStreamHandlerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

public class AgentLibClassLoader extends URLClassLoader {

    private static URL urls[];

    private ClassLoader parent;

    /*static
    {
        String file = AgentLibClassLoader.class.getResource("/ext").getFile();
        File extDir = new File(file);
        File[] files = extDir.listFiles();
        urls = new URL[files.length];
        int i = 0;
        for (File listFile : files) {
            try {
                urls[i] = listFile.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        //return urls;
    }*/

    public AgentLibClassLoader(URL[] urls, ClassLoader parent) {
        //super(urls, parent,new InMemoryJarUrlStreamHandlerFactory());
        super(urls, parent);
        this.parent = parent;
    }

    public AgentLibClassLoader(ClassLoader classLoader) {
        super(urls, classLoader);
        this.parent = parent;
    }

    public static URL[] getUrls() throws Exception {
        InputStream resourceAsStream = AgentLibClassLoader.class.getResourceAsStream("/ext/dependencies.properties");

        Properties properties = new Properties();
        properties.load(resourceAsStream);

        Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();

        List<URL> listUrls = new ArrayList<>();

        while (enumeration.hasMoreElements()) {
            String nextEntry = enumeration.nextElement();
            InputStream resourceAsStream1 = AgentLibClassLoader.class.getResourceAsStream("/ext/" + nextEntry);
            URL fileUrl = AgentLibClassLoader.class.getResource("/ext/" + nextEntry);

            URL url = new URL(fileUrl.getProtocol(),fileUrl.getHost(),fileUrl.getPort(),nextEntry,new InMemoryJarStreamHandler(resourceAsStream1));
            /*String jarPath = fileUrl.getPath().substring(5, fileUrl.getPath().indexOf("!"));
            jarPath = jarPath + "/ext/" + nextEntry;
            //Need to fix this
            URL url = new URL("jar", "", 0, jarPath, new InMemoryJarStreamHandler(resourceAsStream1));
            System.out.println(nextEntry);*/
            listUrls.add(url);
        }

        URL url[] = new URL[listUrls.size()];
        for (int i = 0; i < listUrls.size(); i++) {
            url[i] = listUrls.get(i);
        }

        return url;
    }

    public static String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
        URL dirURL = clazz.getClassLoader().getResource(path);
        if (dirURL != null && dirURL.getProtocol().equals("file")) {
            /* A file path: easy enough */
            return new File(dirURL.toURI()).list();
        }

        if (dirURL == null) {
            /*
             * In case of a jar file, we can't actually find a directory.
             * Have to assume the same jar as clazz.
             */
            String me = clazz.getName().replace(".", "/") + ".class";
            dirURL = clazz.getClassLoader().getResource(me);
        }

        if (dirURL.getProtocol().equals("jar")) {
            /* A JAR path */
            String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!")); //strip out only the JAR file
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
            Set<String> result = new HashSet<String>(); //avoid duplicates in case it is a subdirectory
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(path)) { //filter according to the path
                    String entry = name.substring(path.length());
                    int checkSubdir = entry.indexOf("/");
                    if (checkSubdir >= 0) {
                        // if it is a subdirectory, we just return the directory name
                        entry = entry.substring(0, checkSubdir);
                    }
                    result.add(entry);
                }
            }
            return result.toArray(new String[result.size()]);
        }

        throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
    }

    public static AgentLibClassLoader getClassLoader(String dependenciesPath) throws MalformedURLException {
        ClassLoader classLoader = AgentLibClassLoader.class.getClassLoader();

        File dependenciesDir = new File(dependenciesPath);

        List<URL> listUrls = new ArrayList<>();

        for(File f : dependenciesDir.listFiles()) {
            if(!f.isDirectory()) {
                URL url = f.toURI().toURL();
                listUrls.add(url);
            }
        }

        URL url[] = new URL[listUrls.size()];
        for (int i = 0; i < listUrls.size(); i++) {
            url[i] = listUrls.get(i);
        }

        AgentLibClassLoader agentLibClassLoader = new AgentLibClassLoader(url,classLoader);
        return agentLibClassLoader;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //first we will try to find class
        //if not found then go for super
        synchronized (getClassLoadingLock(name)) {
            try {
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    c = findClass(name);
                }

                if (c != null) {
                    if (resolve) {
                        resolveClass(c);
                    }
                    return c;
                }
            } catch (Exception e) {
                System.out.println("Failed to load class from local context." + name);
            }
        }

        return super.loadClass(name, resolve);
    }

    /*@Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }*/

    private byte[] loadClassFromFile(String fileName) {
        InputStream inputStream = getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
