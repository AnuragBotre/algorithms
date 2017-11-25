package com.trendcore;

/**
 * Created by Anurag on 4/25/2017.
 */
public class ModelManagerWrapper {

    private static ModelManagerWrapper modelManagerWrapper;

    private PluginProviderImpl pluginProvider;

    public static ModelManagerWrapper getInstance(){
        if(modelManagerWrapper == null){
            modelManagerWrapper = new ModelManagerWrapper();
        }
        return modelManagerWrapper;
    }

    public void initializeModelManager(){
        pluginProvider = new PluginProviderImpl();
    }

    public void createModel(final Action action) {

        pluginProvider.setCallback(new Callback() {
            @Override
            public void call(long l) {
                action.execute();
            }
        });
        ModelManager.getInstance().setPluginProvider(pluginProvider);

        ModelManager.getInstance().createModel();
    }


}
