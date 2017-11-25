package com.trendcore.plugin;

import com.trendcore.Container;
import com.trendcore.Plugin;
import com.trendcore.PluginConfiguration;
import com.trendcore.impl.Plugin1SpecificInterfaceImpl;

/**
 * Created by Anurag
 */
public class PluginService implements Plugin {


    @Override
    public PluginConfiguration getPluginConfiguration(Container container) {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setPluginname("Plugin1");
        pluginConfiguration.setPluginVersion("1.0");
        return pluginConfiguration;
    }

    @Override
    public Object getComponent(String service) {
        return new Plugin1SpecificInterfaceImpl();
    }
}
