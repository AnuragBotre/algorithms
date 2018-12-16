package com.trendcore.sql.query;

import com.trendcore.Actor;
import com.trendcore.TableDescriptor;
import com.trendcore.sql.Table;
import org.junit.Test;

import java.util.stream.Collectors;

public class DefaultInsertCommandTest {

    static class InsertQueryBuilder{
        private String colNames;
        private String colValues;
    }

    @Test
    public void formInsertQuery() {
        Actor actor = new Actor();
        TableDescriptor tableDescriptor = Table.init(Actor.class);

    }
}
