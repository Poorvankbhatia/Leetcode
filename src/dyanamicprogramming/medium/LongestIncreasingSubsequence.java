/*

Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

 */

package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 08/12/16.
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {

        return lisMethod2(nums);

    }

    // n square method
    private int lisMethod1(int[] nums) {
        int n = nums.length;
        if(n==0) {
            return 0;
        }

        int[] count = new int[n];
        for (int i=0;i<n;i++) {
            count[i] = 1;
        }

        int len = 1;

        for (int i=1;i<n;i++) {
            for (int j=0;j<i;j++) {
                if(nums[i]>nums[j] && count[j]+1>count[i]) {
                    count[i] = count[j]+1;
                    if(len<count[i]) {
                        len = count[i];
                    }
                }
            }
        }

        return len;
    }


    //nlog(n) solution
    private int lisMethod2(int[] nums) {

        int n = nums.length;
        if(n==0) {
            return 0;
        }

        int[] dp = new int[n];
        int len = 0;

        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int pos = binarySearch(dp,len,nums[i]);
            if (nums[i] < dp[pos]) dp[pos] = nums[i];
            if (pos > len) {
                len = pos;
                dp[len] = nums[i];
            }
        }

        return len+1;

    }


    private int binarySearch(int[] dp,int len,int val) {
        int left = 0;
        int right = len;
        while(left+1 < right) {
            int mid = left + (right-left)/2;
            if (dp[mid] == val) {
                return mid;
            } else {
                if (dp[mid] < val) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        if (dp[right] < val) return len+1;
        else if (dp[left] >= val) return left;
        else return right;

    }


    public static void main(String[] args) {

        int[] arr = new int[]{2, 5, 6, 7, 101, 18};
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(arr));

    }

}


/*

nlogn solution

1, traverse from 0 to len-1, the DP array keep the longest sequence.
2, if the val is bigger than largest in the dp array, add it to the end;
3, if it is among the sequence, return the pos that bigger than pres, update the array with this
position if val is smaller than dp[pos];
This is to keep the sequence element with the smallest number

 */