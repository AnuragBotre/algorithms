package com.trendcore.lock;

public class ConnectionContainerApp {

    interface Command<T>{
        void execute(T t);
    }

    public static void main(String[] args) {

        class DataProvider{

            public void method1(){

                getConnection();
                method1Task();
                releaseConnection();

            }

            private void method1Task() {
                System.out.println("User Fetch");
            }

            public void method2(){

                getConnection();
                method2Task();
                releaseConnection();

            }

            private void method2Task() {
                System.out.println("UserInfo Fetch");
            }

            public void specialMethod(Command command){

                getConnection();
                command.execute(this);
                releaseConnection();

            }

            private void releaseConnection() {
                System.out.println("release Connection");
            }

            private void getConnection() {
                System.out.println("get Connection");
            }

        }

        DataProvider d = new DataProvider();

        d.method1();
        d.method2();

        d.specialMethod(new Command<DataProvider>() {
            @Override
            public void execute(DataProvider dataProvider) {
                dataProvider.method1Task();
                dataProvider.method2Task();

            }
        });

        d.specialMethod(new Command<DataProvider>() {
            @Override
            public void execute(DataProvider dataProvider) {
                dataProvider.method2Task();
            }
        });

    }
}
