package com.trendcore.decorator.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Base64;


public class ClientForDecoratorPattern {

    public static void main(String[] args) throws IOException {

        String testData = stringToByteArray("This is test");
        System.out.println(testData);

    }


    public static String stringToByteArray(Object object) throws IOException {
        String stString = null;
        if (object != null) {

            /**
             * Byte array output stream is passed to object output stream.
             */
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            /**
             * oos will write object content into byte array.
             */
            oos.writeObject(object);

            byte[] encodedST = Base64.getEncoder().encode(baos.toByteArray());
            stString = new String(encodedST);

        }
        return stString;
    }

}
