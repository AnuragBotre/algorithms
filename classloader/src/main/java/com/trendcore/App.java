package com.trendcore;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;

/**
 * Hello world!
 */
public class App {

    private Map plugins = new HashMap<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        URL url = App.class.getClassLoader().getResource("./plugin1/plugin1-1.0-SNAPSHOT.jar").toURI().toURL();

        //System.out.println(path+"plugin1/plugin1-1.0-SNAPSHOT.jar");

        URL urls[] = new URL[1];
        urls[0] = url;

        ClassLoader classLoader = App.class.getClassLoader();

        BasicContainer container = new BasicContainer();

        container.printContainerInformation();

        //ContainerClassLoader urlClassLoader = new ContainerClassLoader(urls,null);
        //If the 2nd argument is not passed then by default parent class loader will be system class loader.
        ContainerClassLoader urlClassLoader = new ContainerClassLoader(urls,classLoader);

        Class<?> aClass = Class.forName("com.trendcore.plugin.PluginService", true, urlClassLoader);
        Class[] type = {};
        Object constructorParams[] = {};
        Plugin plugin = (Plugin) aClass.getConstructor(type).newInstance(constructorParams);

        PluginConfiguration pluginConfiguration = plugin.getPluginConfiguration(container);
        System.out.println(pluginConfiguration.getPluginname());
        System.out.println(pluginConfiguration.getPluginVersion());

        container.printContainerInformation();

        container.registerPlugin(pluginConfiguration,plugin);

        //urlClassLoader.close();


        /*App app = new App();
        app.scanDirectory();*/

        App app = new App();
        app.loadSecondJar(urlClassLoader,container);



    }

    private void loadSecondJar(ClassLoader classLoader, BasicContainer container) {
        URL url = null;
        try {
            url =App.class.getClassLoader().getResource("./plugin2/plugin2-1.0-SNAPSHOT.jar").toURI().toURL();
            //System.out.println(path+"plugin1/plugin1-1.0-SNAPSHOT.jar");
            URL urls[] = new URL[1];
            urls[0] = url;
            ContainerClassLoader urlClassLoader = new ContainerClassLoader(urls, classLoader);

            Class<?> aClass = Class.forName("com.trendcore.plugin.PluginService2", true, urlClassLoader);
            Class[] type = {};
            Object constructorParams[] = {};
            Plugin plugin = (Plugin) aClass.getConstructor(type).newInstance(constructorParams);

            plugin.getPluginConfiguration(container);

            Object plugin2Interface = plugin.getClass().getMethod("getComponent",String.class).invoke(plugin,new Object[]{""});
            plugin2Interface.getClass().getMethod("pluginMethod2").invoke(plugin2Interface,new Object[]{});

            /*Plugin2Interface plugin2Interface = (Plugin2Interface) plugin.getComponent("");
            plugin2Interface.pluginMethod2();*/

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void scanDirectory() throws IOException {
        File file = new File(App.class.getClassLoader().getResource(".").getFile());

        findApps(file);
    }

    private void findApps(File files) throws IOException {
        for(File file : files.listFiles()){
            if(file.isDirectory()){
                findApps(file);
            }else{
                loadJars(file);
            }
        }
    }

    private void loadJars(File file) throws IOException {
        if(file.getName().endsWith(".jar")){

            JarFile jarFile = new JarFile(file);

            ClassLoader classLoader = App.class.getClassLoader();

            URL url = file.toURI().toURL();

            URL urls[] = new URL[1];
            urls[0] = url;

            //ContainerClassLoader urlClassLoader = new ContainerClassLoader(urls,null);
            //If the 2nd argument is not passed then by default parent class loader will be system class loader.
            ContainerClassLoader urlClassLoader = new ContainerClassLoader(urls,classLoader);

            try {
                Class<?> aClass = Class.forName("com.trendcore.Plugin", true, urlClassLoader);
                Class[] type = {};
                Object constructorParams[] = {};
                Plugin plugin = (Plugin) aClass.getConstructor(type).newInstance(constructorParams);

                BasicContainer container = new BasicContainer();

                PluginConfiguration pluginConfiguration = plugin.getPluginConfiguration(container);

                plugins.put(pluginConfiguration.getPluginname()+"_"+pluginConfiguration.getPluginVersion(),plugin);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }finally {
                jarFile.close();
            }
        }
    }
}
