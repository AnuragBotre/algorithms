package com.trendcore;

import com.trendcore.sql.Column;
import com.trendcore.sql.Row;
import com.trendcore.sql.Table;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PartitioningStream {

    public static class Address{
        public static Column<Integer> addressId;
        public static Column<String> address;
    }

    @Test(expected = IllegalStateException.class)
    public void partition() {
        List<Row<Address>> list = new ArrayList<>();
        TableDescriptor tableDescriptor = Table.init(Address.class);
        Address a = Table.as(Address.class);
        for(int i = 0 ; i < 50 ; i++){
            Row<Address> e = new Tuple<>(tableDescriptor.getColumns().size());
            e.set(a.addressId , i+1);
            e.set(a.address , "Street " + (i+1));
            list.add(e);
        }

        Stream<Row<Address>> stream = list.stream();
        stream.limit(25).forEach(addressRow -> System.out.println(addressRow.get(a.addressId)));

        stream.skip(25).forEach(addressRow -> System.out.println(addressRow.get(a.addressId)));
    }
}
