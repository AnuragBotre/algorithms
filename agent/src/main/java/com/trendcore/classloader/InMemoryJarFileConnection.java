package com.trendcore.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class InMemoryJarFileConnection extends URLConnection {

    private boolean connected;

    private final URLConnection innerJarUrlConnection;
    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     * @param jarStream
     */
    protected InMemoryJarFileConnection(URL url, InputStream jarStream) throws IOException {
        super(url);
        URL innerJarUrl = new URL(url.getFile());
        innerJarUrlConnection = innerJarUrl.openConnection();
    }

    @Override
    public void connect() throws IOException {

        //in connect method we need to open connection
        if (!connected) {
            innerJarUrlConnection.connect();
            connected = true;
        }
    }


    @Override
    public InputStream getInputStream() throws IOException {
        connect();
        return innerJarUrlConnection.getInputStream();
    }
}
