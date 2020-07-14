package com.trendcore.classloader;

import java.io.InputStream;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class InMemoryJarUrlStreamHandlerFactory implements URLStreamHandlerFactory {

    private InputStream inputStream;

    public InMemoryJarUrlStreamHandlerFactory() {
    }

    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        return new InMemoryJarStreamHandler(inputStream);
    }
}
