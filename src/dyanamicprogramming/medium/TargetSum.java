/*

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 28/01/17.
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int S) {

        int sum=0;

        for (Integer num : nums) {
            sum += num;
        }

        if(S>sum || ((sum+S)%2==1)) {
            return 0;
        }

        return countSubsetsWithSum(nums,(sum+S)/2);

    }

    private int countSubsetsWithSum(int[] nums,int sum) {

        int[] dp = new int[sum+1];

        dp[0] = 1;
        for (Integer num : nums) {

            for (int i=sum;i>=num;i--) {
                dp[i] += dp[i-num];
            }

        }

        return dp[sum];

    }

    public static void main(String[] args) {
        int[] nums = new int[] {1};
        int sum = 1;
        System.out.print(new TargetSum().findTargetSumWays(nums,sum));
    }

}

/*

The original problem statement is equivalent to:
Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target

Let P be the positive subset and N be the negative subset
For example:
Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

Then let's see how this can be converted to a subset sum problem:

                  sum(P) - sum(N) = target
sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                       2 * sum(P) = target + sum(nums)
So the original problem has been converted to a subset sum problem as follows:
Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2

Note that the above formula has proved that target + sum(nums) must be even

 */