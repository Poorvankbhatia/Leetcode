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

        if(null == nums || nums.length==0) {
            return 0;
        }

        int lo = getMax(nums);
        int hi = getSum(nums);

        while (lo<hi) {
            int mid = lo + (hi-lo)/2;
            int currentPartitions  = getPartitionCount(nums,mid);
            if(currentPartitions<=m) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }

        return lo;

    }

    private int getPartitionCount(int[] nums,int val) {
        int total = 0,count = 1;
        for (Integer element : nums) {
            total += element;
            if(total>val) {
                total = element;
                count++;
            }
        }
        return count;
    }


    private int getMax(int[] nums) {
        int max=Integer.MIN_VALUE;
        for (Integer element: nums) {
            if(max<element) {
                max = element;
            }
        }

        return max;
    }


    private int getSum(int[] nums) {
        int sum = 0;
        for (Integer element : nums) {
            sum += element;
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{7,2,5,10,8};
        int m = 2;
        System.out.println(new SplitArraySum().splitArray(arr,m));
    }

}
