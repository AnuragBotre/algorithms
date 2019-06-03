package collector;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) {
        List<List<String>> nestedList = asList(
                asList("one:one"),
                asList("two:one", "two:two", "two:three"),
                asList("three:one", "three:two", "three:three", "three:four"));

        List list = new ArrayList();

        List collect = nestedList.stream().flatMap(strings -> strings.stream()).collect(
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

}
