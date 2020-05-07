/*

Given a non-decreasing array of positive integers nums and an integer K,
find out if this array can be divided into one or more disjoint increasing subsequences of length at least K.



Example 1:

Input: nums = [1,2,2,3,3,4,4], K = 3
Output: true
Explanation:
The array can be divided into the two subsequences [1,2,3,4] and [2,3,4] with lengths at least 3 each.
Example 2:

Input: nums = [5,6,6,7,8], K = 3
Output: false
Explanation:
There is no way to divide the array using the conditions required.


Note:

1 <= nums.length <= 10^5
1 <= K <= nums.length
1 <= nums[i] <= 10^5

 */
package arrays.hard;

public class DivideArrayIntoIncreasingSubSequence {

    public boolean canDivideIntoSubsequences(int[] A, int K) {
        int cur = 1, groups = 1, n = A.length;
        for (int i = 1; i < n; ++i) {
            cur = A[i - 1] < A[i] ?  1 : cur + 1;
            groups = Math.max(groups, cur);
        }
        return n >= K * groups;
    }

}

/*

For example 2: A = [5,6,6,7,8]
we have two 6, so we have to split A into at least two subsequence.
We want K = 3 numbers in each subsequence, so we need at least K * 2 = 6 numbers.
But we have only A.length = 5 numbers.


So the idea is that, find the maximum quantity of same numbers in A.
As A is sorted already, we can do this in one pass and O(1) space.
cur is the current length of same number until A[i].
If A[i - 1] < A[i], we reset cur = 1. Otherwise increment cur = cur + 1.

If n < K * groups, it's impossible to satisfy the condition, we return false.
Otherwise, we have enough numbers in hand and we can easily meet the requirement:

Time O(N) for one pass, and you can return false earlier.
Space O(1) for no extra space.

 */