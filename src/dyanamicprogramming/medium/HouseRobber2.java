/*

After robbing those houses on that street, the thief has found himself
 a new place for his thievery so that he will not get too much attention. This time, all houses at this
  place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile,
  the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum
amount of money you can rob tonight without alerting the police.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 03/01/17.
 */
public class HouseRobber2 {

    public int rob(int[] nums) {

        int n = nums.length;

        if(n==0) {
            return 0;
        } else if(n==1) {
            return nums[0];
        } else if(n==2) {
            return Math.max(nums[0],nums[1]);
        }

        int[] includeFirst = new int[n];

        includeFirst[0] = nums[0];
        includeFirst[1] = Math.max(nums[0],nums[1]);

        for (int i=2;i<n-1;i++) {
            includeFirst[i] = Math.max(nums[i]+includeFirst[i-2],includeFirst[i-1]);
        }


        int[] includeLast = new int[n];

        includeLast[0] = 0;
        includeLast[1] = nums[1];

        for (int i=2;i<n;i++) {
            includeLast[i] = Math.max(nums[i]+includeLast[i-2],includeLast[i-1]);
        }

        return Math.max(includeFirst[n-2],includeLast[n-1]);

    }


}
