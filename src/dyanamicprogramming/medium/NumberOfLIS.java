/*
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.

 */
package dyanamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by poorvank.b on 30/09/17.
 */
public class NumberOfLIS {

    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;
        if(n==0 || n==1) {
            return n;
        }

        int[] length = new int[n]; //the length of longest subsequence ending with nums[k];
        int[] count = new int[n]; //total number of longest subsequence ending with nums[i];

        Arrays.fill(length,1);
        Arrays.fill(count,1);
        int maxLength=Integer.MIN_VALUE;

        for (int i=1;i<n;i++) {
            for (int j=0;j<i;j++) {
                if(nums[i]>nums[j]) {
                    if(length[j]+1>length[i]) {
                        length[i] = length[j]+1;
                        count[i]=count[j];
                    } else if(length[j]+1==length[i]) {
                        count[i]+=count[j];
                    }
                }
            }
            maxLength = Math.max(maxLength,length[i]);
        }

        int ans=0;
        for (int i=0;i<n;i++) {
            if(length[i]==maxLength) {
                ans+=count[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,2,2};
        System.out.print(new NumberOfLIS().findNumberOfLIS(arr));
    }

}
