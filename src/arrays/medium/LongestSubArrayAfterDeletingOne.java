/*
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array.

Return 0 if there is no such subarray.



Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
Example 4:

Input: nums = [1,1,0,0,1,1,1,0,1]
Output: 4
Example 5:

Input: nums = [0,0,0]
Output: 0


Constraints:

1 <= nums.length <= 10^5
nums[i] is either 0 or 1.
 */
package arrays.medium;

public class LongestSubArrayAfterDeletingOne {

    public int longestSubarray(int[] nums) {
        int zeroCount=0;
        int i=0,j=0;
        int ans = 0;
        while (j< nums.length) {
            if(nums[i]==0) {
                zeroCount++;
            }
            if (zeroCount>1) {
                while (nums[i++]!=0) {
                    zeroCount--;
                }
            }
            ans = Math.max(ans, j-i);
            j++;
        }
        return ans;
    }

}

/*
DP Sol:
One way to deal this problem is dynamic programming. Let dp[i][0] be the length of longest subarray of ones,
such that it ends at point i. Let dp[i][1] be the length longest subarray, which has one zero and ends at point i.
Let us traverse through our nums and update our dp table, we have two options:

dp[0][0] = 1 if nums[0] = 1 and 0 in opposite case.
If nums[i] = 1, than to update dp[i][0] we just need to look into dp[i-1][0] and add one, the same true is for dp[i][1].
If nums[i] = 0, than dp[i][0] = 0, we need to interrupt our sequence without zeroes. dp[i][1] = dp[i-1][0],
because we we added 0 and number of 1 is still i-1.
Let us go through example [1,0,1,1,0,0,1,1,0,1,1,1]:
then we have the following dp table:

1	0	1	2	0	0	1	2	0	1	2	3
0	1	2	3	2	0	1	2	2	3	4	5
Complexity: time and space is O(n), because we iterate over our table dp once. Space can be reduced to O(1) because
we only look into the previous column of our table.

Extension and follow-up. Note, that this solution can be easily extended for the case, when we need to delete k elements
instead of one, with time complexity O(nk) and space complexity O(k).

Other solutions. This problem can be solved with sliding window technique as well, or we can evaluate sizes of groups of
0's and 1's and then choose two group of 1's, such that it has group of 0's with only one element between them.
 */