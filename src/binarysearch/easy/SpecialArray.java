/*
You are given an array nums of non-negative integers. nums is considered special if there exists a number x
such that there are exactly x numbers in nums that are greater than or equal to x.

Notice that x does not have to be an element in nums.

Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.



Example 1:

Input: nums = [3,5]
Output: 2
Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.
Example 2:

Input: nums = [0,0]
Output: -1
Explanation: No numbers fit the criteria for x.
If x = 0, there should be 0 numbers >= x, but there are 2.
If x = 1, there should be 1 number >= x, but there are 0.
If x = 2, there should be 2 numbers >= x, but there are 0.
x cannot be greater since there are only 2 numbers in nums.
Example 3:

Input: nums = [0,4,3,0,4]
Output: 3
Explanation: There are 3 values that are greater than or equal to 3.

 */
package binarysearch.easy;

import java.util.Arrays;

public class SpecialArray {

    public int specialArray(int[] nums) {
        Arrays.sort(nums);

        for (int i=0;i<=nums[nums.length-1];i++) {
            int x = equalOrSmallerIndex(nums,i);
            if(x==i) {
                return i;
            }
        }
        return -1;
    }

    private int equalOrSmallerIndex(int[] nums, int value) {
        int lo = 0;
        int hi = nums.length-1;
        while (lo<hi) {
            int mid = lo+(hi - lo)/2;
            if(nums[mid]==value && (mid>0 && nums[mid-1]<value)) {
                lo = mid;
                break;
            } else if(nums[mid]<value) {
                lo = mid+1;
            } else {
                hi = mid;
            }
        }
        if(nums[lo]>=value) {
            return nums.length-lo;
        } else {
            return nums.length-lo-1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SpecialArray().specialArray(new int[]{3,3,6,6,7,8,8,9}));
    }

}

/*
O(n) Count Sort.

public int specialArray(int[] nums) {
        int[] a = new int[1001];
        for(int x : nums) {
            a[x]++;
        }
        for(int i=999;i>=0;i--) {
            a[i]+=a[i+1];
        }
        for(int i=0;i<=1000;i++) {
            if(a[i]==i) {
                return i;
            }
        }
        return -1;
    }

count the amount of numbers
add the amount backward so we can know how many numbers are no less than the current index.
Example:

input [0, 4, 3, 0, 4]

index  0  1  2  3  4  0  ... 1000 // the range defined in the question
v     [2, 0, 0, 1, 2, 0, ..., 0]
# after accumulation
v     [5, 3, 3, 3, 2, 0, ..., 0]
                ^ // 3 numbers are no less than 3

 */