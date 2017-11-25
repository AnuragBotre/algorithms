package com.trendcore.plugin;

import com.trendcore.*;
import com.trendcore.impl.Plugin2InterfaceImpl;

/**
 * Created by Anurag
 */
public class PluginService2 implements Plugin {


    private Plugin2InterfaceImpl plugin2Interface;

    @Override
    public PluginConfiguration getPluginConfiguration(Container container) {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setPluginname("Plugin2");
        pluginConfiguration.setPluginVersion("2.0");

        Plugin plugin = container.getPlugin("Plugin1");

        Plugin1SpecificInterface plugin1SpecificInterface = (Plugin1SpecificInterface) plugin.getComponent("service1");

        plugin2Interface = new Plugin2InterfaceImpl();
        plugin2Interface.setPlugin1SpecificInterface(plugin1SpecificInterface);

        return pluginConfiguration;
    }

    @Override
    public Plugin2Interface getComponent(String service) {
        return plugin2Interface;
    }

}
