package com.trendcore.dao.sql;

import com.trendcore.dao.Column;
import com.trendcore.dao.DatabaseRow;
import com.trendcore.errorcode.MySQLErrorCode;
import com.trendcore.exception.SystemException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseSqlUtils {
	
	private Connection connection;


	public DatabaseSqlUtils(Connection connection) {
		this.connection = connection;
	}


    public void insert(DatabaseRow row) {
        insert(row,null);
    }

	public void insert(DatabaseRow row,CommandPreparedStatement commandPreparedStatement) {
		
		Map<Column<?>, Object> columns = row.getColumns();
		
		StringBuilder insertQuery = new StringBuilder("INSERT INTO " + row.getTablename());

        prepareInsertQuery(columns, insertQuery);

        setValuesAndExecuteQuery(columns,insertQuery, commandPreparedStatement);

		/*try (CommandPreparedStatement ps = connection.prepareStatement(insertQuery.toString())) {

			int i = 1;
			for(Map.Entry<Column<?>, Object> columnEntry : columns.entrySet()){
				ps.setObject(i, columnEntry.getValue());
				i++;
			}

			ps.executeUpdate();

			try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}*/
	}



    private void prepareInsertQuery(Map<Column<?>, Object> columns, StringBuilder insertQuery) {
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for(Map.Entry<Column<?>, Object> columnEntry : columns.entrySet()){
            Column<?> column = columnEntry.getKey();

            fields.append(column.getName()+",");
            values.append("?,");
        }

        insertQuery.append(" ( " + fields.substring(0, fields.length()-1) + " ) ");
        insertQuery.append(" VALUES ");
        insertQuery.append(" ( " + values.substring(0, values.length()-1) + " ) ");
    }

    public void update(DatabaseRow row, Map<Column<?>, Object> whereClauseColumns) {
        Map<Column<?>, Object> columns = row.getColumns();

        StringBuilder updateQuery = new StringBuilder("UPDATE  " + row.getTablename());

        prepareColumnToUpdate(columns, updateQuery);

        prepareWhereClause(updateQuery, whereClauseColumns);

        System.out.println(updateQuery);

        columns.putAll(whereClauseColumns);

        setValuesAndExecuteQuery(columns, updateQuery);
    }

    private void setValuesAndExecuteQuery(Map<Column<?>, Object> columns, StringBuilder updateQuery) {
        setValuesAndExecuteQuery(columns,updateQuery,new DefaultPreparedStatement());
    }

    private void setValuesAndExecuteQuery(Map<Column<?>, Object> columns, StringBuilder updateQuery, CommandPreparedStatement cmdPreparedStatement) {


        try (java.sql.PreparedStatement ps = cmdPreparedStatement.getPreparedStatement(connection,updateQuery.toString())) {

            int i = 1;
            for(Map.Entry<Column<?>, Object> columnEntry : columns.entrySet()){
                ps.setObject(i, columnEntry.getValue());
                i++;
            }

            cmdPreparedStatement.executeQuery(ps);

        } catch (SQLException e) {
            MySQLErrorCode errorCode = new MySQLErrorCode(e.getErrorCode());
            throw SystemException.wrap(e,errorCode);
        }
    }


    private void prepareColumnToUpdate(Map<Column<?>, Object> columns, StringBuilder updateQuery) {
        StringBuilder fields = new StringBuilder();

        for(Map.Entry<Column<?>, Object> columnEntry : columns.entrySet()){
            Column<?> column = columnEntry.getKey();

            fields.append(column.getName()+" = ?,");
        }

        updateQuery.append(" SET " + fields.substring(0, fields.length()-1));
    }

    private void prepareWhereClause(StringBuilder updateQuery, Map<Column<?>, Object> whereClauseColumns) {
        if (whereClauseColumns != null && !whereClauseColumns.isEmpty()) {
            StringBuilder whereClause = new StringBuilder();
            for(Map.Entry<Column<?>, Object> columnEntry : whereClauseColumns.entrySet()){
                whereClause.append(columnEntry.getKey().getName()+" = ?,");
            }

            updateQuery.append(" WHERE " + whereClause.substring(0, whereClause.length()-1));
        }
    }
}
