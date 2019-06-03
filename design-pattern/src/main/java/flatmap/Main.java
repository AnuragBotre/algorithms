package flatmap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) {
        // Creating a List of Strings
        List<String> list = asList("5.6", "7.4", "4",
                "1", "2.3");

        //list.stream().flatMap(s -> Stream.of(s)).forEach(s -> System.out.println(s));
        //Short hand of the above method.
        list.stream().flatMap(s -> Stream.of(s)).forEach(System.out::println);


        //optional for stream
        //The flatMap() method first flattens the input Stream of Streams to a
        // Stream of Strings (for more about flattening, see the article).
        // Thereafter it works similarly to the map() method.
        List<List<String>> nestedList = asList(
                asList("one:one"),
                asList("two:one", "two:two", "two:three"),
                asList("three:one", "three:two", "three:three", "three:four"));

        nestedList.forEach(strings -> System.out.println(strings));

        //flatten list with imperative style
        List flattenListWithImperativeStyle = flattenList(nestedList);

        flattenListWithImperativeStyle.forEach(System.out::println);

        System.out.println("Flatten List using flat map.");

        //The list before flattening :
        //[ [one:one], [two:one,two:two,two:three], [three:one,three:two,three:three,three:four] ]
        //The list has 2 levels and consists of 3 small lists. After Flattening, it gets transformed into “one level” structure as shown :
        //[one:one , two:one , two:two , two:three , three:one , three:two , three:three , three:four]

        nestedList.stream().flatMap(strings -> strings.stream()).forEach(System.out::println);
    }

    private static List flattenList(List<List<String>> nestedList) {

        List finalList = new ArrayList();

        for (List<String> list : nestedList) {
            finalList.addAll(list);
        }

        return finalList;
    }

}
