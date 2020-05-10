package com.trendcore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryStorageService implements StorageService {

    private Map<UUID, ExecutionTask> map = new HashMap<>();

    private String location = "profiler.map";

    public Map<UUID, ExecutionTask> getMap() {
        return map;
    }

    public void setMap(Map<UUID, ExecutionTask> map) {
        this.map = map;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void registerExecutionTaskDetails(ExecutionTask root) {
        map.put(root.getTaskId(), root);
    }

    @Override
    public void dump() {
        try {
            File file = new File(location);
            try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);) {
                objectOutputStream.writeObject(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
