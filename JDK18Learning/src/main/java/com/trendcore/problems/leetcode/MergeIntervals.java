package com.trendcore.problems.leetcode;

import java.util.*;
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

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            Interval interval = (Interval) object;

            if (start != interval.start) return false;
            return end == interval.end;
        }

        @Override
        public int hashCode() {
            int result = start;
            result = 31 * result + end;
            return result;
        }
    }


    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();

        mergeIntervals.testCase(new int[][]{
                {1, 3}
        });

        mergeIntervals.testCase(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        });

        mergeIntervals.testCase(new int[][]{
                {1, 4}, {4, 5}
        });

        mergeIntervals.testCase(new int[][]{
                {1, 4}, {0, 4}
        });

        mergeIntervals.testCase(new int[][]{
                {1, 4}, {0, 1}
        });

        mergeIntervals.testCase(new int[][]{
                {1, 4}, {0, 0}
        });

        mergeIntervals.testCase(new int[][]{
                {1, 4}, {0, 2}, {3, 5}
        });

        mergeIntervals.testCase(new int[][]{
                {2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}
        });

        mergeIntervals.testCase(new int[][]{
            {2, 3}, {4, 6}, {5, 7}, {3, 4}
        });
    }

    private void testCase(int[][] ints) {

        List<Interval> collect = Stream.of(ints).map(ints1 -> new Interval(ints1[0], ints1[1])).collect(Collectors.toList());

        System.out.println();
        merge(collect).forEach(interval -> {
            System.out.print("[");
            System.out.print(interval.start + " " + interval.end);
            System.out.print("]");
        });
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {

            Interval interval = intervals.get(i);
            if (i == 0) {
                mergedIntervals.add(interval);
            } else {
                List<Interval> notMergedList = new ArrayList();
                Interval interval1 = new Interval(interval.start,interval.end);
                for (int j = 0; j < mergedIntervals.size(); j++) {
                    Interval interInterval = mergedIntervals.get(j);
                    if (intervalCanbeMerged(interval1, interInterval)) {
                        //create merged interInterval
                        int start = interval1.start < interInterval.start ? interval1.start : interInterval.start;
                        int end = interval1.end > interInterval.end ? interval1.end : interInterval.end;
                        interval1.start = start;
                        interval1.end = end;
                    }else{
                        notMergedList.add(interInterval);
                    }
                }
                notMergedList.add(interval1);
                mergedIntervals = notMergedList;
            }

        }

        return mergedIntervals;
    }

    //1,4     0,0
    //1,3     2,4
    //1,3     3,4
    //1,4     0,0
    //2,5     3,4
    //2,5     5,6
    private boolean intervalCanbeMerged(Interval interval, Interval interval1) {
        if (interval1.start <= interval.start && interval1.end >= interval.start) {
            return true;
        } else if (interval1.start <= interval.end && interval1.end >= interval.end) {
            return true;
        } else if (interval.start <= interval1.start && interval.end >= interval1.start) {
            return true;
        } else if (interval.start <= interval1.end && interval.end >= interval1.end) {
            return true;
        } else {
            return false;
        }
    }

}
