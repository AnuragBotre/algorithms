package com.trendcore.dao.users;

import com.trendcore.dao.Column;
import com.trendcore.dao.DatabaseRow;
import com.trendcore.dao.sql.AutoIncrementCommandPreparedStatement;
import com.trendcore.dao.sql.DatabaseSqlUtils;
import com.trendcore.dao.sql.TableRow;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anurag on 12/18/2016.
 */
public class NewUser implements TableRow {

    private DatabaseRow row;

    private static Column<Integer> id = new Column<>("id",Integer.class);
    private static Column<String> firstname = new Column<>("name",String.class);

    public NewUser() {
        row = new DatabaseRow("test");
    }

    public static Column<Integer> getId() {
        return id;
    }

    public static Column<String> getFirstname() {
        return firstname;
    }

    public DatabaseRow getRow() {
        return row;
    }

    public static void main(String[] args) throws SQLException {
        final NewUser user = new NewUser();

        //user.getRow().putColumn(NewUser.getId(),1);
        user.getRow().putColumn(NewUser.getFirstname(),"Anurag");


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/event","anurag","anurag");

        DatabaseSqlUtils d = new DatabaseSqlUtils(connection);

        d.insert(user.getRow(),new AutoIncrementCommandPreparedStatement(){
            @Override
            public void handleAutoIncrementKey(ResultSet generatedKeys) throws SQLException {
                user.getRow().putColumn(NewUser.getId(),generatedKeys.getInt(1));
            }
        });

        System.out.println(user.getRow().getColumns().get(NewUser.getId()));

        final NewUser majortUser = new NewUser();
        majortUser.getRow().putColumn(NewUser.getFirstname(),"Majort");

        Map<Column<?>, Object> whereCondition = new HashMap<>();
        whereCondition.put(NewUser.getId(),1);
        d.update(majortUser.getRow(),whereCondition);
    }

    @Override
    public Map<Column<?>, Object> getColumns() {
        return row.getColumns();
    }

    @Override
    public String getName() {
        return "User";
    }
}
