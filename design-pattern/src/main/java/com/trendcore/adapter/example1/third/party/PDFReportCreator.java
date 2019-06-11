package com.trendcore.adapter.example1.third.party;

public class PDFReportCreator implements PDFCreator {

    @Override
    public void createPDF() {
        System.out.println("Create PDF and save.");
    }
}
