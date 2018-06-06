package com.trendcore;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {

    public static void main(String[] args) throws IOException {
        String logFilePath = "D:\\Anurag\\eQSecurity\\BI-dumps\\eQube Server Logs\\sample-log-file.log";

        Stream<String> lines = Files.lines(Paths.get(logFilePath));

        class LogData {
            private Timestamp timestamp;
            private String number;
            private String threadname;
            private String username;
            private String logLevel;
            private String classname;
            private String msg;
            private String exception;

            @Override
            public String toString() {
                return timestamp + " " + number + " " + threadname + " " + username + " " + logLevel + " " + classname + " " + msg + "  " + exception;
            }
        }

        class Buffer {

            List<LogData> lines = new ArrayList<>();
            List<String> exception = new ArrayList<>();

            public void add(LogData line) {
                lines.add(line);
            }

            public void process(LogData line) {
                if (lines.size() > 0) {

                    Runnable getParsedContents = () -> {
                        System.out.println(lines.remove(0));
                    };
                    getParsedContents.run();

                    Runnable getExceptionStackTrace = () -> {
                        exception.forEach(s -> {
                            System.out.println(s);
                        });
                        exception.clear();
                    };
                    getExceptionStackTrace.run();

                    System.out.println("----------------------------------------------------------------------------------------");

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
                LogData logData1 = extractFields(line, matcher -> {

                    LogData logData = new LogData();

                    logData.timestamp = stringToDate(matcher.group(1));
                    logData.number = matcher.group(2);
                    logData.threadname = matcher.group(3);
                    logData.username = matcher.group(4);
                    logData.logLevel = matcher.group(5);
                    logData.classname = matcher.group(6);
                    logData.msg = matcher.group(7);

                    return logData;
                });
                buffer.process(logData1);
            } catch (Exception e) {
                buffer.addException(line);
            }


            return null;
        }).forEach(a -> {

        });


        //buffer.flush();

    }

    private static <R> R extractFields(String line, Function<Matcher, R> mapValues) {
        String exp = "^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}) *(\\d*) \\[([A-Za-z-0-9]*)\\] *\\[([A-Za-z0-9]*)\\] \\[\\] *([A-Z]*) *([A-Za-z0-9$.]*) *-(.*)";
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return mapValues.apply(matcher);
    }

    public static Timestamp stringToDate(String dateString) {
        try {
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
            Date parsedDate = null;
            parsedDate = s.parse(dateString);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
