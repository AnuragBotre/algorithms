package com.trendcore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {

    static class Tuple2<V1,V2>{
        public V1 field1;
        public V2 field2;

        public Tuple2(V1 field1, V2 field2) {
            this.field1 = field1;
            this.field2 = field2;
        }

        @Override
        public String toString() {
            return "Tuple2{" +
                    "field1=" + field1 +
                    ", field2=" + field2 +
                    '}';
        }
    }

    public static void main(String[] args) {
        String string = "The history of New York begins around 10,000 BC, when the first Native Americans arrived. By 1100 AD, New York's main native cultures, the Iroquoian and Algonquian, had developed. European discovery of New York was led by the French in 1524 and the first land claim came in 1609 by the Dutch. As part of New Netherland, the colony was important in the fur trade and eventually became an agricultural resource thanks to the patroon system. In 1626 the Dutch bought the island of Manhattan from Native Americans.[1] In 1664, England renamed the colony New York, after the Duke of York (later James II & VII.) New York City gained prominence in the 18th century as a major trading port in the Thirteen Colonies.\r\n" +
                "New York played a pivotal role during the American Revolution and subsequent war. The Stamp Act Congress in 1765 brought together representatives from across the Thirteen Colonies to form a unified response to British policies. The Sons of Liberty were active in New York City to challenge British authority. After a major loss at the Battle of Long Island, the Continental Army suffered a series of additional defeats that forced a retreat from the New York City area, leaving the strategic port and harbor to the British army and navy as their North American base of operations for the rest of the war. The Battle of Saratoga was the turning point of the war in favor of the Americans, convincing France to formally ally with them. New York's constitution was adopted in 1777, and strongly influenced the United States Constitution. New York City was the national capital at various times between 1785 and 1790, where the Bill of Rights was drafted. Albany became the permanent state capital in 1797. In 1787, New York became the eleventh state to ratify the United States Constitution.";

        Arrays.stream(string.split("\r\n")).parallel().flatMap(line ->
                Arrays.stream(line.split(" "))
        ).map(s -> new Tuple2<>(s,1))
        .collect(() -> new HashMap<String,Integer>(),(stringIntegerHashMap, tuple2) -> {
            Integer integer = stringIntegerHashMap.get(tuple2.field1);
            if(integer != null){
                stringIntegerHashMap.put(tuple2.field1,integer + 1);
            } else {
                stringIntegerHashMap.put(tuple2.field1,tuple2.field2);
            }

        },(stringIntegerHashMap, stringIntegerHashMap2) -> {

            stringIntegerHashMap.entrySet().forEach(elementInFirstMap -> {
                Integer integer = stringIntegerHashMap2.get(elementInFirstMap.getKey());
                if(integer != null) {
                    stringIntegerHashMap.put(elementInFirstMap.getKey(),integer + elementInFirstMap.getValue());
                }
            });

            stringIntegerHashMap
                    .entrySet()
                    .stream()
                    .map(stringIntegerEntry ->
                            new Tuple2<>(stringIntegerEntry.getKey(),stringIntegerEntry.getValue()))
                    .forEach(stringIntegerTuple2 -> System.out.println(stringIntegerTuple2));

        });
    }

}
