package com.trendcore;

/**
 * Created by Anurag
 */
public interface Plugin {

    PluginConfiguration getPluginConfiguration(Container container);

    Object getComponent(String service);
}
