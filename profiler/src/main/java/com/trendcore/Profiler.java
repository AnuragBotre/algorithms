package com.trendcore;

import java.util.UUID;
import java.util.function.Supplier;

public class Profiler {

    private Method root;

    private Method current;

    static ThreadLocal<ExecutionTask> profiler = new ThreadLocal<>();

    private static StorageService storageService = new InMemoryStorageService();

    public static void pushMethod(String className,String methodName, long startTime, String parameterNames) {
        ExecutionTask request = profiler.get();
        if (request == null) {
            request = new ExecutionTask(UUID.randomUUID());
            profiler.set(request);
        }

        request.addMethod(className,methodName, startTime, parameterNames);
    }

    public static void pushMethod(String className, String methodName, long startTime, String parameterNames, String category, Object... args) {
        ExecutionTask request = profiler.get();
        if (request == null) {
            request = new ExecutionTask(UUID.randomUUID());
            profiler.set(request);
        }

        request.addMethod(className, methodName, startTime, parameterNames);
    }


    public static void popMethod(long endTime) {
        ExecutionTask request = profiler.get();
        if (request != null) {
            Method method = request.removeMethod(endTime);

            if (method == null) {
                //clear thread local
                storageService.registerExecutionTaskDetails(request);
                profiler.remove();
            }
        }


    }


    public static void wrap(Runnable runnable) {

    }

    public static void wrap(Supplier consumer) {

    }
}
