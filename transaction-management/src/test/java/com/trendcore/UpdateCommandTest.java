package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;
import com.trendcore.transaction.TransactionHandler;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UpdateCommandTest {

    public static class Actor {
        public static Column<Integer> actor_id;
        public static Column<String> first_name;
        public static Column<String> last_name;
        public static Column<Timestamp> last_update;
    }

    @Test
    public void testBasicUpdate() throws Exception {
        Actor actor = new Actor();
        TableDescriptor tableDescriptor = Table.init(Actor.class,"actor");
        tableDescriptor.setPrimaryKey(Actor.actor_id);




        Row<Actor> row = new Tuple<>(tableDescriptor.getColumns().size());

        row.set(Actor.actor_id,1);
        row.set(Actor.first_name,"AAAA");
        row.set(Actor.last_name,"BBBB");
        row.set(Actor.last_update,new Timestamp(System.currentTimeMillis()));


        List<Row> rows = new ArrayList<>();
        rows.add(row);

        DataSource dataSource = HikariDataSource.get("test").getDataSource();

        TransactionHandler t = new TransactionHandler(dataSource);
        t.execute(connection -> {
            UpdateCommand updateCommand = new UpdateCommand(dataSource.getConnection());
            updateCommand.execute(tableDescriptor,rows);
        });

        //language=MYSQL-SQL
        String sql = "select * from actor a where a.actor_id = 1";
    }
}
