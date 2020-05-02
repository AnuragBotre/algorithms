package com.trendcore;

import java.util.Map;

public interface MethodProcessor {


    Map<String, String> process(String category, String className, String methodName, Object[] methodArgs);
}
