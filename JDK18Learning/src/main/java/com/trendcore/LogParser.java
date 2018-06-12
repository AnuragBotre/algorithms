package com.trendcore;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {

    public static void main(String[] args) {

        try {
            Files.list(Paths.get("D:\\Anurag\\eQSecurity\\BI-dumps\\eQube Server Logs"))
                    .filter(path -> !path.getFileName().toString().contains("sample-log-file.log"))
                    //.filter(path -> path.getFileName().toString().contains("eQubeServerLog.12.log"))
                    .forEach(path -> {
                        System.out.println(path.toString());
                        parse(path.toString());
                    });
        } catch (Exception e) {
            System.out.println("Inside main");
            e.printStackTrace();
        }
        //parse("D:\\Anurag\\eQSecurity\\BI-dumps\\eQube Server Logs\\sample-log-file.log");
    }

    private static void parse(String logFilePath) {

        class Sequence{
            int count;

            public void inc() {
                count++;
            }
        }

        Sequence sequence = new Sequence();

        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(logFilePath), StandardCharsets.ISO_8859_1);


            class LogData {
                private Timestamp timestamp;
                private String num;
                private String threadname;
                private String username;
                private String logLevel;
                private String classname;
                private String message;
                private String exception;

                @Override
                public String toString() {
                    return timestamp + " " + num + " " + threadname + " " + username + " " + logLevel + " " + classname + " " + message + "  " + exception;
                }
            }

            class TransactionManager {

                private String driverClass;
                private String url;
                private String username;
                private String password;

                private Connection connection;

                private String INSERT_QUERY = "INSERT INTO SS_LOGS (TIMESTAMP,NUM,THREADNAME,USERNAME,LOGLEVEL,CLASSNAME,MESSAGE,EXCEPTION) " +
                        "                                   VALUES (?,         ?,    ?,         ?,      ?,      ?,         ?,        ?)";

                class PrepareStatementCounter {
                    int i = 1;

                    public int inc() {
                        int j = i;
                        i++;
                        return j;
                    }
                }


                public TransactionManager(String driverClass, String url, String username, String password) {
                    this.driverClass = driverClass;
                    this.url = url;
                    this.username = username;
                    this.password = password;
                }

                public void init() {
                    try {
                        Class.forName(driverClass);
                        this.connection = DriverManager.getConnection(url, username, password);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Could not initialized database connection.");
                    } catch (SQLException e) {
                        throw new RuntimeException("Could not initialized database connection.");
                    }

                }

                public void insert(LogData logData) throws SQLException {
                    PreparedStatement preparedStatement = null;
                    try {
                        preparedStatement = connection.prepareStatement(INSERT_QUERY);

                        PrepareStatementCounter counter = new PrepareStatementCounter();
                        setValuesOnPs(logData.timestamp, counter, preparedStatement);
                        setValuesOnPs(logData.num, counter, preparedStatement);
                        setValuesOnPs(logData.threadname, counter, preparedStatement);
                        setValuesOnPs(logData.username, counter, preparedStatement);
                        setValuesOnPs(logData.logLevel, counter, preparedStatement);
                        setValuesOnPs(logData.classname, counter, preparedStatement);
                        setValuesOnPs(logData.message, counter, preparedStatement);
                        setValuesOnPs(logData.exception, counter, preparedStatement);

                        preparedStatement.executeUpdate();
                    } finally {
                        close(preparedStatement);
                    }
                }

                private void close(AutoCloseable c) {
                    if (c != null) {
                        try {
                            c.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                private void setValuesOnPs(Object obj, PrepareStatementCounter counter, PreparedStatement ps) throws SQLException {
                    if (obj != null) {
                        ps.setObject(counter.inc(), obj);
                    } else {
                        ps.setNull(counter.inc(), Types.VARCHAR);
                    }
                }
            }

            TransactionManager t = new TransactionManager("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:ORCL", "EQ_SECURITY_PRODUCT", "EQ_SECURITY_PRODUCT");
            t.init();

            class Buffer {

                List<LogData> lines = new ArrayList<>();
                List<String> exception = new ArrayList<>();

                public void add(LogData line) {
                    lines.add(line);
                }

                public void process(LogData line) {
                    if (lines.size() > 0) {

                        Consumer<LogData> getParsedContents = logData -> {
                            //System.out.println(lines.remove(0));
                            //System.out.println(logData);
                        };
                        LogData dataTobeProcessed = lines.remove(0);
                        getParsedContents.accept(dataTobeProcessed);

                        Runnable getExceptionStackTrace = () -> {
                        /*exception.forEach(s -> {
                            System.out.println(s);
                        });
                        exception.clear();*/

                            Pattern compile = Pattern.compile("^\\s+at\\s+[\\w.]+");
                            long count = exception.stream()
                                    .limit(2)
                                    .filter(s -> {
                                        try {
                                            return compile.matcher(s).find();
                                        } catch (Exception e) {
                                            return false;
                                        }
                                    }).count();


                            StringBuilder builder = new StringBuilder();
                            exception.forEach(s -> {
                                builder.append(s);
                            });
                            if (count > 0) {
                                dataTobeProcessed.exception = builder.toString();
                            } else {
                                dataTobeProcessed.message = dataTobeProcessed.message + builder.toString();
                            }

                        };
                        getExceptionStackTrace.run();
                        exception.clear();


                        Runnable addIndatabase = () -> {
                            try {
                                t.insert(dataTobeProcessed);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        };
                        addIndatabase.run();
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
                        logData.num = matcher.group(2);
                        logData.threadname = matcher.group(3);
                        logData.username = matcher.group(4);
                        logData.logLevel = matcher.group(5);
                        logData.classname = matcher.group(6);
                        logData.message = matcher.group(7);

                        return logData;
                    });
                    buffer.process(logData1);
                } catch (Exception e) {
                    buffer.addException(line);
                }
                return null;
            }).forEach(a -> {
                sequence.inc();
            });
        } catch (Exception e) {
            //Log Exception
            System.out.println("No of lines processed :- " + sequence.count);
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
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
