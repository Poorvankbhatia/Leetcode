/*

Given an array nums of positive integers, return the longest possible length of an array prefix of nums, such that it is possible to
remove exactly one element from this prefix so that every number that has appeared in it will have the same number of occurrences.

If after removing one element there are no remaining elements, it's still considered that every appeared number has the same number of ocurrences (0).



Example 1:

Input: nums = [2,2,1,1,5,3,3,5]
Output: 7
Explanation: For the subarray [2,2,1,1,5,3,3] of length 7, if we remove nums[4]=5, we will get [2,2,1,1,3,3], so that each number will appear exactly twice.
Example 2:

Input: nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
Output: 13
Example 3:

Input: nums = [1,1,1,2,2,2]
Output: 5
Example 4:

Input: nums = [10,2,8,9,3,8,1,5,2,3,7,6]
Output: 8


Constraints:

2 <= nums.length <= 10^5
1 <= nums[i] <= 10^5

 */
package hashing.hard;

import java.util.HashMap;
import java.util.Map;

public class MaximumEqualFrequency {

    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
            int freq = countMap.get(nums[i]);
            freqMap.put(freq, freqMap.getOrDefault(freq, 0) + 1);
            int count = freqMap.get(freq) * freq;
            if (count == i + 1 && i != nums.length - 1) {
                res = Math.max(res, i + 2);
            } else if (count == i) {
                res = Math.max(res, i + 1);
            }
        }
        return res;
    }

}

/*

Count frequency of the elements
We also need to count the number of elements with that frequency

Explanation
There are 2 cases when we need to update the result:

Case 1:
frequency * (number of elements with that frequency) == length AND i != nums.length - 1
E.g. [1,1,2,2,3]
When the iteration is at index 3, the count will be equal to the length. It should update the result with
(length + 1) as it should take an extra element in order to fulfil the condition.

Case 2:
frequency * (number of elements with that frequency) == length - 1
E.g. [1,1,1,2,2,3]
When the iteration is at index 4, the count will be equal to (length - 1). It should update the result with length as it fulfil the condition.

Complexity
Time: O(N) where N is the number of elements
Space: O(N)

 */