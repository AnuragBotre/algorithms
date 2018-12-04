package com.trendcore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;

public class SQL {

    public static void select(Connection connection, String sql, BiConsumer<ResultSet,Long> consumer, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int cnt = 0;
            for (Object param : params) {
                ps.setObject(++cnt, param);
            }
            try (ResultSet rs = ps.executeQuery()) {
                long rowCnt = 0;
                rs.getMetaData();
                while (rs.next()) {
                    consumer.accept(rs, rowCnt++);
                }
            } catch (SQLException e) {
                //TODO exception handling
                //throw new DataAccessException(e);
            }
        } catch (SQLException e) {
            //TODO exception handling
            //throw new DataAccessException(e);
        }
    }

}
