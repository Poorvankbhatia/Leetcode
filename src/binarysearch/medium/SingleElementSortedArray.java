/*

Given a sorted array consisting of only integers where every element appears twice except for one element which appears once.
Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.



 */
package binarysearch.medium;

/**
 * Created by poorvank.b on 09/03/17.
 */
public class SingleElementSortedArray {

    public int singleNonDuplicate(int[] nums) {

        if(nums.length==1) {
            return nums[0];
        }

        int n = nums.length;

        int start = 0,end = n-1;
        int result = 0;

        while (start<end) {

            int mid = start + (end-start)/2;

            if (mid%2!=0) {
                if((mid>0 && nums[mid-1]==nums[mid])) {
                    start = mid+1;
                } else {
                    end = mid;
                }
            } else {
                if(mid<n-1 && nums[mid+1]==nums[mid]) {
                    start = mid+1;
                } else {
                    end = mid;
                }
            }

        }

        return nums[start];
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,1,2,3,3,4,4,8,8};
        System.out.println(new SingleElementSortedArray().singleNonDuplicate(nums));

    }

}
