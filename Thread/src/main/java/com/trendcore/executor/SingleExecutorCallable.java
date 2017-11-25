package com.trendcore.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Anurag
 */
public class SingleExecutorCallable implements Callable {

    @Override
    public Object call() throws Exception {
        List list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            Thread.sleep(2000);
            list.add(i);
        }

        return list;
    }
}
