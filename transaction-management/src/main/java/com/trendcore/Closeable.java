package com.trendcore;

public interface Closeable {

    static void close(AutoCloseable closeable) {
        if(closeable != null){
            try {
                closeable.close();
            } catch (Exception e) {
                //Log exception
            }
        }
    }

    static void close(java.io.Closeable closeable) {
        if(closeable != null){
            try {
                closeable.close();
            } catch (Exception e) {
                //Log exception
            }
        }
    }
}
