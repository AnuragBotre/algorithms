package com.trendcore.problems;

import java.util.Scanner;

/**
 * Created by Anurag
 */
public class ExcitedScooby {

    /*
        https://www.hackerearth.com/challenge/hiring/amazon-hiring-challenge-2/algorithm/excited-scooby-4/

        Scooby and all of his friends have gathered for a party. There are N friends present.
        Scooby is really happy to see all of his friends in one place and is excited to greet them.

        All N friends are seated in a circle, and are numbered from 0 to N-1.
        Scooby is initially sitting beside the Ath friend.
        After greeting one friend, he goes clockwise to the Bth next friend, sits next to him and greets him.
        He repeats this till he returns to the Ath friend.

        In his excitement, it is possible that Scooby misses out on greeting some friends.
        Your job is to find the number of friends (including A) that Scooby will have greeted before reaching back to A.

        Input:

        The first line contains T, the number of test cases.

        Each of the next T lines contain three space-separated integers, the values of A, B and N for that test case.

        Output:

        For each test case, output the number of friends that Scooby will have greeted before reaching back to A.

        Constraints:

        1 ≤ T ≤ 100000

        1 ≤ N ≤ 10^15

        0 ≤ B ≤ 10^15

        0 ≤ A < N

        SAMPLE INPUT            SAMPLE OUTPUT
        1                               5
        1 1 5
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0)
        {
            long A = s.nextLong();
            long B=s.nextLong();
            long N=s.nextLong();
            long ans=LCM(B,N);
            if(B!=0)
                ans/=B;
            else
                ans=1;
            System.out.println(ans);
        }
    }

    static long LCM(long B,long N)
    {
        return B/GCD(B,N)*N;
    }

    static long GCD(long B,long N)
    {
        if(N==0)return B;
        return GCD(N,B%N);

    }

}
