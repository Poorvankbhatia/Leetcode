/*

Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

 */
package binarysearch.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 12/10/16.
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {

        int firstOccurrence = first(0,nums.length-1,nums,target);
        int secondOccurrence = second(0,nums.length-1,nums,target);

        return new int[]{firstOccurrence,secondOccurrence};


    }


    private int first(int start,int end,int[] nums,int target) {

        if(end>=start) {

            int mid = start + (end-start)/2;

            if((mid==0 || nums[mid]>nums[mid-1]) && nums[mid]==target) {
                return mid;
            } else if(nums[mid]<target) {
                return first(mid+1,end,nums,target);
            }

            return first(start,mid-1,nums,target);

        }

        return -1;
    }


    private int second(int start,int end,int[] nums,int target) {

        if(end>=start) {

            int mid = start + (end-start)/2;

            if((mid==nums.length-1 || nums[mid]<nums[mid+1]) && nums[mid]==target) {
                return mid;
            } else if(nums[mid]>target) {
                return second(start,mid-1,nums,target);
            }

            return second(mid+1,end,nums,target);

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(new SearchRange().searchRange(nums,8)));
    }

}
