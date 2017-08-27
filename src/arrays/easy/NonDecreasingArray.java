/*
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.
Note: The n belongs to [1, 10,000].
 */
package arrays.easy;

/**
 * Created by poorvank.b on 27/08/17.
 */
public class NonDecreasingArray {

    public boolean checkPossibility(int[] nums) {

        if(nums == null || nums.length<=2){
            return true;
        }
        int index = 0;
        int count = 0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                count++;
                index = i;
            }
            if(count>1){
                return false;
            }
        }

        if(index==0 || index+1 == nums.length-1){
            return true;
        }
        return nums[index+1]>=nums[index-1] || nums[index+2]>=nums[index];

    }

    public static void main(String[] args) {
        System.out.println(new NonDecreasingArray().checkPossibility(new int[]{3,4,2,3}));
    }

}
