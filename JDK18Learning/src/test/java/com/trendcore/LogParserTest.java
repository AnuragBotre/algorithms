package com.trendcore;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogParserTest {

    @Test
    public void stringToDateTest() throws Exception {
        Timestamp timestamp = LogParser.stringToDate("2018-06-04 16:46:00,832");
        System.out.println(timestamp);
    }


}
