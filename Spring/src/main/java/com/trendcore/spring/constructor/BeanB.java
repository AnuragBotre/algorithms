package com.trendcore.spring.constructor;

/**
 * Created by Anurag
 */
public class BeanB {

    private BeanA beanA;

    public BeanB(BeanA beanA){
        this.beanA = beanA;
    }

    /*public BeanA getBeanA() {
        return beanA;
    }

    public void setBeanA(BeanA beanA) {
        this.beanA = beanA;
    }*/
}
