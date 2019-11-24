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
        if(nums==null || nums.length==0) {
            return new int[]{-1,-1};
        }
        return new int[]{find(nums,0,nums.length-1,target,true),find(nums,0,nums.length-1,target,false)};
    }

    private int find(int[] nums,int start,int end,int target,boolean first) {
        if(target > nums[end] || target < nums[start] || start>end) {
            return -1;
        }
        if(nums[start]==target && first) {
            return start;
        }
        if(nums[end]==target && !first) {
            return end;
        }
        int mid = start+(end-start)/2;
        if(first) {
            if(nums[mid]==target && mid>0 && nums[mid-1]<target){
                return mid;
            } else if(nums[mid]<target) {
                return find(nums,mid+1,end,target,true);
            } else {
                return find(nums,start,mid,target, true);
            }
        } else {
            if(nums[mid]==target && mid<nums.length-1 && nums[mid+1]>target) {
                return mid;
            } else if(nums[mid]<=target) {
                return find(nums,mid+1,end,target,false);
            } else {
                return find(nums,start,mid,target,false);
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(new SearchRange().searchRange(nums,8)));
    }

}
