/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.

 */
package arrays.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank.b on 15/10/17.
 */
public class DegreeArray {

    HashMap<Integer,Integer> frequency = new HashMap<>();
    HashMap<Integer,Integer> firstPos = new HashMap<>();
    HashMap<Integer,Integer> lastPos = new HashMap<>();

    public int findShortestSubArray(int[] nums) {

        for (Integer e: nums) {
            if(!frequency.containsKey(e)) {
                frequency.put(e,0);
            }
            frequency.put(e,frequency.get(e)+1);
        }

        for (int i=0;i<nums.length;i++) {
            if(!firstPos.containsKey(nums[i])) {
                firstPos.put(nums[i],i);
            }
            lastPos.put(nums[i],i);
        }

        List<Integer> maximumOccurringIntegers = new ArrayList<>();
        int max = getMaxFrequency(nums);
        for (int i=0;i<nums.length;i++) {
            if(frequency.get(nums[i])==max) {
                maximumOccurringIntegers.add(nums[i]);
            }
        }

        int min=Integer.MAX_VALUE;

        for (Integer maximumOccurringInteger : maximumOccurringIntegers) {
            min = Math.min(lastPos.get(maximumOccurringInteger) - firstPos.get(maximumOccurringInteger), min);
        }

        return min+1;

    }

    private int getMaxFrequency(int[] nums) {
        int max=Integer.MIN_VALUE;
        for (Integer e : nums) {
            max = Math.max(max,frequency.get(e));
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2,3,1,4,2};
        System.out.println(new DegreeArray().findShortestSubArray(arr));
    }

}
