package com.trendcore.batch;

import com.monitorjbl.xlsx.StreamingReader;
import com.trendcore.*;
import com.trendcore.sql.Column;
import com.trendcore.sql.Seq;
import com.trendcore.sql.Table;
import com.trendcore.transaction.TransactionHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ImportXLS {

    @Test
    public void readXLSAndPrintOnConsole() throws FileNotFoundException {

        Workbook workbook = getWorkbook();

        for (Sheet sheet : workbook) {
            System.out.println(sheet.getSheetName());
            for (Row r : sheet) {
                for (Cell c : r) {
                    System.out.println(c.getStringCellValue());
                }
            }
        }
    }

    @Test
    public void creatingInputStream() {
        Workbook workbook = getWorkbook();

        workbook.sheetIterator().forEachRemaining(sheets -> processSheets(sheets));
    }

    public Workbook getWorkbook() {
        InputStream is = ImportXLS.class.getClassLoader().getResourceAsStream("actor-new.xlsx");
        return StreamingReader.builder()
                .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .open(is);
    }

    private void processSheets(Sheet sheet) {
        Stream<Row> stream = StreamSupport.stream(sheet.spliterator(), false);
        stream.skip(1).forEach(cells -> {


            Stream<Cell> cellStream = StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(cells.cellIterator(), Spliterator.NONNULL),
                    false);

            cellStream.forEach(cell -> System.out.println(cell.getStringCellValue()));
        });
    }

    @Test
    public void testDatabaseOperationStrategy() throws Exception {

        //language=JSON
        String json = "{\n" +
                "  //This is with ids  \n" +
                "  \"actor\" : {\n" +
                "      \"fields\" : [\"firstname\",\"lastname\"],\n" +
                "      \"data\" : [\n" +
                "        [\"PENELOPE\",\"GUINESS\"],\n" +
                "        [\"NICK\",\"WAHLBERG\"],\n" +
                "        [\"ED\",\"CHASE\"]    \n" +
                "      ]\n" +
                "            \n" +
                "  },\n" +
                "  \"film\" : {\n" +
                "      \"fields\" : [\"film_id\",\"title\",\"desc\"],\n" +
                "      \"data\" : [\n" +
                "        [\"seqGenerator()\" , \"VARSITY TRIP\" , \"Action Packed\"],\n" +
                "        [\"seqGenerator()\" , \"NEWSIES STORY\" , \"Action Packed\"]\n" +
                "      ]\n" +
                "  },\n" +
                "  \"file_actor\" : {\n" +
                "    \"fields\" : [\"actor_id\",\"film_id\"],\n" +
                "    \"data\" : [\n" +
                "      [\"foreignKeyActorId()\" , \"foreignKeyFilmId()\"]\n" +
                "    ]\n" +
                "  }\n" +
                "}\n" +
                "  \n";


        //d.inputStream();

        Workbook workbook = getWorkbook();


        //Imperative code

        //analyze table and make prepare statement for them
        //needs to be done in batch

        //https://stackoverflow.com/questions/515278/insert-rows-parent-and-children-programmatically
        /*

            prepareStatement for all tables

            actor -> is prepare statement is present else compile
                stream ->
                    single record
                        enter ids
                    store id
                        enter its childs
                            address ->


                            table strucutre
                            //works well in case of one to one and one to many

                            actor ->
                                fields ->
                                data ->
                                    row 1 ->
                                        relational data ->
                                            address ->
                                                fields ->
                                                row1 ->
                                                row1 ->
        * */


        TransactionHandler transactionHandler = new TransactionHandler(HikariDataSource.get().getDataSource());

        TableDescriptor actorTableDescriptor = Table.init(Actor.class,"actor");


        transactionHandler.execute(connection -> {


            Seq seq = new Seq(0);

            DefaultInsertCommand d = new DefaultInsertCommand(connection);


            workbook.sheetIterator().forEachRemaining(sheets -> {
                Stream<Row> stream = StreamSupport.stream(sheets.spliterator(), false);
                stream.skip(1).map(cells -> {

                    Cell firstnameCell = cells.getCell(1);
                    Cell lastnameCell = cells.getCell(2);

                    com.trendcore.sql.Row<Actor> row = new Tuple(4);
                    row.set(Actor.actor_id, seq.next());
                    row.set(Actor.first_name, firstnameCell.getStringCellValue());
                    row.set(Actor.last_name, lastnameCell.getStringCellValue());
                    row.set(Actor.last_update, new Timestamp(System.currentTimeMillis()));

                    return row;
                }).forEach(row -> {
                    d.insert(actorTableDescriptor,row);
                });
            });
        });

    }

    public static class Actor {
        public static Column<Integer> actor_id;
        public static Column<String> first_name;
        public static Column<String> last_name;
        public static Column<Timestamp> last_update;
    }
}
