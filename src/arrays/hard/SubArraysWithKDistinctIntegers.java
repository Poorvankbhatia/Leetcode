/*
Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of
different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.



Example 1:

Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
Example 2:

Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Note:

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length

 */
package arrays.hard;

import java.util.HashMap;
import java.util.Map;

public class SubArraysWithKDistinctIntegers {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums,k)-atMost(nums,k-1);
    }

    private int atMost(int[] nums, int k) {
        int start = 0;
        int end = 0;
        Map<Integer,Integer> freq = new HashMap<>();
        int distinct = 0;
        int ans = 0;
        while(end<nums.length) {
            if(freq.getOrDefault(nums[end],0)==0) {
                distinct++;
            }
            freq.put(nums[end],freq.getOrDefault(nums[end],0)+1);
            end++;
            while(start<end && distinct>k) {
                if(freq.getOrDefault(nums[start],0)==1) {
                    distinct--;
                }
                freq.put(nums[start],freq.get(nums[start])-1);
                start++;
            }
            ans+=(end-start);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,2,1};
        System.out.println(new SubArraysWithKDistinctIntegers().subarraysWithKDistinct(A,2));
    }

}
/*

Also check nice subarrays.

to get the number of subarrays with at most K distinct elements.
Then f(exactly K) = f(atMost K) - f(atMost K-1).

Time complexity : O(n)

 */
