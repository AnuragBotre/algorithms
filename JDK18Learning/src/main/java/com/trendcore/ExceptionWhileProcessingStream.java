package com.trendcore;

import java.util.stream.Stream;

public class ExceptionWhileProcessingStream {

    public static void main(String[] args) {
        Stream<String> abc = Stream.of(null,"abc", "pqr");

        abc
            .filter(s -> s.length() > 0)
            .count();
    }

}
