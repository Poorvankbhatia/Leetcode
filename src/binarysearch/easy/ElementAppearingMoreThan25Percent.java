/*

Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.



Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6


Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5

 */
package binarysearch.easy;

public class ElementAppearingMoreThan25Percent {

    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        if(n==1) {
            return arr[0];
        }
        int element = arr[n/4];
        int f = firstOccurrence(0,n-1,arr,element);
        int l = lastOccurrence(0,n-1,arr,element);
        if(l-f+1>n/4) {
            return element;
        }
        element = arr[n/2];
        f = firstOccurrence(0,n-1,arr,element);
        l = lastOccurrence(0,n-1,arr,element);
        if(l-f+1>n/4) {
            return element;
        }
        element = arr[(3*n)/4];
        f = firstOccurrence(0,n-1,arr,element);
        l = lastOccurrence(0,n-1,arr,element);
        if(l-f+1>n/4) {
            return element;
        }
        return -1;
    }

    private int firstOccurrence(int start,int end,int[] nums, int target) {

        if(start>end) {
            return -1;
        }

        int mid = start+(end-start)/2;
        if(nums[mid]==target) {
            if(mid>0 && nums[mid-1]<target) {
                return mid;
            } else if(mid>0) {
                return firstOccurrence(start,mid-1,nums,target);
            } else {
                return mid;
            }
        } else if(nums[mid]>target) {
            return firstOccurrence(start,mid-1,nums,target);
        } else {
            return firstOccurrence(mid+1,end,nums,target);
        }

    }

    private int lastOccurrence(int start,int end,int[] nums,int target) {
        if(start>end) {
            return -1;
        }

        int mid = start+(end-start)/2;
        if(nums[mid]==target) {
            if(mid<nums.length-1 && nums[mid+1]>target) {
                return mid;
            } else if(mid<nums.length-1) {
                return lastOccurrence(mid+1,end,nums,target);
            } else {
                return mid;
            }
        } else if(nums[mid]>target) {
            return lastOccurrence(start,mid-1,nums,target);
        } else {
            return lastOccurrence(mid+1,end,nums,target);
        }

    }

    public static void main(String[] args) {
        System.out.println(new ElementAppearingMoreThan25Percent().findSpecialInteger(new int[]{1,2,3,3}));
    }

}
