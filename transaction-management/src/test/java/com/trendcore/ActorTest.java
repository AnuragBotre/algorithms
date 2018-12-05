package com.trendcore;

import org.junit.Test;

public class ActorTest {

    @Test
    public void checkSyntaxForTable() {
        Actor actorRow = new Actor();
        actorRow.val(actorRow.ID,1);

        System.out.println(actorRow.val(actorRow.ID));
    }
}
