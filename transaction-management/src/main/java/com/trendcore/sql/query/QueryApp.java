package com.trendcore.sql.query;

import com.trendcore.sql.Column;
import com.trendcore.sql.Table;

public class QueryApp {

    public static QueryApp queryApp = new QueryApp();

    public static class Select{
        public static From select(){
            System.out.println("select * ");
            return new From();
        }

        public static From select(Column... columns) {
            return new From();
        }
    }

    public static class From{

        public Where from(String t) {
            System.out.println(" from " + t);
            return new Where();
        }

        public Where from(Table t) {
            System.out.println(" from " + t.getTableName());
            return new Where();
        }

        public <T extends Table> void from(Class<T> table) {
            System.out.println(" from " + table);
        }
    }
    
    public static class Where{

        public void where() {

        }
    }

    public static <T extends Table> String as(String alias) {
        return alias;
    }

    public static <T extends Table> String as(Table table,String alias) {
        return alias;
    }
}
