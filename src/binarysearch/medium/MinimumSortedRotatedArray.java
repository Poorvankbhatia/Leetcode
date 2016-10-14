/*

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.

 */
package binarysearch.medium;

/**
 * Created by poorvank on 12/10/16.
 */
public class MinimumSortedRotatedArray {

    public int findMin(int[] nums) {
        return findMinBinarySearch(nums,0,nums.length-1);
    }

    private int findMinBinarySearch(int[] nums,int start,int end) {

        if(nums[start]<=nums[end]) {
            return nums[start];
        }

        int mid = start + (end - start) / 2;

        if ((mid == 0 || nums[mid] < nums[mid - 1]) && (mid == nums.length - 1 || nums[mid] < nums[mid + 1])) {
            return nums[mid];
        } else if(nums[mid]>=nums[start]) {
            return findMinBinarySearch(nums,mid+1,end);
        } else {
            return findMinBinarySearch(nums,start,mid-1);
        }



    }

}
