package com.trendcore;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {

    public static void main(String[] args) throws IOException {
        String logFilePath = "D:\\Anurag\\eQSecurity\\BI-dumps\\eQube Server Logs\\sample-log-file.log";

        Stream<String> lines = Files.lines(Paths.get(logFilePath));

        class Buffer {

            List<String> lines = new ArrayList<>();
            List<String> exception = new ArrayList<>();

            public void add(String line) {
                lines.add(line);
            }

            public void process(String line) {
                if(lines.size() > 0){
                    System.out.println(lines.remove(0));
                    exception.forEach(s -> {
                        System.out.println(s);
                    });
                    System.out.println("----------------------------------------------------------------------------------------");
                    exception.clear();
                }
                add(line);
            }

            public void addException(String line) {
                exception.add(line);
            }
        }

        Buffer buffer = new Buffer();

        lines.map(line -> {

            try {
                extractFields(line);
                buffer.process(line);
            }catch (Exception e){
                buffer.addException(line);
            }


            return null;
        }).forEach(a -> {

        });


        //buffer.flush();

    }

    private static void extractFields(String line) {
        String exp = "^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}) *(\\d*) \\[([A-Za-z-0-9]*)\\] *\\[([A-Za-z0-9]*)\\] \\[\\] *([A-Z]*) *([A-Za-z0-9$.]*) *-(.*)";
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        String date = matcher.group(1);
        String someNo = matcher.group(2);
        String threadname = matcher.group(3);
        String username = matcher.group(4);
        String logLevel = matcher.group(5);
        String classname = matcher.group(6);
        String msg = matcher.group(7);

        //System.out.println("Extracting fields :- " + line);
    }

}
