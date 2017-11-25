package com.trendcore.impl;

import com.trendcore.Plugin1SpecificInterface;
import com.trendcore.Plugin2Interface;

/**
 * Created by Anurag
 */
public class Plugin2InterfaceImpl implements Plugin2Interface {

    Plugin1SpecificInterface plugin1SpecificInterface;

    public Plugin1SpecificInterface getPlugin1SpecificInterface() {
        return plugin1SpecificInterface;
    }

    public void setPlugin1SpecificInterface(Plugin1SpecificInterface plugin1SpecificInterface) {
        this.plugin1SpecificInterface = plugin1SpecificInterface;
    }

    @Override
    public void pluginMethod2() {
        plugin1SpecificInterface.method1();
    }
}
