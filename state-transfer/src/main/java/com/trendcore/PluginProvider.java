package com.trendcore;

/**
 * Created by Anurag on 4/25/2017.
 */
public interface PluginProvider {

    public Object getPluginDefinition(long l);

    public void closeConnection();

}
