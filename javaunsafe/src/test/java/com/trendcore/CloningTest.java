package com.trendcore;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CloningTest {

    @Test
    public void cloneObject() throws Exception {

        class B{

            public Object b;

            public B(Object a) {
                b = new Object();
            }

            @Override
            public String toString() {
                return super.toString() + " B";
            }
        }

        class A implements Cloneable{

            public int a;

            public B b;

            A(){
                a = 10;
                b = new B(this);
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public String toString() {
                return super.toString() + " " + a;
            }
        }

        A a = new A();

        A a1 = (A) a.clone();

        System.out.println(a1.hashCode() + " " + a.hashCode());
        System.out.println(a1.b.hashCode() + " " + a.b.hashCode());

        traverse(a);

    }

    private void traverse(Object a) throws IllegalAccessException {
        Map map = new HashMap<>();

        traverseInternal(a, map);

        map.entrySet().stream().forEach(o -> System.out.println(o + " "));
    }

    private void traverseInternal(Object a, Map map) throws IllegalAccessException {
        Field[] fields = a.getClass().getFields();
        for(int i = 0 ; i < fields.length ; i++){

            if(!map.containsKey(fields[i].get(a)) && isNotPrimitive(fields[i])) {
                map.put(fields[i].get(a), fields[i].get(a));
                traverseInternal(fields[i].get(a),map);
            }
        }
    }

    private boolean isNotPrimitive(Field field) {
        return !field.getType().isPrimitive();
    }
}
