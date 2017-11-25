package com.trendcore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anurag
 */
public class BasicContainer implements Container{

    private Map<String, Plugin> pluginConfigurationRegistry = new HashMap<>();

    public void registerPlugin(PluginConfiguration pluginConfiguration,Plugin plugin) {
        pluginConfigurationRegistry.put(pluginConfiguration.getPluginname(),plugin);
    }

    @Override
    public Plugin getPlugin(String pluginname) {
        return pluginConfigurationRegistry.get(pluginname);
    }

    public void printContainerInformation(){
        System.out.println(pluginConfigurationRegistry);
    }
}
