package com.trendcore.problems.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 * <p>
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

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
        public String toString() {
            return " " + start + " " + end;
        }
    }


    public static void main(String[] args) {
        InsertInterval insertInterval = new InsertInterval();
        insertInterval.testCase(new int[][]{
                {1, 3}, {6, 9}
        }, 2, 5);

        insertInterval.testCase(new int[][]{
                //[1,2],[3,5],[6,7],[8,10],[12,16]
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        }, 4, 8);

        insertInterval.testCase(new int[][]{
                //[1,2],[3,5],[6,7],[8,10],[12,16]
                {1, 5}
        }, 2, 7);

        insertInterval.testCase(new int[][]{
                //[1,2],[3,5],[6,7],[8,10],[12,16]
                {1, 5}
        }, 0, 0);
    }

    private void testCase(int[][] ints, int start, int end) {

        List<InsertInterval.Interval> collect = Stream.of(ints).map(ints1 -> new InsertInterval.Interval(ints1[0], ints1[1])).collect(Collectors.toList());

        System.out.println();
        insert(collect, new Interval(start, end)).forEach(interval -> {
            System.out.print("[");
            System.out.print(interval.start + " " + interval.end);
            System.out.print("]");
        });
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> mergedInterval = new ArrayList<>();

        if (intervals.size() == 0) {
            mergedInterval.add(newInterval);
            return mergedInterval;
        }

        //step -1
        //loop through list and pick a position where insert can be made
        mergedInterval = step1(0, intervals.size(), intervals, newInterval, mergedInterval);

        // while looping also keep merging
        return mergedInterval;
    }

    private List<Interval> step1(int start, int end, List<Interval> intervals, Interval newInterval, List<Interval> mergedInterval) {
        boolean newIntervalMerged = false;

        List<Interval> finalMergedInterval = mergedInterval;
        Consumer<Interval> zeroThPosition = o -> {
            finalMergedInterval.add(o);
        };

        int positionOfBreak = 0;
        for (int i = start; i < end; i++) {
            Interval interval = intervals.get(i);
            if (i == 0) {
                //zeroThPosition.accept(interval);
                mergedInterval.add(interval);
            } else {
                if (newInterval.start < interval.start) {
                    positionOfBreak = i;
                    break;
                }
                mergedInterval = getMergedIntervalForStep1(mergedInterval, interval,false);
            }
        }

        mergedInterval = getMergedIntervalForStep1(mergedInterval, newInterval , true);

        for (int i = positionOfBreak; i < end; i++) {
            Interval interval = intervals.get(i);
            if (i == 0) {
                zeroThPosition.accept(interval);
            } else {
                mergedInterval = getMergedIntervalForStep1(mergedInterval, interval,false);
            }
        }

        return mergedInterval;
    }

    private List<Interval> getMergedIntervalForStep1(List<Interval> mergedInterval, Interval interval, boolean addAtFirst) {
        Interval temp = new Interval(interval.start, interval.end);
        List<Interval> nonMergedList = new ArrayList<>();

        int pos = 0;
        for (int j = 0, cnt = 0; j < mergedInterval.size(); j++) {
            Interval intermediateMergedInterval = mergedInterval.get(j);
            if (intervalsCanBeMerged(temp, intermediateMergedInterval)) {

                int start = temp.start < intermediateMergedInterval.start ? temp.start : intermediateMergedInterval.start;
                int end = temp.end > intermediateMergedInterval.end ? temp.end : intermediateMergedInterval.end;
                temp.start = start;
                temp.end = end;
            } else {

                if (temp.start > intermediateMergedInterval.start) {
                    pos = cnt;
                }

                nonMergedList.add(intermediateMergedInterval);
                cnt++;
            }
        }

        if(addAtFirst) {
            nonMergedList.add(0,temp);
        }else{
            nonMergedList.add(temp);
        }
        mergedInterval = nonMergedList;
        return mergedInterval;
    }

    public List<Interval> approach1(List<Interval> intervals, Interval newInterval, List<Interval> mergedInterval) {
        boolean newIntervalMerged = false;

        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (i == 0) {
                mergedInterval.add(interval);
            } else {

                if (newInterval.start < interval.start && !newIntervalMerged) {
                    interval = newInterval;
                    i--;
                    newIntervalMerged = true;
                }

                mergedInterval = getMergedInterval(mergedInterval, interval);
            }
        }

        if (!newIntervalMerged) {
            mergedInterval = getMergedInterval(mergedInterval, newInterval);
        }

        return mergedInterval;
    }

    public List<Interval> getMergedInterval(List<Interval> mergedInterval, Interval interval) {
        Interval temp = new Interval(interval.start, interval.end);
        List<Interval> nonMergedList = new ArrayList<>();

        int pos = 0;
        for (int j = 0, cnt = 0; j < mergedInterval.size(); j++) {
            Interval intermediateMergedInterval = mergedInterval.get(j);
            if (intervalsCanBeMerged(temp, intermediateMergedInterval)) {

                int start = temp.start < intermediateMergedInterval.start ? temp.start : intermediateMergedInterval.start;
                int end = temp.end > intermediateMergedInterval.end ? temp.end : intermediateMergedInterval.end;
                temp.start = start;
                temp.end = end;
            } else {

                if (temp.start > intermediateMergedInterval.start) {
                    pos = cnt;
                }

                nonMergedList.add(intermediateMergedInterval);
                cnt++;
            }
        }

        boolean initialized = false;
        for (int j = 0; j < nonMergedList.size(); j++) {
            if (nonMergedList.get(j).start > temp.start) {
                pos = j;
                initialized = true;
                break;
            }
        }
        if (initialized) {
            nonMergedList.add(pos, temp);
        } else {
            nonMergedList.add(temp);
        }
        mergedInterval = nonMergedList;
        return mergedInterval;
    }

    private boolean intervalsCanBeMerged(Interval first, Interval second) {
        if (first.start <= second.start && first.end >= second.start) {
            return true;
        } else if (first.start <= second.end && first.end >= second.end) {
            return true;
        } else if (second.start <= first.start && second.end >= first.start) {
            return true;
        } else if (second.start <= first.end && second.end >= first.end) {
            return true;
        }
        return false;
    }
}
