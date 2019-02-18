package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/merge-intervals/
 * <p>
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {

    /**
     * Definition for an interval.
     */
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();

        mergeIntervals.testCase(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        });


        mergeIntervals.merge(null);
    }

    private void testCase(int[][] ints) {

        List<Interval> collect = Stream.of(ints).map(ints1 -> new Interval(ints1[0], ints1[1])).collect(Collectors.toList());

        System.out.println();
        merge(collect).forEach(interval -> System.out.println(interval.start + " " + interval.end));
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new ArrayList<>();

        Map map = new HashMap();

        for (int i = 0; i < intervals.size(); i++) {
            //TODO : Need to check options
            //
        }
        return mergedIntervals;
    }

}
