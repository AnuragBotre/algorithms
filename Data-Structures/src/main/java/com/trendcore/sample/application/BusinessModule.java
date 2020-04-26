package com.trendcore.sample.application;

public class BusinessModule {
    public String getBusinessObject(int i, String test) {
        DataAccessLayer dataAccessLayer = new DataAccessLayer();
        String s = dataAccessLayer.fetchData(i,test);
        return s;
    }
}
