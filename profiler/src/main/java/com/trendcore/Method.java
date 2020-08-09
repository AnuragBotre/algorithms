package com.trendcore;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Method implements Serializable {

    private final String className;

    private final String methodName;

    private final String category;

    private long startTime;

    private long endTime;

    private List<Method> methods = new LinkedList<>();

    private Method parent;

    private String parameterNames;

    public Method(String className, String methodName, long startTime, Method parent, String parameterNames, String category) {
        this.className = className;
        this.methodName = methodName;
        this.parameterNames = parameterNames;
        this.startTime = startTime;
        this.parent = parent;
        this.category = category;
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

    public String getClassName() {
        return className;
    }

    public String getCategory() {
        return category;
    }
}
