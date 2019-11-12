/*
Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).



Example 1:

Input: [3,6,9,12]
Output: 4
Explanation:
The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: [9,4,7,2,10]
Output: 3
Explanation:
The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: [20,1,15,3,10,5,8]
Output: 4
Explanation:
The longest arithmetic subsequence is [20,15,10,5].


Note:

2 <= A.length <= 2000
0 <= A[i] <= 10000
 */
package dyanamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSequence {
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int n = arr.length;
        int[] dp = new int[n];
        dp[n-1]=1;
        map.put(arr[n-1],n-1);
        int max = 1;
        for(int i=n-2;i>=0;i--) {
            int v = difference+arr[i];
            if(map.containsKey(v)) {
                dp[i]=dp[map.get(v)]+1;
                max = Math.max(dp[i],max);
            } else {
                dp[i]=1;
            }
            map.put(arr[i],i);
        }
        return max;
    }
}

/*

Each cell is a hashmap with difference as key and length as value.
Without using extra space:

public int longestSubsequence(int[] arr, int difference) {
		HashMap<Integer, Integer> dp = new HashMap<>();
		int longest = 0;
		for(int i=0; i<arr.length; i++) {
			dp.put(arr[i], dp.getOrDefault(arr[i] - difference, 0) + 1);
			longest = Math.max(longest, dp.get(arr[i]));
		}
		return longest;
	}

 */