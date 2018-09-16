package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/search-insert-position/description/
 * <p>
 * 35. Search Insert Position
 * <p>
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * <p>
 * You may assume no duplicates in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 * <p>
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 * <p>
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 * <p>
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class SearchInsertPosition {

    private static SearchInsertPosition s;

    public static void main(String[] args) {
        SearchInsertPosition s = new SearchInsertPosition();
        s.testCase(new int[]{1, 3, 5, 6}, 5);
        s.testCase(new int[]{1, 3, 5, 6}, 2);
        s.testCase(new int[]{1, 3, 5, 6}, 1);
        s.testCase(new int[]{1, 3, 5, 6}, 0);
        s.testCase(new int[]{1, 3, 5, 6}, 6);
        s.testCase(new int[]{1, 3, 5, 6}, 7);
    }

    private void testCase(int[] nums, int target) {
        System.out.println(searchInsert(nums, target));
    }

    public int searchInsert(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;

        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        //System.out.println(mid + " " + nums[mid] + " Low :- " + low + " High :-" + high);

        return low;
    }

}
