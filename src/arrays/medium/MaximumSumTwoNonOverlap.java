/*
Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays,
which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)

Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:

0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
0 <= j < j + M - 1 < i < i + L - 1 < A.length.


Example 1:

Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
Example 2:

Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
Example 3:

Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
 */
package arrays.medium;

public class MaximumSumTwoNonOverlap {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] prefixSum = new int[A.length + 1];
        for (int i = 0; i < A.length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }
        return Math.max(maxSum(prefixSum, L, M), maxSum(prefixSum, M, L));
    }
    private int maxSum(int[] p, int L, int M) {
        int ans = 0,maxL=0;
        for (int i = L + M;i < p.length; ++i) {
            maxL = Math.max(maxL, p[i - M] - p[i - M - L]);
            ans = Math.max(ans, maxL + p[i] - p[i - M]);
        }
        return ans;
    }
}
/*

Scan the prefix sum array from index L + M, which is the first possible position;
update the max value of the L-length subarray; then update max value of the sum of the both;
we need to swap L and M to scan twice, since either subarray can occur before the other.
In private method, prefix sum difference p[i - M] - p[i - M - L] is L-length subarray from index
i - M - L to i - M - 1, and p[i] - p[i - M] is M-length subarray from index i - M to i - 1.

 */