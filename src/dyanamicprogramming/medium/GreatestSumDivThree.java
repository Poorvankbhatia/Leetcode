/*

Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.



Example 1:

Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
Example 2:

Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.
Example 3:

Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).


Constraints:

1 <= nums.length <= 4 * 10^4
1 <= nums[i] <= 10^4

 */
package dyanamicprogramming.medium;

public class GreatestSumDivThree {

    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];
        for(int num:nums){
            int[] next = dp.clone();
            for(int sum:dp){
                int nsum = sum+num;
                int index = nsum%3;
                next[index] = Math.max(nsum, next[index]);
            }
            dp = next;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new GreatestSumDivThree().maxSumDivThree(new int[]{2,6,2,2,7}));
    }

}

/*


Intuition:
        1.The last maximum possible sum that it is divisible by three could only depends
        on 3 kinds of "subroutines/subproblems":
            1. previous maximum possible sum that it is divisible by three
               preSum % 3 == 0       (example: preSum=12 if lastNum=3)
            2. preSum % 3 == 1       (example: preSum=13 if lastNum=2)
            3. preSum % 3 == 2       (example: preSum=14 if lastNum=1)
        2. This recusion + "subroutines" pattern hints Dynamic Programming

    dp state:
        dp[i] = max sum such that the reminder == i when sum / 3

without clone:

public int maxSumDivThree(int[] A) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : A) {
            int[] dp2 = new int[3];
            for (int i = 0; i < 3; ++i)
                dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            dp = dp2;
        }
        return dp[0];
    }

 */