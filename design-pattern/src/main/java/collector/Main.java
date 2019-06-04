package collector;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) {
        List<List<String>> nestedList = asList(
                asList("one:one"),
                asList("two:one", "two:two", "two:three"),
                asList("three:one", "three:two", "three:three", "three:four"));

        sequential(nestedList);

        parallel(nestedList);

        collectorToList(nestedList);

        collectorToSet(nestedList);

        collectorsToMap(nestedList);

        collectingAndThen(nestedList);

        groupBy(nestedList);

    }

    private static void groupBy(List<List<String>> nestedList) {

        System.out.println("Group By using collector :- ");

        Map<Object, List<String>> collect = nestedList.stream().flatMap(
                                                strings -> strings.stream()
                                            ).collect(
                                                Collectors.groupingBy(s -> s.length())
                                            );

        collect.entrySet().stream().forEach(objectListEntry -> System.out.println(objectListEntry.getKey() + " " + objectListEntry.getValue()));
    }

    private static void collectingAndThen(List<List<String>> nestedList) {

        class Adder {

            int i;

            public Adder(int i) {
                this.i = i;
            }

            int add(int j) {
                i = i + j;
                return i;
            }

        }


        Adder collect = nestedList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        lists -> lists.stream().map(strings -> strings.size()).collect(
                                () -> new Adder(0),
                                (adder, integer) -> adder.add(integer),
                                (adder, adder2) -> adder.add(adder2.i)
                        )
                )
        );
        System.out.println(collect.i);
    }

    private static void collectorsToMap(List<List<String>> nestedList) {
        Map<Object, String> collect = nestedList.stream().flatMap(strings -> strings.stream()).collect(Collectors.toMap((Function<Object, Object>) o -> {
            System.out.println(" key mapper :- " + o);
            return o;
        }, s -> {
            System.out.println(" Value mapper :- " + s);
            return s;
        }));

        collect.entrySet().stream().forEach(objectStringEntry -> System.out.println(objectStringEntry.getKey() + " " + objectStringEntry.getValue()));

        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");

        try {
            listWithDuplicates.stream().collect(Collectors.toMap(Function.identity(), String::length));
        } catch (Exception e) {
            System.out.println("List with duplicates contains duplicate elements ");
        }

    }

    private static void collectorToSet(List<List<String>> nestedList) {
        Set<String> collect = nestedList.stream().flatMap(strings -> strings.stream()).collect(Collectors.toSet());

        System.out.println("To Set :- ");
        collect.forEach(s -> System.out.println(s));
    }

    private static void collectorToList(List<List<String>> nestedList) {
        List<String> collect = nestedList.stream().flatMap(strings -> strings.stream()).collect(Collectors.toList());

        System.out.println("To List :- ");
        collect.forEach(s -> System.out.println(s));
    }

    private static void parallel(List<List<String>> nestedList) {
        System.out.println("Doing it in parallel");
        /**
         * Above code () -> list, supplying global list will not work in case of parallel streams.
         */
        List collect1 = nestedList.parallelStream().flatMap(strings -> strings.stream()).collect(
                () -> {
                    System.out.println(Thread.currentThread().getId() + " Getting List instances...");
                    return new ArrayList();
                },
                (list12, s) -> {
                    System.out.println(Thread.currentThread().getId() + " Accumulating list :- " + list12 + " " + s);
                    list12.add(s);
                },
                (list1, list2) ->
                        //This method will be never used in sequentail stream.
                        //A careful reading of the streams implementation code in ReduceOps.java
                        // reveals that the combine function is called only when a ReduceTask completes,
                        // and ReduceTask instances are used only when evaluating a pipeline in parallel.
                        // Thus, in the current implementation,
                        // the combiner is never called when evaluating a sequential pipeline.
                {
                    System.out.println(" Combining -> " + list1 + " " + list2);
                    list1.addAll(list2);
                });

        collect1.forEach(o -> System.out.println(o));
    }

    private static void sequential(List<List<String>> nestedList) {
        List list = new ArrayList();

        List collect = nestedList.stream().flatMap(strings -> strings.stream()).collect(
                //This is wrong never return global instance
                //In case of sequential this will work but in case of parallel
                //it will not work.
                () -> list,
                (list12, s) -> list12.add(s),
                (list1, list2) ->
                        //This method will be never used in sequential stream.
                        //A careful reading of the streams implementation code in ReduceOps.java
                        // reveals that the combine function is called only when a ReduceTask completes,
                        // and ReduceTask instances are used only when evaluating a pipeline in parallel.
                        // Thus, in the current implementation,
                        // the combiner is never called when evaluating a sequential pipeline.
                {
                    //list1.addAll(list2);
                    System.out.println(list1 + " " + list2);
                });

        collect.forEach(o -> System.out.println(o));

        nestedList.forEach(strings -> System.out.println(strings));
    }

}
