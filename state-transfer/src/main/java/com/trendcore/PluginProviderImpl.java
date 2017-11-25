package com.trendcore;

/**
 * Created by Anurag on 4/25/2017.
 */
public class PluginProviderImpl implements PluginProvider{

    private Callback callback;

    @Override
    public Object getPluginDefinition(long l) {
        callback.call(l);
        return null;
    }

    @Override
    public void closeConnection() {

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
