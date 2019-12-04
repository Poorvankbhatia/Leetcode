/*

Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.
After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.



Example 1:

Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
Explanation: A becomes [15,15,15,9,10,10,10]


Note:

1 <= K <= A.length <= 500
0 <= A[i] <= 10^6

 */
package dyanamicprogramming.medium;

public class PartitionArrayForMaxSum {

    int[] dp;
    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        dp = new int[n];// dp[i] = maximum sum from i to end.
        return util(A,0,K);
    }

    private int util(int[] A,int start,int K) {
        if(start>=A.length) {
            return 0;
        }
        if(start==A.length-1) {
            return A[A.length-1];
        }
        if(dp[start]!=0) {
            return dp[start];
        }
        int sum=0;
        int max = A[start];
        int c=1;
        for(int i=start;i<start+K && i<A.length;i++) {
            max = Math.max(A[i],max);
            sum=Math.max(sum,(max*(c++))+util(A,i+1,K));
        }
        dp[start]=sum;
        return sum;
    }

}