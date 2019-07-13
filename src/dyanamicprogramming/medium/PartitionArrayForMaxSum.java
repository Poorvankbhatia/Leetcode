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

    public int maxSumAfterPartitioning(int[] A, int K) {
        int[] dp = new int[A.length];
        dp[0] = A[0];

        for(int i=1;i<A.length;i++) {
            int maxSum = A[i]+dp[i-1], maxVal = A[i];
            for(int j=i-1;j>=0 && j>i-K;j--) {
                maxVal = Math.max(maxVal, A[j]);
                if(j==0) {
                    maxSum = Math.max(maxSum, maxVal*(i-j+1));
                } else {
                    maxSum = Math.max(maxVal*(i-j+1)+dp[j-1], maxSum);
                }

            }
            dp[i] = maxSum;
        }

        return dp[A.length-1];
    }

}

/*

dp[i] record the maximum sum we can get considering A[0] ~ A[i]
To get dp[i], we will try to change k last numbers separately to the maximum of them,
where k = 1 to k = K.

Time O(NK), Space O(N)



 */