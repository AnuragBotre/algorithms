package com.trendcore;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MongoDBStorageExecution implements StorageService {

    private ExecutorService executor;

    private MongoClient mongoClient;
    private final MongoDatabase profilingDatabase;

    public MongoDBStorageExecution(Properties mongoDBProperties) {
        String host = mongoDBProperties.getProperty("host", ServerAddress.defaultHost());
        int port = Integer.parseInt(mongoDBProperties.getProperty("port", String.valueOf(ServerAddress.defaultPort())));
        String databaseName = mongoDBProperties.getProperty("databaseName", "profiling");
        mongoClient = new MongoClient(host, port);

        if (mongoDBProperties.getProperty("username") != null) {
            MongoCredential credential;
            credential = MongoCredential.createCredential(mongoDBProperties.getProperty("username"), databaseName,
                    mongoDBProperties.getProperty("password").toCharArray());
        }

        profilingDatabase = mongoClient.getDatabase(databaseName);
        executor = Executors.newFixedThreadPool(2);
    }


    public static MongoDBStorageExecution getInstance(Map<String, String> args) {
        String mongoDBPropertiesFile = args.get("mongodb.properties");
        Properties mongoDBProperties = new Properties();
        try {
            mongoDBProperties.load(new FileInputStream(new File(mongoDBPropertiesFile)));
        } catch (Exception e) {
            System.out.println("Failed to load properties for mongo database using default properties.");
            //throw new RuntimeException(e);
        }
        MongoDBStorageExecution s = new MongoDBStorageExecution(mongoDBProperties);
        return s;
    }

    @Override
    public void registerExecutionTaskDetails(ExecutionTask root) {
        executor.submit(() -> {
            try {
                saveExecutionTask(root);
            } catch (Exception e) {

            }
            return null;
        });
    }

    void saveExecutionTask(ExecutionTask root) {
        if (root != null) {
            //save execution summary
            saveExecutionSummary(root);
            saveMethodsInfo(root.getRoot(), root.getTaskId(), null , 1);
        }
    }

    void saveExecutionSummary(ExecutionTask root) {
        MongoCollection<Document> executionSummary = profilingDatabase.getCollection("executionSummary");
        Document document = new Document();
        document.put("startTime", root.getStartTime());
        document.put("endTime", root.getEndTime());
        document.put("taskId", root.getTaskId());
        document.put("attributes", root.getAttributes());
        executionSummary.insertOne(document);
    }

    void saveMethodsInfo(Method method, UUID taskId, ObjectId parentObjectId, long i) {
        if (method != null) {
            //save current method details
            MongoCollection<Document> methodInformation = profilingDatabase.getCollection("methodInformation");
            Document document = new Document();
            document.put("executionTaskId", taskId);
            document.put("className", method.getClassName());
            document.put("methodName", method.getMethodName());
            document.put("startTime", method.getStartTime());
            document.put("endTime", method.getEndTime());
            document.put("parameterNames", method.getParameterNames());
            document.put("methodInvocationIndex", i);

            if (parentObjectId != null) {
                document.put("parentMethodId", parentObjectId);
            }

            methodInformation.insertOne(document);

            ObjectId id = document.getObjectId("_id");

            List<Method> methods = method.getMethods();
            long methodInvocationIndex = 1;
            for (Method m : methods) {
                saveMethodsInfo(m, taskId, id, methodInvocationIndex);
                methodInvocationIndex++;
            }
        }
    }

    @Override
    public void dump() {

    }
}
