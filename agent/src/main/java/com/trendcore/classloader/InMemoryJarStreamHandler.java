package com.trendcore.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class InMemoryJarStreamHandler extends URLStreamHandler {

    private final InputStream jarStream;

    public InMemoryJarStreamHandler(InputStream jarStream) {
        this.jarStream = jarStream;
    }

    protected void parseURL_Temp(URL u, String spec, int start, int limit) {
        String path = "jar:" + spec.substring(4);
        if (path.contains("*/")) {
            path = path.replaceFirst("\\*/", "!/");
        } else {
            path = path.replaceFirst("\\^/", "!/");
        }

        this.setURL(u, u.getProtocol(), "", -1, (String)null, (String)null, path, (String)null, (String)null);
    }

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new InMemoryJarFileConnection(u,jarStream);
    }
}
