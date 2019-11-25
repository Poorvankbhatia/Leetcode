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
        int lo = 0;
        int hi = nums.length-1;
        while(lo<hi) {
            int mid = (lo)+(hi-lo)/2;
            if(nums[mid]>nums[nums.length-1]) {
                lo=mid+1;
            } else {
                hi=mid;
            }
        }
        return nums[lo];
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