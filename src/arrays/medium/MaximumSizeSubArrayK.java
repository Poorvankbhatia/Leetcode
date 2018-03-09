/*

Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,
return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)



 */

package arrays.medium;

// 1,4,6,9,0,9,1,1,1,1,1

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 08/02/18.
 */
public class MaximumSizeSubArrayK {

    public int maxSubArrayLen(int[] nums, int k) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        Map<Integer,Integer> sumIndexMap = new HashMap<>();

        int sum=0;
        int maxLen = Integer.MIN_VALUE;

        for (int i=0;i<nums.length;i++) {

            sum+=nums[i];

            if(sum==k) {
                maxLen=Math.max(maxLen,i+1);
            }

            if(sumIndexMap.containsKey(sum-k)) {
                maxLen = Math.max(maxLen,i-sumIndexMap.get(sum-k));
            }


            if(!sumIndexMap.containsKey(sum)) {
                sumIndexMap.put(sum,i);
            }

        }

        return maxLen<0?0:maxLen;



    }


    public static void main(String[] args) {
        int[] arr = new int[]{9,1,4,9,1,1,1,1,1};
        System.out.println(new MaximumSizeSubArrayK().maxSubArrayLen(arr,5));
    }
}
