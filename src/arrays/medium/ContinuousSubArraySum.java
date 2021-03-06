/*

Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray
of **size at least 2**
that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

 */
package arrays.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 26/02/17.
 */
public class ContinuousSubArraySum {

    public boolean checkSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1); //{0,0} k=0
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) {
                runningSum %= k;
            }
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) {
                    return true;
                }
            }
            else map.put(runningSum, i);
        }
        return false;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,5};
        int k = -6; // 6*0=0
        System.out.println(new ContinuousSubArraySum().checkSubarraySum(arr, k));
    }

}

/*

We iterate through the input array exactly once, keeping track of the running sum mod k of the elements in the process.
If we find that a running sum value at index j has been previously seen before in some earlier index i in the array,
then we know that the sub-array (i,j] contains a desired sum.

[0], 0 -> false;
[5, 2, 4], 5 -> false;
[0, 0], 100 -> true;
[1,5], -6 -> true;

Running sum from first element to index i : sum_i. If we mod k, it will be some format like : sum_i = k * x + modk_1
Running sum from first element to index j : sum_j. If we mod k, it will be some format like : sum_j = k * y + modk_2
If they have the same mod, which is modk_1 == modk_2, subtracting these two running sum we get the difference
sum_i - sum_j = (x - y) * k = constant * k, and the difference is the sum of elements between index i and j, and the value is a multiple of k.

 */