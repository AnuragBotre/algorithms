package com.trendcore.agent.loader;

import com.trendcore.agent.ClassTransformerLoader;

import java.lang.instrument.Instrumentation;

public class Main {

    public static void go(String agentArgs, Instrumentation inst) {
        ClassTransformerLoader.go(agentArgs,inst);
    }

}
