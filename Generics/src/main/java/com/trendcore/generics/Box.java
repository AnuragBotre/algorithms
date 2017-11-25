package com.trendcore.generics;

import java.util.List;

/**
 * Created by Anurag
 */
public class Box<S extends Number> {

    private S object;

    public S getObject() {
        return object;
    }

    public void setObject(S object) {
        this.object = object;
    }

    public <T,A,B> void doSomething(T t,A a, B b) {
        System.out.println(t.getClass() + " " + a.getClass() + " " + b.getClass() );
    }

    public <T,M> M getSomething(T t) {
        M m = (M) t;// Some operation on that will return M
        return m;
    }

    public <U extends Number> void methodWithWildCardForNumbers(U u){
        System.out.println(u.doubleValue());
    }

    public void methodWithWildCards(Box<? super Number> box1,Box<? extends Number> box2) {
        box1.setObject(1);
        Number object = box2.getObject();
    }

    public <V extends Number,M extends Number> M getObjectWithWildCard(V v) {
        return null;
    }

    public <T extends Number> void getObjectWithWildCard(List<? super Number> v) {
        v.add(1);
        v.add(1.0);
    }
}
