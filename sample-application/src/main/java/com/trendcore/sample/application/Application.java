package com.trendcore.sample.application;

import com.trendcore.sample.application.approach1.DAOSpecs;
import com.trendcore.sample.application.approach1.MockHttpRequest;

import java.util.HashMap;
import java.util.Map;

public class Application {

    private String databaseType;

    private Map<String,Object> daos = new HashMap<>();

    private Map<String,Module> modules = new HashMap<>();

    public Application(String databaseType) {
        this.databaseType = databaseType;
    }

    public static void register() {

    }

    public Module defineModule(String moduleName) {
        Module module = modules.getOrDefault(moduleName, new Module());
        modules.put(moduleName,module);
        return module;
    }

    public void registerDao(String databaseType, DAOSpecs daoSpecs) {
        daos.put(databaseType,daoSpecs);
    }

    public void invoke(String s, MockHttpRequest mockHttpRequest) {
        modules.forEach((s1, module) -> {
            if(s.contains(module.getBaseUrl())){

                String replace = s.replace(module.getBaseUrl(), "");

                Object apply = module.getMap().get(replace).apply(mockHttpRequest);
            }
        });
    }
}
