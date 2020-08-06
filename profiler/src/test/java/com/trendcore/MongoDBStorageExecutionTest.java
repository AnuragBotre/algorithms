package com.trendcore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

public class MongoDBStorageExecutionTest {

    private MongoDBStorageExecution mongoDBStorageExecution;
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        properties = new Properties();
        mongoDBStorageExecution = new MongoDBStorageExecution(properties);
    }

    @Test
    public void initMongoDB() {
        Properties properties = new Properties();
        MongoDBStorageExecution mongoDBStorageExecution = new MongoDBStorageExecution(properties);
    }

    @Test
    public void saveExecutionInfo() {

        ExecutionTask root = getExecutionTask();
        mongoDBStorageExecution.saveExecutionSummary(root);
    }

    private ExecutionTask getExecutionTask() {
        ExecutionTask root = new ExecutionTask();
        root.setStartTime(System.currentTimeMillis() - 10);
        root.setEndTime(System.currentTimeMillis());
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("test", "test");
        root.setAttributes(attributes);
        return root;
    }

    @Test
    public void saveMethodInfo() {
        Method root = getMethod();
        mongoDBStorageExecution.saveMethodsInfo(root, UUID.randomUUID(),null, 1);
    }

    private Method getMethod() {
        Method root = new Method(MongoDBStorageExecution.class.getName(), "test", System.currentTimeMillis(), null, "");
        root.setEndTime(System.currentTimeMillis());

        Method save = new Method(MongoDBStorageExecution.class.getName(), "save", System.currentTimeMillis(), root, "java.lang.String");
        save.setEndTime(System.currentTimeMillis());

        Method innerSave1 = new Method(MongoDBStorageExecution.class.getName(), "innerSave1", System.currentTimeMillis(), save, "java.lang.String");
        innerSave1.setEndTime(System.currentTimeMillis());

        Method innerSave2 = new Method(MongoDBStorageExecution.class.getName(), "innerSave2", System.currentTimeMillis(), save, "java.lang.String");
        innerSave2.setEndTime(System.currentTimeMillis());

        Method innerSave3 = new Method(MongoDBStorageExecution.class.getName(), "innerSave3", System.currentTimeMillis(), save, "java.lang.String");
        innerSave3.setEndTime(System.currentTimeMillis());

        save.getMethods().addAll(Arrays.asList(innerSave1,innerSave2,innerSave3));

        Method method1 = new Method(MongoDBStorageExecution.class.getName(), "method1", System.currentTimeMillis(), root, "java.lang.String");
        method1.setEndTime(System.currentTimeMillis());

        root.getMethods().add(save);
        root.getMethods().add(method1);
        return root;
    }

    @Test
    public void saveCompleteExecutionInfo() {
        ExecutionTask root = getExecutionTask();

        Method rootMethod = getMethod();

        root.setRoot(rootMethod);

        mongoDBStorageExecution.saveExecutionTask(root);
    }

    @After
    public void tearDown() throws Exception {

    }
}