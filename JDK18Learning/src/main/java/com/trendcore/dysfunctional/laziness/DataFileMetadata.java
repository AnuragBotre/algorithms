package com.trendcore.dysfunctional.laziness;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Supplier;

public class DataFileMetadata {

    private long customerId;
    private String type;
    private File f;

    //Memoize supplier which once invoked remember the value.
    private Supplier<String> contents = new ExecuteOnceSupplier<>(this::loadContents);

    private String loadContents() {
        try {
            return loadFromFile();
        } catch (IOException e) {
            throw new DataFileUnavailableException(e);
        }
    }

    private String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(f.toPath()));
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public String getContents() {
        if(contents == null) {
            loadContents();
        }
        return contents.get();
    }

    public void setContents(String contents) {

    }
}
