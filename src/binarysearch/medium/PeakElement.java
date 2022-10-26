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

        System.out.println(new PeakElement().findPeakElement(new int[]{30,41,24,11,24,23,14,43,18,45,44,42,5,39,41,11,35,47,16,11,30,25,18,41,45}));

    }

}

/*
we are essentially doing is going in the direction of the rising slope(by choosing the element which is greater
than current). How does that guarantee the result? Think about it, there are 2 possibilities.
a) rising slope has to keep rising till end of the array
b) rising slope will encounter a lesser element and go down.
In both scenarios we will have an answer.
a) the answer is the end element because we take the boundary as -INFINITY
b) the answer is the largest element before the slope falls.
 */