package com.trendcore;

import java.util.UUID;

public class ExecutionTask {

    private Method root;

    private Method current;

    private long startTime;

    private long endTime;

    private UUID taskId;

    public ExecutionTask(UUID taskId) {
        this.taskId = taskId;
    }


    public void addMethod(String methodName, long startTime, String parameterNames) {
        if (root == null) {
            root = new Method(methodName, startTime, current, parameterNames);
            current = root;
        } else {
            Method method = new Method(methodName, startTime, current, parameterNames);
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
}
