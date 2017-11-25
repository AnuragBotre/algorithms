package com.trendcore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Anurag
 */
public class ComparatorExample {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();

        list.add(new PersonWithComparable("Smith", 64));
        list.add(new PersonWithComparable("Kelly", 50));
        list.add(new PersonWithComparable("Cate", 20));
        list.add(new PersonWithComparable("Bob", 20));

        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person personOne, Person personTwo) {
                return personOne.getAge() < personTwo.getAge() ? -1 :
                        personOne.getAge() == personTwo.getAge() ? 0 : 1;
            }
        });

        System.out.println(list);

        sortArrayUsingStream(list, new Comparator<Person>() {
            @Override
            public int compare(Person personOne, Person personTwo) {
                return personOne.getAge() < personTwo.getAge() ? -1 :
                        personOne.getAge() == personTwo.getAge() ? 0 : 1;
            }
        });
        System.out.println("Using Streams");

        sortArrayUsingStream(list, Comparator.comparing(Person::getAge).thenComparing(Person::getName));

        System.out.println("Sorting Streams");
        List<Person> sortedPersonList = getSortedList(list, Comparator.comparing(Person::getAge).thenComparing(Person::getName)).collect(toList());

        System.out.println(sortedPersonList);
    }

    private static <T> Stream<T> getSortedList(List<T> list, Comparator<T> comparator) {
        return list.stream().sorted(comparator);
    }

    private static <T> void sortArrayUsingStream(List<T> list, Comparator<T> comparator) {
        list.stream().sorted(comparator).forEach(System.out::println);
    }

}
