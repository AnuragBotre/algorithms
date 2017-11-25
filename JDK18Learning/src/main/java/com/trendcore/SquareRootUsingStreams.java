package com.trendcore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Anurag
 */
public class SquareRootUsingStreams {

    public static void main(String[] args) {
        /*List<Double> sqrtOfFirst100Primes = new ArrayList<>();

        int index = 1;

        while (sqrtOfFirst100Primes.size() < 100) {
            if (isPrime(index)) {
                sqrtOfFirst100Primes.add(Math.sqrt(index));
            }
            index++;
        }*/

        List<Double> sqrtOfFirst100Primes = Stream
                                                .iterate(1,e -> e + 1)
                                                .filter(SquareRootUsingStreams::isPrime)
                                                .map(Math::sqrt)
                                                .limit(100)
                                                .collect(toList());
        //filter , map , limit are called intermediate functions
        //collect is called the terminal function which will triiger all the computation

        System.out.println(String.format("Computed %d values, first is %g, last is %g",
                        sqrtOfFirst100Primes.size(),
                        sqrtOfFirst100Primes.get(0),
                        sqrtOfFirst100Primes.get(sqrtOfFirst100Primes.size()-1)));
    }

    public static boolean isPrime(int number) {
        /*boolean divisble = false;

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                divisble = true;
                break;
            }
        }

        return number > 1 && !divisble;*/

        return number > 1 && IntStream.range(2,number).noneMatch(value -> number % value == 0);
    }

}
