/*
You are given a binary array nums containing only the integers 0 and 1.
Return the number of subarrays in nums that have more 1's than 0's. Since the answer may be very large,
return it modulo 109 + 7.


A subarray is a contiguous sequence of elements within an array.



Example 1:

Input: nums = [0,1,1,0,1]
Output: 9
Explanation:
The subarrays of size 1 that have more ones than zeros are: [1], [1], [1]
The subarrays of size 2 that have more ones than zeros are: [1,1]
The subarrays of size 3 that have more ones than zeros are: [0,1,1], [1,1,0], [1,0,1]
The subarrays of size 4 that have more ones than zeros are: [1,1,0,1]
The subarrays of size 5 that have more ones than zeros are: [0,1,1,0,1]
Example 2:

Input: nums = [0]
Output: 0
Explanation:
No subarrays have more ones than zeros.
Example 3:

Input: nums = [1]
Output: 1
Explanation:
The subarrays of size 1 that have more ones than zeros are: [1]


Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 1
 */
package arrays.hard;

import java.util.HashMap;

public class SubArraysWithMoreZeroesThanOnes {

    public int subarraysWithMoreZerosThanOnes(int[] nums) {

        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(0,1);

        // count: cumulative number of new valid subarray ending at i
        int res = 0, count = 0, prefixSum = 0;
        for (int num : nums) {
            if (num == 1) {
                // update prefixSum
                prefixSum++;
                // any subarray ending at i-1 and has sum = 0 will create a new valid subarray
                count += map.getOrDefault(prefixSum - 1, 0);
            } else {
                // replace 0 with -1, then update prefixSum
                prefixSum--;
                // any subarray ending at i-1 and has sum = 1 will become invalid.
                count -= map.getOrDefault(prefixSum, 0);
            }

            res += count;
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            res %= 1000000007;
        }

        return res;
    }

}

/*

Replace 0 with -1, then calculate the prefix sum. If prefixSum[i] > 0, then subarray nums[0:i] has more 1's than 0's.


We can use a sample("01111000000110") to depict all the scenarios.

Step1: we transit "0" to "-1".
Step2: we create a prefixSum array. Prefix[i] is the sum from nums[0] to nums[i](after transition).

Then, our new problem is: for any index i, how many points before the ith point is lower than it.

Step3:
In #2 and #3, these points are the new highest points. In #2, it is higher than all points before except zero. In #3,
 it is higher than all points before.
In #1 and #4, they are lower than all points before, so they have no contribution.
In #5 and #6, they have previous points before. If values are lower than the first "1", they will be lower than the
second "1". Therefore, the basic value of repeated value is the value of its precedent. For #6, it inheritances three
 from its precedent(0, -1, 0). For #5, it gets nothing. Then, we need to calculate the offset value from the
 precedent index to the ith point. For #5, they are three points (-2, -3, -2). For #6, this is nothing.
Tip 1: We can approve that all points between #5 and its precedent are lower than it. Because #5 is higher than
the point before it and #5 is a repeated point. For #6, vice versa, its contribution is always 0.
Tip 2: We can use a small trick to identify the situation is #5 or #6 when we meet repeated numbers. If nums[i] is 1,
 it is a #5 situation; otherwise, it is a #6 situation. It also can identify a new point in the hashmap as a new
 highest point(#2, #3) or a new lowest one(#1, #4).

class Solution {
    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        // <prefixSum, index>
        Map<Integer, Integer> hm = new HashMap<>();
        int mod = 1_000_000_007;
        int[] dp = new int[nums.length];
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            // transition
            sum += nums[i] == 1 ? 1 : -1;
            if (!hm.containsKey(sum)) {
                if ((nums[i] == 1)) {
                    // new highest
                    dp[i] = i + 1;
                    if (sum == 0) dp[i] -= 1;
                } else {
                    // new lowest;
                }
            } else {
                // repeated
                dp[i] = dp[hm.get(sum)];
                if (nums[i] == 1) {
                    // bow
                    dp[i] += i - (hm.get(sum) + 1);
                } else {
                    // dome
                }
            }
            hm.put(sum, i);
            res = (res + dp[i]) % mod;
        }
        return res;
    }
}

 */