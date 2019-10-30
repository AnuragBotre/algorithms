package com.trendcore.dysfunctional.laziness;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DataFileMetadata {

    private long customerId;
    private String type;
    private File f;
    private String contents;

    public void loadContents() {
        try {
            contents = loadFromFile();
        } catch (IOException e) {
            throw new DataFileUnavailableException(e);
        }
    }

    private String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(f.toPath()));
    }

}
