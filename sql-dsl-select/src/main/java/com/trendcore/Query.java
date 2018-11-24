package com.trendcore;

public class Query {

    public Select select;

    public static class Select {

        public From from() {
            return new From();
        }
    }

    public static class From {

        public Join innerJoin() {
            return new Join();
        }

        public Join join() {
            return null;
        }

        public Join leftJoin() {
            return null;
        }

        public Join rightJoin() {
            return null;
        }
    }

    public static class Join {

        public Join leftJoin() {
            return new Join();
        }

        public Where where() {
            return new Where();
        }
    }

    public static class Where {

        public GroupBy groupBy() {
            return new GroupBy();
        }
    }

    public static class GroupBy{

        public Having having() {
            return new Having();
        }
    }

    public static class Having {

    }
}
