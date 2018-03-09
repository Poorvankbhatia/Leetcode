/*

In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).


 */
package arrays.Hard;

import java.util.Arrays;

/**
 * Created by poorvank.b on 01/10/17.
 */
public class MaximumSumNonOverlap {

    public int[] maxSumOfThreeSubarrays(int[] nums, int K) {

        int n = nums.length;
        int[] sum = new int[n-K+1];

        int currentSum=0;
        for (int i=0;i<n;i++) {
             currentSum+= nums[i];
            if (i >= K) {
                currentSum -= nums[i-K];
            }
            if (i >= K-1) {
                sum[i-K+1] = currentSum;
            }
        }

        int[] left = new int[sum.length];
        int best = 0;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > sum[best]) {
                best = i;
            }
            left[i] = best;
        }

        int[] right = new int[sum.length];
        best = sum.length - 1;
        for (int i = sum.length - 1; i >= 0; i--) {
            if (sum[i] >= sum[best]){
                best = i;
            }
            right[i] = best;
        }

        System.out.println(Arrays.toString(sum));
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        int[] ans = new int[]{-1, -1, -1};
        for (int j = K; j < sum.length - K; j++) {
            int i = left[j - K];
            int k = right[j + K];
            if (ans[0] == -1 || sum[i] + sum[j] + sum[k] >
                    sum[ans[0]] + sum[ans[1]] + sum[ans[2]]) {

                ans[0] = i;
                ans[1] = j;
                ans[2] = k;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{7,13,20,19,19,2,10,1,1,19};
        System.out.print(Arrays.toString(new MaximumSumNonOverlap().maxSumOfThreeSubarrays(arr,3)));
    }

}

/*

It is natural to consider an array W of each interval's sum, where each interval is the given length K.
To create W, we can either use prefix sums, or manage the sum of the interval as a window slides along the array.

From there, we approach the reduced problem: Given some array W and an integer K, what is the lexicographically smallest tuple of indices
 (i, j, k) with i + K <= j and j + K <= k that maximizes W[i] + W[j] + W[k]?

Algorithm

Suppose we fixed j. We would like to know on the intervals i∈[0,j−K] and k∈[j+K,len(W)−1], where the largest value of W[i]
(and respectively W[k]) occurs first. (Here, first means the smaller index.)

We can solve these problems with dynamic programming. For example,
if we know that i is where the largest value of W[i] occurs first on [0, 5]. then on [0, 6]
the first occurrence of the largest W[i] must be either i or 6. If say, 6 is better, then we set best = 6.

At the end, left[z] will be the first occurrence of the largest value of W[i] on the interval i∈[0,z], and right[z] will be the same
but on the interval i∈[z,len(W)−1]. This means that for some choice j, the candidate answer must be
(left[j-K], j, right[j+K]). We take the candidate that produces the maximum W[i] + W[j] + W[k].

 */