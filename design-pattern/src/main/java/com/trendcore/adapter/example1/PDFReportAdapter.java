package com.trendcore.adapter.example1;

import com.trendcore.adapter.example1.third.party.PDFCreator;

public class PDFReportAdapter implements Reportable {

    PDFCreator creator;

    public PDFReportAdapter(PDFCreator creator) {
        this.creator = creator;
    }

    @Override
    public void saveReport() {
        creator.createPDF();
    }
}
