package com.trendcore.adapter.example1;

public class ExcelReport implements Reportable{

    @Override
    public void saveReport() {
        System.out.println("saving excel report.");
    }
}
