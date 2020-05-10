package com.trendcore;

import com.trendcore.ExecutionTask;
import com.trendcore.Method;

public interface StorageService {

    void registerExecutionTaskDetails(ExecutionTask root);

    void dump();
}
