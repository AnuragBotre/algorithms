package com.trendcore;

import javassist.*;

public class JavaAssistJarReader {

    public static void main(String[] args) throws Exception {
        /*ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath( "c:/Test.jar" );
        CtClass cc = pool.get("com.test.TestFunction");
        CtMethod m = CtNewMethod.make("public void test2() { System.out.println(\"test2\"); }", cc);
        cc.addMethod(m);
        CtMethod cm = cc.getDeclaredMethod("test1", new CtClass[0]);
        cm.insertBefore("{ test2();}");

        byte[] b = cc.toBytecode(); // convert the new class to bytecode.
        pool.removeClassPath(cp);   // need to remove the classpath to release connection to JAR file so we can update it.
        JarHandler jarHandler = new JarHandler();
        jarHandler.replaceJarFile("C:/Test.jar", b, "com/test/TestFunction.class");*/


    }

}
