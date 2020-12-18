package com.trendcore;

import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8StreamsTest {

    class Tuple2<T1, T2> implements Comparable<Tuple2<T1,T2>>{
        T1 t1;
        T2 t2;

        public Tuple2(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        @Override
        public int compareTo(Tuple2<T1, T2> anotherObject) {
            return ((Comparable)this.t1).compareTo(anotherObject.t1);
        }

        @Override
        public String toString() {
            return "{" +
                    "" + t1 +
                    ", " + t2 +
                    '}';
        }
    }

    @Test
    public void selectClause() {
        Stream.of(tuple(1, 1),
                tuple(2, 2)).forEach(System.out::println);
    }

    @Test
    public void crossJoin() {
        Stream<Integer> firstStream = Stream.of(1, 2);
        Supplier<Stream<String>> secondStreamSupplier = () -> Stream.of("A", "B");

        firstStream
                .flatMap(integer -> {
                    return secondStreamSupplier
                            .get()
                            .map(
                                    string -> new Tuple2<>(integer, string)
                            )
                            ;
                })
                .forEach(tuple2 -> System.out.println(tuple2));

    }

    @Test
    public void crossJoinWithList() {
        List<Integer> listOfInteger = Arrays.asList(1, 2, 3);
        List<String> listOfStrings = Arrays.asList("A", "B", "C");

        listOfInteger.stream().flatMap(integer ->
                listOfStrings
                        .stream()
                        .map(string -> new Tuple2<>(integer, string))
        ).forEach(integerStringTuple2 -> System.out.println(integerStringTuple2));
    }

    @Test
    public void limit() {
        List<Integer> listOfInteger = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        listOfInteger
                .stream()
                .limit(5)
                .forEach(integer -> System.out.println(integer))
                ;
    }

    @Test
    public void offset() {
        List<Integer> listOfInteger = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        listOfInteger
                .stream()
                .skip(3)
                .forEach(integer -> System.out.println(integer))
        ;
    }

    @Test
    public void limitOffset() {
        List<Integer> listOfInteger = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        /**
         * Note order of limit offset is also important.
         */
        listOfInteger
                .stream()
                .skip(3)
                .limit(5)
                .forEach(integer -> System.out.println(integer))
        ;


    }

    @Test
    public void limitOffsetOrderIsImportant() {
        List<Integer> listOfInteger = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        /**
         * This code will limit to 5 elements then it will skip three elements and will show rest of elements.
         */
        listOfInteger
                .stream()
                .limit(5)
                .skip(3)
                .forEach(integer -> System.out.println(integer))
        ;
    }

    @Test
    public void groupByWithClassifier() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                                    tuple(1, "Barn"),
                                    tuple(2, "Casilo"),
                                    tuple(2, "Sergio"),
                                    tuple(3, "Mikel"));
        stream.map(o -> (Tuple2<Integer,String>)o)
            .collect(
                Collectors.groupingBy(integerStringTuple2 -> integerStringTuple2.t1)
            )
            .forEach((integer, integers) -> System.out.println(integer + " " + integers))
        ;
    }

    @Test
    public void groupByWithClassifierAndCollectionAsArrayList() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                                            tuple(1, "Barn"),
                                            tuple(2, "Casilo"),
                                            tuple(2, "Sergio"),
                                            tuple(3, "Mikel"));

        stream.map(o -> (Tuple2<Integer,String>)o)
                .collect(
                        Collectors.groupingBy(integerStringTuple2 -> integerStringTuple2.t1,
                                /*This is data structure in which above values will be stored*/
                                Collectors.toCollection(() -> new ArrayList<>())
                        )
                )
                .forEach((integer, integers) -> System.out.println(integer + " " + integers))
        ;
    }

    @Test
    public void groupByWithClassifierAndCollectionAsLinkedList() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        stream.map(o -> (Tuple2<Integer,String>)o)
                .collect(
                        Collectors.groupingBy(integerStringTuple2 -> integerStringTuple2.t1,
                                /*This is data structure in which above values will be stored*/
                                Collectors.toCollection(() -> new LinkedList<>())
                        )
                )
                .forEach((integer, integers) -> System.out.println(integer + " " + integers))
        ;
    }

    @Test(expected = ClassCastException.class)
    public void orderBy() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        stream
            .map(object -> (Tuple2<Integer,String>)object)
            .sorted()
            .forEach(tuple2 -> System.out.println(tuple2))
        ;
    }

    @Test
    public void orderByWithComparator() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        stream
                .map(object -> (Tuple2<Integer,String>)object)
                .sorted((firstObject, secondObject) -> firstObject.t1.compareTo(secondObject.t1))
                .forEach(tuple2 -> System.out.println(tuple2))
        ;
    }

    @Test
    public void orderByWithComparatorClass() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        stream
                .map(object -> (Tuple2<Integer,String>)object)
                .sorted(
                        Comparator
                                .comparing(tuple2 -> tuple2.t1)



                )
                .forEach(tuple2 -> System.out.println(tuple2))
        ;
    }

    @Test
    public void orderByReverseOrder() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        stream
                .map(object -> (Tuple2<Integer,String>)object)
                .sorted(Comparator.reverseOrder())
                .forEach(tuple2 -> System.out.println(tuple2))
        ;
    }

    @Test
    public void reduce() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        Optional<Tuple2<Integer, String>> reduce = stream
                                                    .map(o -> (Tuple2<Integer, String>) o)
                                                    .reduce((prevTuple, currentTuple) -> {
                                                        return new Tuple2<Integer, String>(prevTuple.t1, currentTuple.t2);
                                                    })
                                                    ;

        System.out.println(reduce);
    }

    @Test
    public void reduce_With_Empty_Stream() {
        Stream<Object> stream = Stream.of();

        Optional<Tuple2<Integer, String>> reduce = stream
                .map(o -> (Tuple2<Integer, String>) o)
                .reduce((prevTuple, currentTuple) -> {
                    return new Tuple2<Integer, String>(prevTuple.t1, currentTuple.t2);
                })
                ;

        System.out.println(reduce);
    }

    @Test
    public void reduceWithIdentity_IdentityMeansInitialValue() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        Tuple2<Integer, String> firstValue = stream
                                    .map(o -> (Tuple2<Integer, String>) o)
                                    .reduce(
                                            /*
                                                Identity means initial value.
                                                This initial value is of same type which returned from Map
                                                operation.
                                            */
                                            new Tuple2<>(0, "firstValue")

                                        , (identityTuple, currentTuple) -> {
                                            System.out.println(identityTuple + " " + currentTuple);
                                            return new Tuple2<>(currentTuple.t1, currentTuple.t2);
                                    })
                                    ;

        System.out.println(firstValue);
    }

    @Test
    public void reduceWithIdentity_IdentityMeansInitialValue_With_Empty_Stream() {
        Stream<Object> stream = Stream.of();

        Tuple2<Integer, String> firstValue = stream
                .map(o -> (Tuple2<Integer, String>) o)
                .reduce(/*
                            Identity means initial value.
                            This initial value is of same type which returned from Map
                            operation.
                        */
                        new Tuple2<>(0, "firstValue")

                        , (identityTuple, currentTuple) -> {
                            System.out.println(identityTuple + " " + currentTuple);
                            return new Tuple2<>(currentTuple.t1, currentTuple.t2);
                        })
                ;

        System.out.println(firstValue);
    }

    @Test
    public void reduce_with_identity_accumulator_combiner() {
        Stream<Object> stream = Stream.of(tuple(1, "Adam"),
                tuple(1, "Barn"),
                tuple(2, "Casilo"),
                tuple(2, "Sergio"),
                tuple(3, "Mikel"));

        Tuple2<String, String> reduce = stream
                .map(o -> (Tuple2<Integer, String>) o)
                .reduce(
                        /*
                            In this method you can have identity type of any.
                            and it will be used in reduction method.
                         */
                        new Tuple2<String, String>("String", "firstValue"),

                        (stringStringTuple, integerStringTuple) -> {

                            System.out.println("Inside accumulator :- Tuple from identity " + stringStringTuple + " Tuple from map function" + integerStringTuple);
                            return new Tuple2<String, String>(stringStringTuple.t2, integerStringTuple.t2);

                        }, (stringStringTuple2, stringStringTuple22) -> {

                            System.out.println("Inside accumulator :- Tuple from accumulator " + stringStringTuple2 + " Tuple from accumulator" + stringStringTuple22);
                            return new Tuple2<>(stringStringTuple2.t1, stringStringTuple22.t2);
                        });

        System.out.println(reduce);
    }

    private <T1, T2> Object tuple(T1 t1, T2 t2) {
        return new Tuple2(t1, t2);
    }
}
