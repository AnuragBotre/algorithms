package com.trendcore.agent;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgumentParserUtil {

    public static Map<String, String> parserArgs(String agentArgs) {
        Map<String, String> collect = Arrays.stream(agentArgs.split(";"))
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    String[] split = s.split("=");
                    return split;
                }).collect(Collectors.toMap(strings -> strings[0], o -> o[1]));

        return collect;

    }
}
