package com.trendcore.sql;

import com.trendcore.TableDescriptor;
import com.trendcore.Tuple;

import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
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
                            //TODO Exception handling
                            throw new RuntimeException(e);
                        }
                    });

            if(Table.class.isAssignableFrom(aClass)) {
                tableDescriptors.put(aClass, finalTableDescriptor);
            }
        }

        return tableDescriptor;
    }

    static <R extends Table> Row<R> row(Class<R> aClass) {
        TableDescriptor tableDescriptor = tableDescriptors.get(aClass);
        if(tableDescriptor == null){
            tableDescriptor = Table.init(aClass);
        }
        Row<R> row = new Tuple(tableDescriptor.getColumns().size());
        return row;
    }

    static Row row(ResultSetMetaData metaData, ResultSet resultSet) {
        Row row;
        try {
            row = new Tuple(metaData.getColumnCount());
            row.populate(resultSet);
            return row;
        } catch (SQLException e) {
            //TODO Exception handling
            throw new RuntimeException();
        }
    }

    static <T> T as(Class<? extends T> actorClass) {
        return null;
    }

    static <T> TableDescriptor init(Class<T> clazz, String tablename) {
        TableDescriptor tableDescriptor = init(clazz);
        tableDescriptor.setTablename(tablename);
        return tableDescriptor;
    }

    String getTableName();

    /*Row getRow();*/

    List<Column<?>> getColumns();



    /*default <T> void val(Column<T> id, T t){
        getRow().set(id,t);
    }

    default <T> T val(Column<T> id){
        return (T) getRow().get(id);
    }*/
}
