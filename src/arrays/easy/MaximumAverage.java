/*

Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value.
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].

 */

package arrays.easy;

/**
 * Created by poorvank on 31/07/17.
 */
public class MaximumAverage {

    public double findMaxAverage(int[] nums, int k) {

        if(nums.length==0 || k==0) {
            return 0;
        }

        int n = nums.length;

        int sum = 0;
        for (int i=0;i<k;i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i=k;i<n;i++) {

            sum = sum-nums[i-k]+nums[i];
            if(sum>maxSum) {
                maxSum = sum;
            }
        }


        return (double) maxSum/k;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,12,-5,-6,50,3};
        System.out.print(new MaximumAverage().findMaxAverage(arr,4));
    }

}
