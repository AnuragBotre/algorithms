package com.trendcore;

import com.trendcore.sql.Row;
import com.trendcore.sql.Table;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ActorTest {

    private EnhancedActor e = new EnhancedActor();;

    @Test
    public void checkSyntaxForTable() {
        Actor actor = new Actor();
        Row<Actor> row = Table.row(Actor.class);
        row.set(actor.ID,1);

        System.out.println(row.get(actor.ID));
    }

    @Test
    public void definingRow() {
        //Method to load Table Descriptor

        //Method to load Table Descriptor - End

        Row<EnhancedActor> row = Table.row(EnhancedActor.class);
        row.set(Actor.ID,1);
        Integer integer = row.get(Actor.ID);
        Assert.assertEquals(integer,(Object)1);
    }

    @Test
    public void syntaxForRow() {
        Row<EnhancedActor> row = Table.row(EnhancedActor.class);

        row.set(EnhancedActor.ID,1);
        row.set(EnhancedActor.NAME,"John");
        Date date = row.get(EnhancedActor.BIRTHDATE);
        String s = row.get(EnhancedActor.NAME);

        Assert.assertTrue(true);
    }
}
