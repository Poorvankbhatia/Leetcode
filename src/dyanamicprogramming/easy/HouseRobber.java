/*

You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you from robbing
each of them is that adjacent houses have security system connected and it will automatically contact the police if
two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the
maximum amount of money you can rob tonight without alerting the police.

 */
package dyanamicprogramming.easy;

import java.util.Arrays;

/**
 * Created by poorvank on 11/09/16.
 */
public class HouseRobber {

    public int rob(int[] nums) {

        if(nums.length==0) {
            return 0;
        } else if(nums.length==1) {
            return nums[0];
        }

        int n =nums.length;
        int[] dp = new int[n+1];
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0],nums[1]);

        for (int i=3;i<=n;i++) {
            dp[i] = Math.max((nums[i-1] + dp[i-2]),dp[i-1]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0};
        System.out.println(new HouseRobber().rob(nums));
    }

}
