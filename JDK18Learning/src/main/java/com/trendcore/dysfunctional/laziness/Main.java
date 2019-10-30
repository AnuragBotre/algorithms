package com.trendcore.dysfunctional.laziness;

public class Main {

    public static void main(String[] args) {
        DataFileMetadata d = new DataFileMetadata();

        //This method is called before load contents which will return null.
        //There is an inter-dependency between the getContents method and the loadContents
        // method that we’ve left for client code to resolve.
        //Below FileManager is the example.
        System.out.println(d.getContents());


        FileManager fileManager = new FileManager();
        fileManager.process(d);

        /*
         *If you were to submit this code for code review, you may get back a some informative feedback that the call to
         * FileManager::process will result in null values being stored in our HashMap and that you need to call
         * metadata.loadContents inside the process method.
         * Alternatively the feedback may ask that you move the call to FileManager::process to somewhere further down the call
         * stack to after a point where loadContents has been called on the application DataFileMetadata files.
         * Or perhaps you need to check.
         */

        //Solution
        //step - 1
        //Encapsulating the relationship



    }

}
