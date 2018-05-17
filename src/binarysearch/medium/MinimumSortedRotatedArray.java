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


/*

With Duplicates :

When duplicates are allowed, we don't really know if the pivot is located before or after the mid.. example : nums=[10,10,10,1,10]

Run-time complexity is affected by the degree of duplicate, i.e. the number of duplicates and how they distribute in the sequence,
worst case o(log(n-m) +o(m) where n is the length and m the number of duplicates

class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        int min = Integer.MAX_VALUE;
        while(low <= high){
            int mid = (low + high) / 2;
            min = Math.min(min, nums[mid]);
            if(nums[low] < nums[mid] || nums[mid] > nums[high]){
                min = Math.min(min, nums[low]);
                low = mid + 1;
            }else if(nums[low] > nums[mid] || nums[mid] < nums[high]){
                high = mid - 1;
            }else {
                low++;
            }
        }
        return min;
    }
}

 */