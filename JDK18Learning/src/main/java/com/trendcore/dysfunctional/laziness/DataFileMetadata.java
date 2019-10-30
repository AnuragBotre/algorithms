package com.trendcore.dysfunctional.laziness;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DataFileMetadata {

    private long customerId;
    private String type;
    private File f;

    //Eager initialization with object creation.
    private String contents;

    private String loadContents() {
        try {
            contents = loadFromFile();
            return contents;
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
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
