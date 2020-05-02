package com.trendcore;

import java.util.Date;

public class Vararg {

    public void method1() {
        method2("1","2",3,new Date(),new ProfileMe());
    }

    private void method2(Object... args) {

    }

}
