package com.trendcore.dao.users;

import com.trendcore.dao.Column;
import com.trendcore.dao.DatabaseRow;
import com.trendcore.dao.sql.TableRow;

import java.util.Map;

/**
 * Created by Anurag on 12/17/2016.
 */
public class User  implements TableRow {

    private static final String tablename = "Users";

    private Integer id;

    private String firstname;

    private String lastname;

    private String emailAddress;

    public static String getTablename() {
        return tablename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public static void main(String[] args) {
        User user = new User();

        user.setId(1);
        user.setFirstname("Anurag");
        user.setLastname("Botre");
        user.setEmailAddress("Anurag.Botre@trendcore.com");

        DatabaseRow row = new DatabaseRow("Users");
        row.putColumn(new Column("id",Integer.class),1);
        row.putColumn(new Column("firstname",String.class),1);
        row.putColumn(new Column("lastname",String.class),1);

    }

    @Override
    public Map<Column<?>, Object> getColumns() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
