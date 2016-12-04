/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

 */

package dyanamicprogramming.easy;


/**
 * Created by poorvank on 11/09/16.
 */
public class RangeSumQuery {

    private int[] sum;

    public RangeSumQuery(int[] nums) {

        sum = new int[nums.length];
        if (nums.length!=0) {
            sum[0] = nums[0];
            for (int i=1;i<nums.length;i++) {
                sum[i] += sum[i-1] + nums[i];
            }
        }

    }

    public int sumRange(int i, int j) {
        if(i>=sum.length || j>=sum.length) {
            return 0;
        }
        return i==0?sum[j]:(sum[j]-sum[i-1]);
    }

    public static void main(String[] args) {

        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(nums);
        System.out.println(rangeSumQuery.sumRange(2,5));

    }


}
