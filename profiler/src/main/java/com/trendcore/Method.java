package com.trendcore;

import java.util.LinkedList;
import java.util.List;

public class Method {

    private final String methodName;

    private long startTime;

    private long endTime;

    private List<Method> methods = new LinkedList<>();

    private Method parent;

    private String parameterNames;

    public Method(String methodName, long startTime, Method current, String parameterNames) {
        this.methodName = methodName;
        this.parameterNames = parameterNames;
        this.startTime = startTime;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Method getParent() {
        return parent;
    }

    public String getMethodName() {
        return methodName;
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

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void setParent(Method parent) {
        this.parent = parent;
    }

    public String getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(String parameterNames) {
        this.parameterNames = parameterNames;
    }
}