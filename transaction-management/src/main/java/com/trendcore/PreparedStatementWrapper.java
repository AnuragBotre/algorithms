package com.trendcore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementWrapper{

    PreparedStatement preparedStatement;

    List<ResultSet> resultSets;

    public PreparedStatementWrapper(PreparedStatement preparedStatement){
        this.preparedStatement = preparedStatement;
        resultSets = new ArrayList<>(2);
    }


}
