package com.trendcore;

import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class Profiler {

    private Method root;

    private Method current;

    static ThreadLocal<ExecutionTask> profiler = new ThreadLocal<>();

    private static StorageService storageService = new InMemoryStorageService();

    private static MethodProcessor methodProcessor;

    public static void registerMethodProcessor(MethodProcessor m) {
        methodProcessor = m;
    }

    public static void pushMethod(String className, String methodName, long startTime, String parameterNames) {
        registerExecution(className, methodName, startTime, parameterNames);
    }

    private static ExecutionTask registerExecution(String className, String methodName, long startTime, String parameterNames) {
        ExecutionTask request = profiler.get();
        if (request == null) {
            request = new ExecutionTask(UUID.randomUUID());
            profiler.set(request);
        }

        request.addMethod(className, methodName, startTime, parameterNames);
        return request;
    }

    public static void pushMethod(String className, String methodName, long startTime, String parameterNames, String category, Object... methodArgs) {

        ExecutionTask request = registerExecution(className, methodName, startTime, parameterNames);
        if (methodProcessor != null) {
            Map<String, String> attributes = methodProcessor.process(category, className, methodName, methodArgs);
            request.setAttributes(attributes);
        }
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
