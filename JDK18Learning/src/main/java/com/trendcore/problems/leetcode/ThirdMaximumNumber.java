package com.trendcore.problems.leetcode;

/**
 * https://leetcode.com/problems/third-maximum-number/description/
 * <p>
 * 414. Third Maximum Number
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
 * <p>
 * Example 1:
 * Input: [3, 2, 1]
 * <p>
 * Output: 1
 * <p>
 * Explanation: The third maximum is 1.
 * Example 2:
 * Input: [1, 2]
 * <p>
 * Output: 2
 * <p>
 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 * Example 3:
 * Input: [2, 2, 3, 1]
 * <p>
 * Output: 1
 * <p>
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {

    public static void main(String[] args) {
        ThirdMaximumNumber t = new ThirdMaximumNumber();
        t.testCase();
    }

    private void testCase() {
        System.out.println(thirdMax(new int[]{3, 2, 1}));
        System.out.println(thirdMax(new int[]{1, 2}));
        System.out.println(thirdMax(new int[]{2, 2, 3, 1}));
        System.out.println(thirdMax(new int[]{2, 5, 7}));
        System.out.println(thirdMax(new int[]{2, 5}));
        System.out.println(thirdMax(new int[]{-2147483648,-2147483648,-2147483648,-2147483648,1,1,1}));
    }

    public int thirdMax(int[] nums) {

        int firstMax = 0;
        int secondMax = 0;
        int thirdMax = 0;

        boolean secondMaxInitialized = false;
        boolean thirdMaxInitialized = false;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                firstMax = nums[i];
            } else {

                if (firstMax < nums[i]) {
                    if(secondMaxInitialized) {
                        thirdMax = secondMax;
                        thirdMaxInitialized = true;
                    }
                    secondMax = firstMax;
                    secondMaxInitialized = true;

                    firstMax = nums[i];
                }

                if (!secondMaxInitialized && nums[i] < firstMax) {
                    thirdMax = secondMax;
                    secondMax = nums[i];
                    secondMaxInitialized = true;
                } else if (secondMaxInitialized && nums[i] < firstMax && secondMax < nums[i]) {
                    thirdMax = secondMax;
                    thirdMaxInitialized = true;
                    secondMax = nums[i];
                    secondMaxInitialized = true;
                }

                if (!thirdMaxInitialized && secondMaxInitialized && nums[i] < secondMax) {
                    thirdMax = nums[i];
                    thirdMaxInitialized = true;
                } else if (thirdMaxInitialized && nums[i] < secondMax && thirdMax < nums[i]) {
                    thirdMax = nums[i];
                    thirdMaxInitialized = true;
                }
            }
        }

        return thirdMaxInitialized ? thirdMax : firstMax;
    }

    private void shifArr(int[] kArr, int num) {
        for (int i = kArr.length - 1; i > num; i--) {
            kArr[i] = kArr[i - 1];
        }
    }

    private void fillAllElementsWith(int i, int[] kArr) {
        for (int j = 0; j < kArr.length; j++) {
            kArr[j] = i;
        }
    }
}
