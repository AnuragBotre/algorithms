package com.trendcore;

import org.junit.Test;

import java.io.*;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

public class InMemoryStorageServiceTest {

    @Test
    public void dump() throws IOException, ClassNotFoundException {
        String profilerDumpDirectory = InMemoryStorageServiceTest.class.getResource(".").getFile() + "../../../../dumps";
        System.out.println(profilerDumpDirectory);
        File file = new File(profilerDumpDirectory + "/" + "profiler.map");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Map<UUID,ExecutionTask> o = (Map) ois.readObject();


        o.forEach((o1, o2) -> {
            System.out.println(o1 + " " + o2);
            o2.getRoot().getMethods().forEach(method -> {
                System.out.println(method.getClassName());
            });
        });
    }

    private void method1() {

    }
}