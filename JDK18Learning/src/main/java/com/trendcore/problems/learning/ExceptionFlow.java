package com.trendcore.problems.learning;

public class ExceptionFlow {

    public static void main(String[] args) throws Exception {


        ExceptionFlow e = new ExceptionFlow();

        e.someMethod();
    }

    private void someMethod() throws Exception {

        try{

            boolean flag = true;

            if(flag){
                throw new RuntimeException();
            }else {
                throw new Exception();
            }


        } catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e1) {
            throw e1;
        }

    }

}
