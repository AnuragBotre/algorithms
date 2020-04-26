package com.trendcore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryStorageService implements StorageService {

    Map<UUID,ExecutionTask> map = new HashMap<>();

    @Override
    public void registerExecutionTaskDetails(ExecutionTask root) {
        map.put(root.getTaskId(),root);
    }
}
