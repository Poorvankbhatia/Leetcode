/*

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

 */
package arrays.medium;

import java.util.*;

/**
 * Created by poorvank.b on 30/04/17.
 */
public class SubArraysWithGivenSum {

    public int subarraySum(int[] input, int k) {

        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < input.length; i++) {
            sum += input[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            // for cases like [0,0,0,0,0,0,0,0,0]
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,0,1,0,0,1,0,12,2};
        System.out.println(new SubArraysWithGivenSum().subarraySum(arr,0));
    }

}

/*

we know the key to solve this problem is SUM[i, j]. So if we know SUM[0, i - 1] and SUM[0, j],
then we can easily get SUM[i, j]. To achieve this, we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).

 */