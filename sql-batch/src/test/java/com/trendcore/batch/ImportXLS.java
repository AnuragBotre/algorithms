package com.trendcore.batch;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImportXLS {

    @Test
    public void readXLSAndPrintOnConsole() throws FileNotFoundException {

        InputStream is = ImportXLS.class.getClassLoader().getResourceAsStream("actor-new.xlsx");
        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .open(is);

        for (Sheet sheet : workbook){
            System.out.println(sheet.getSheetName());
            for (Row r : sheet) {
                for (Cell c : r) {
                    System.out.println(c.getStringCellValue());
                }
            }
        }
    }
}
