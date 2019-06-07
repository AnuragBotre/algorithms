package com.trendcore.factory;

public class ColorFactory implements AbstractFactory<Color> {

    @Override
    public Color create(String choice){

        if(choice.equals("blue")){
            return new Blue();
        }else{
            return new Black();
        }
    }
}
