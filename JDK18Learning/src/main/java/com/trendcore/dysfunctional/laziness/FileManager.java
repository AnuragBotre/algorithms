package com.trendcore.dysfunctional.laziness;

import java.util.HashMap;
import java.util.Map;

public class FileManager {

    private Map<Long,String> dataTable = new HashMap<>();

    public void process(DataFileMetadata metadata){

        dataTable.put(metadata.getCustomerId(),metadata.getContents());

    }

}
