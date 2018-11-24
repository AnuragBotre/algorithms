package com.trendcore;

import org.junit.Test;

public class SelectTest {

    @Test
    public void selectDefinition() {
        Query query = new Query();

        Object select = query.select.from().innerJoin().leftJoin().where().groupBy().having();
    }
}
