package com.trendcore.strategy.example1;

public abstract class StrategySearch implements Strategy {

    @Override
    public void solve() {
        while(true){
            preProcess();
            if(search()){
                break;
            }
            postProcess();
        }
    }

    protected abstract void postProcess();

    protected abstract boolean search();

    protected abstract void preProcess();
}
