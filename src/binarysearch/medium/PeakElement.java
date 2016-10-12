/*

A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

 */
package binarysearch.medium;

/**
 * Created by poorvank on 11/10/16.
 */
public class PeakElement {

    public int findPeakElement(int[] nums) {
        return findPeakUtil(nums,0,nums.length-1);
    }

    private int findPeakUtil(int[] nums,int low,int high) {

        if(low==high) {
            return low;
        }

        if(high==low+1) {
            return nums[high]>nums[low]?high:low;
        }

        int mid = low + (high-low)/2;

        if(high>low) {

            if((mid==0 || nums[mid]>nums[mid-1]) && (mid==nums.length-1 || nums[mid]>nums[mid+1])) {
                return mid;
            }
            else if(nums[mid]<nums[mid-1]) {
                return findPeakUtil(nums,low,mid-1);
            } else {
                return findPeakUtil(nums,mid+1,high);
            }

        }

        return -1;

    }

    public static void main(String[] args) {

        System.out.println(new PeakElement().findPeakElement(new int[]{1,2,3,1}));

    }

}
