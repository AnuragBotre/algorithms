package com.trendcore;

/**
 * Created by Anurag on 4/25/2017.
 */
public class ModelManager {

    private PluginProvider pluginProvider;

    private static ModelManager modelManager;

    public static ModelManager getInstance(){
        if(modelManager == null){
            modelManager = new ModelManager();
        }
        return modelManager;
    }


    public void setPluginProvider(PluginProvider pluginProvider) {
        this.pluginProvider = pluginProvider;
    }

    public void createModel() {
        pluginProvider.getPluginDefinition(1);
    }
}
