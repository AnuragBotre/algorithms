package com.trendcore.server;

import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Anurag
 */
public class ServerTest {

    @org.junit.Test
    public void createServer() throws Exception {

        Server server = new Server();

        server.start(8080);

    }

    @Test
    public void client() throws Exception {

        Socket socket = new Socket();

        socket.connect(new InetSocketAddress("localhost",8080));


        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("Host: www.w3.org\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:57.0) Gecko/20100101 Firefox/57.0\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                "Accept-Language: en-US,en;q=0.5\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Referer: https://www.google.co.in/\n" +
                "Connection: keep-alive\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "If-Modified-Since: Tue, 26 Oct 1999 18:40:19 GMT\n" +
                "If-None-Match: \"1e9c-357cd126a4ac0-gzip\"\n" +
                "Cache-Control: max-age=0");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        /*bufferedWriter.close();
        outputStreamWriter.close();*/

        InputStream inputStream = socket.getInputStream();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader reader = new BufferedReader(inputStreamReader);

        /*String s = null;
        while((s = reader.readLine()) != null){
            System.out.println(s);
        }*/

        byte buff[] = new byte[1024];
        int bytesRead;
        while((bytesRead = inputStream.read(buff)) != -1){
            System.out.println(new String(buff));
        }
    }
}
