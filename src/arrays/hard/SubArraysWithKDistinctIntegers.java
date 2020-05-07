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

    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    // all subarrays with <= K distinct numbers are counted.
    private int atMostK(int[] A, int K) {
        int i = 0, j = 0;
        int total = 0;
        int distinct = 0;   // count of distinct numbers in the window.
        Map<Integer, Integer> counter = new HashMap<>();
        while (j < A.length) {
            if (counter.getOrDefault(A[j], 0) == 0) {
                distinct++;
            }
            counter.put(A[j], 1 + counter.getOrDefault(A[j], 0));
            j++;
            while (i < j && distinct > K) {  // shrink the left boundary of window.
                counter.put(A[i], counter.get(A[i]) - 1);
                if (counter.get(A[i]) == 0) {
                    distinct--;
                }
                i++;
            }

            total += j - i;
        }

        return total;
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
