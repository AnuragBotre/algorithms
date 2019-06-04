package collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
