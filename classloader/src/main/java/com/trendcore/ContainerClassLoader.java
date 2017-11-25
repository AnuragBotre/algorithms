package com.trendcore;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Anurag
 */
public class ContainerClassLoader extends URLClassLoader {

    public ContainerClassLoader(URL[] urls) {
        super(urls);
        System.out.println(getParent());
    }

    public ContainerClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }


}
