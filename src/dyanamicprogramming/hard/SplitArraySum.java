/*

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous
subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 19/01/17.
 */
public class SplitArraySum {

    public int splitArray(int[] nums, int m) {
        long small = Long.MIN_VALUE;
        long high = 0;
        for (int x : nums) {
            small = Math.max(small, x);
            high += x;
        }
        while (small < high) {
            long mid = (small) + (high - small) / 2;
            int c = countSplits(nums,mid);
            if (c>m){ // If split count is higher it means we need to increase the mid value to reduce it.
                small=mid+1;
            } else{
                high = mid;
            }
        }
        return small>Integer.MAX_VALUE?Integer.MAX_VALUE:(int) small;
    }


    private int countSplits(int[] nums, long val) {
        int split = 0;
        long sum = 0;
        int i=0;
        while (i<nums.length) {
            sum += nums[i];
            if (sum > val) {
                sum = 0;
                split++;
                continue;
            }
            i++;
        }
        return split+1;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,4,4};
        int m = 3;
        System.out.println(new SplitArraySum().splitArray(arr,m));
    }

}
