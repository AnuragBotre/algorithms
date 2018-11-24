package com.trendcore;

public class GenericsUnderstanding {

    public static void main(String[] args) {

        GenericsUnderstanding g = new GenericsUnderstanding();

        I i = getIOfA();

        g.doSomething(i);
    }

    public void anotherExample() {
        Universe universe = new Universe();

        Configuration configuration = getConfiguration();

        universe.doSomething(configuration);
    }

    private FileSystemConfiguration getConfiguration() {
        return new FileSystemConfiguration();
    }

    interface Configuration{
        void method1();
    }

    class Universe{

        public <V extends Configuration> void doSomething(V v){

        }
    }

    class FileSystemConfiguration implements Configuration{
        @Override
        public void method1() {

        }
    }

    private <B extends I> void doSomething(B i) {

    }


    private static A getIOfA() {
        return new A();
    }

    public static interface I {
        void method();
    }

    public static class A implements I {
        @Override
        public void method() {

        }

        public void method1() {

        }
    }

}
