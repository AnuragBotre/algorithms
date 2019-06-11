package com.trendcore.adapter.example1;

import com.trendcore.adapter.example1.third.party.PDFCreator;
import com.trendcore.adapter.example1.third.party.PDFReportCreator;

public class ClientForAdapterPatternExample1 {

    public static void main(String[] args) {
        Reportable wordReport = new WordReport();
        wordReport.saveReport();

        Reportable excelReport = new ExcelReport();
        excelReport.saveReport();

        PDFCreator pdfCreator = new PDFReportCreator();
        Reportable pdfReport = new PDFReportAdapter(pdfCreator);
        pdfReport.saveReport();
    }

}
