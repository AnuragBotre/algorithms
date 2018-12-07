package com.trendcore.sql;

import com.trendcore.Actor;
import com.trendcore.TableDescriptor;
import com.trendcore.Tuple;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Table {

    Map<Class<Table>,TableDescriptor> tableDescriptors = new ConcurrentHashMap<>();

    static TableDescriptor init(Class aClass) {

         TableDescriptor tableDescriptor = tableDescriptors.get(aClass);

        if(tableDescriptor == null){
            tableDescriptor = new TableDescriptor();
            Seq seq = new Seq();
            TableDescriptor finalTableDescriptor = tableDescriptor;
            Arrays.asList(aClass.getDeclaredFields()).stream().filter(field -> field.getType().isAssignableFrom(Column.class))
                    .forEach(field -> {
                        try {
                            Column c = new Column();
                            c.setName(field.getName());
                            c.setType((Class) ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0]);
                            c.setIndex(seq.next());
                            field.set(null,c);
                            finalTableDescriptor.add(c);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

            if(aClass.isInstance(Table.class)) {
                tableDescriptors.put(aClass, finalTableDescriptor);
            }
        }

        return tableDescriptor;
    }

    static <T extends Table> Row<T> row(Class<T> aClass) {
        TableDescriptor tableDescriptor = tableDescriptors.get(aClass);

        if(tableDescriptor != null){
            Row<T> row = new Tuple(tableDescriptor.getColums().size());
            return row;
        }

        throw new RuntimeException("table descriptor not found");
    }

    static Row row(ResultSetMetaData metaData, ResultSet resultSet) {
        Row row = null;
        try {
            row = new Tuple(metaData.getColumnCount());
            row.populate(resultSet);
            return row;
        } catch (SQLException e) {
            //TODO Exception handling
            throw new RuntimeException();
        }
    }

    String getTableName();

    Row getRow();

    List<Column<?>> getColumns();

    default <T> void val(Column<T> id, T t){
        getRow().set(id,t);
    }

    default <T> T val(Column<T> id){
        return (T) getRow().get(id);
    }
}
