package com.trendcore.agent.storage;

import com.trendcore.MongoDBStorageExecution;
import com.trendcore.StorageService;

import java.util.Map;
import java.util.Properties;

public class StorageServiceFactory {


    public static StorageService getStorageService(Map<String, String> args, Type storageType) {
        switch (storageType) {
            case DATABASE:
                return MongoDBStorageExecution.getInstance(args);
            case IN_MEMORY:
            default:
                return null;
        }
    }

    public enum  Type {
        IN_MEMORY,
        DATABASE
    }
}
