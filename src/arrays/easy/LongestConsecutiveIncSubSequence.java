/*
Given an unsorted array of integers, find the length of longest continuous increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 */
package arrays.easy;

/**
 * Created by poorvank.b on 10/09/17.
 */
public class LongestConsecutiveIncSubSequence {

    public int findLengthOfLCIS(int[] nums) {

        int length = 1;
        int maxLength = 1;

        if(nums==null || nums.length==0) {
            return 0;
        }

        for (int i=1;i<nums.length;i++) {
            if(nums[i]>nums[i-1]) {
                length++;
                maxLength = Math.max(maxLength,length);
            } else {
                length = 1;
            }
        }

        return maxLength;

    }

}
