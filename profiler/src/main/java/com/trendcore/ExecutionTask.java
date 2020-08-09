package com.trendcore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExecutionTask implements Serializable {

    private Method root;

    private Method current;

    private long startTime;

    private long endTime;

    private UUID taskId;

    private Map<String,String> attributes;

    public ExecutionTask() {
        this.taskId = UUID.randomUUID();
        attributes = new HashMap<>();
    }


    public void addMethod(String className, String methodName, long startTime, String parameterNames, String category) {
        if (root == null) {
            root = new Method(className,methodName, startTime, current, parameterNames,category);
            current = root;
        } else {
            Method method = new Method(className,methodName, startTime, current, parameterNames,category);
            current.getMethods().add(method);
            current = method;
        }
    }

    public Method removeMethod(long endTime) {
        if (current != null) {
            current.setEndTime(endTime);
            current = current.getParent();
        }
        return current;
    }

    public Method getRoot() {
        return root;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setRoot(Method root) {
        this.root = root;
    }

    public Method getCurrent() {
        return current;
    }

    public void setCurrent(Method current) {
        this.current = current;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public void setAttributes(Map<String, String> attributes) {
        attributes.putAll(attributes);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
