package com.trendcore.spring.constructor;

/**
 * Created by Anurag
 */
public class BeanA {

    private BeanB beanB;

    /*public BeanA(BeanB beanB){
        this.beanB = beanB;
    }*/

    public BeanB getBeanB() {
        return beanB;
    }

    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }
}
