/*

Given an integer array arr and an integer difference, return the length of the longest subsequence in arr
which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.



Example 1:

Input: arr = [1,2,3,4], difference = 1
Output: 4
Explanation: The longest arithmetic subsequence is [1,2,3,4].
Example 2:

Input: arr = [1,3,5,7], difference = 1
Output: 1
Explanation: The longest arithmetic subsequence is any single element.
Example 3:

Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
Output: 4
Explanation: The longest arithmetic subsequence is [7,5,3,1].


Constraints:

1 <= arr.length <= 10^5
-10^4 <= arr[i], difference <= 10^4

 */
package hashing.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticWithGivenDiff {

    public int longestSubsequence(int[] arr, int diff) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int a : arr) {
            if (map.containsKey(a - diff)) {
                map.put(a, map.get(a - diff) + 1);
            } else {
                map.put(a, 1);
            }
            max = Math.max(map.get(a), max);
        }
        return max;
    }

}
