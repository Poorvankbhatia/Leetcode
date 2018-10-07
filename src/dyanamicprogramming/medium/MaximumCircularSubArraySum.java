/*

Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.
(Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally,
for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)



Example 1:

Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1


Note:

-30000 <= A[i] <= 30000
1 <= A.length <= 30000

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 07/10/18.
 */
public class MaximumCircularSubArraySum {

    public int maxSubarraySumCircular(int[] A) {

        int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
        for (int a : A) {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-2,-3,-1};
        System.out.println(new MaximumCircularSubArraySum().maxSubarraySumCircular(a));
    }

}

/*


So there are two case.

The first is that the subarray take only a middle part, and we know how to find the max subarray sum.
The second is that the subarray take a part of head array and a part of tail array.
We can transfer this case to the first one.
The maximum result equals to the total sum minus the minimum subarray sum.

So the max subarray circular sum equals to
max(the max subarray sum, the total sum - the min subarray sum)

One** corner case** to pay attention:
If all number are negative,
return the maximum one,
(which equals to the max subarray sum)

 */