package com.trendcore.agent;

import org.junit.Test;

import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JavaClassTransformerTest {

    @Test
    public void testTransform() throws Exception {
        JavaClassTransformer javaClassTransformer = new JavaClassTransformer();
        Class<Thread> threadClass = Thread.class;
        String name = threadClass.getName();
        ClassLoader classLoader = javaClassTransformer.getClass().getClassLoader();

        String threadClassName = threadClass.getName().replace('.', '/');
        InputStream is = classLoader.getResourceAsStream(threadClassName + ".class");
        byte[] bytes = ClassTransformationUtil.getBytes(is);

        byte[] transform = javaClassTransformer.transform(classLoader, name, threadClass, null, bytes);


        //try to invoke run method and should get exception

        invokeClass(threadClass, transform);

    }

    private void invokeClass(Class threadClass, byte[] transform) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassDefinition classDefinition = new ClassDefinition(threadClass, transform);

        TestClassLoader testClassLoader = new TestClassLoader(classDefinition,getClass().getClassLoader());

        Class<?> aClass = Class.forName(classDefinition.getDefinitionClass().getName(),true,testClassLoader);

        Constructor<?> constructor = aClass.getConstructor(Runnable.class);

        Thread t = (Thread) constructor.newInstance(new Object[]{(Runnable) () -> System.out.println("Run")});
        t.run();

        System.out.println("test");
    }

    class TestClassLoader extends ClassLoader {

        private final ClassDefinition classDefinition;

        public TestClassLoader(ClassDefinition classDefinition, ClassLoader parent) {
            super(parent);
            this.classDefinition = classDefinition;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

            if(name.equalsIgnoreCase(classDefinition.getDefinitionClass().getName())) {
                return defineClass(null,classDefinition.getDefinitionClassFile(),0,classDefinition.getDefinitionClassFile().length);
            }

            return super.loadClass(name, resolve);
        }




    }
}