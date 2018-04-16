/*

Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 16/04/18.
 */
public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;

        if(n==0) {
            return 0;
        }

        int[] dp = new int[target+1];

        dp[0] = 1;

        for (int i=1;i<=target;i++) {
            for (int j=0;j<n;j++) {
                if(i>=nums[j]) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }

        return dp[target];
    }

}

/*

 if there are negative numbers in the array, we must add a requirement that each number is only used one time, or either positive
 number or negative number should be used only one time, otherwise there would be infinite possible combinations.
For example, we are given:
{1, -1}, target = 1,
it’s obvious to see as long as we choose n 1s and (n-1) -1s, it always sums up to 1, n can be any value >= 1.

 */