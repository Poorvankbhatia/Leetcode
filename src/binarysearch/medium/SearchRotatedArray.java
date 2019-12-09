/*

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.


 */
package binarysearch.medium;

/**
 * Created by poorvank on 13/10/16.
 */
public class SearchRotatedArray {

    public int search(int[] nums, int target) {
        if(nums.length==0) {
            return -1;
        }
        int pivot = findPivot(nums);
        //System.out.println(pivot);
        int val = bs(0,pivot,nums,target);
        if(val==-1) {
            return bs(pivot+1,nums.length-1,nums,target);
        }
        return val;
    }

    private int findPivot(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        while(start<end) {
            int mid = start+(end-start)/2;
            if((mid==0 || nums[mid]>nums[mid-1]) && (mid== nums.length-1 || nums[mid]>nums[mid+1])) {
                return mid;
            }
            if(nums[mid]<nums[nums.length-1]) {
                end=mid-1;
            } else {
                start=mid+1;
            }
        }
        return start;
    }

    private int bs(int start,int end,int[] nums,int target) {
        while(start<=end) {
            int mid = start+(end-start)/2;
            if(nums[mid]==target) {
                return mid;
            }
            if(nums[mid]>target) {
                end=mid-1;
            } else {
                start=mid+1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{5,1,3};
        int n =5;
        System.out.println(new SearchRotatedArray().search(arr,n));
    }

}

/*

For an array containing duplicates , worst case complexity is O(n) only.

For duplicates:

public int findMin(int[] num) {
    return findMin(num, 0, num.length-1);
}

public int findMin(int[] num, int left, int right){
    if(right==left){
        return num[left];
    }
    if(right == left +1){
        return Math.min(num[left], num[right]);
    }
    // 3 3 1 3 3 3

    int middle = (right-left)/2 + left;
    // already sorted
    if(num[right] > num[left]){
        return num[left];
    //right shift one
    }else if(num[right] == num[left]){
        return findMin(num, left+1, right);
    //go right
    }else if(num[middle] >= num[left]){
        return findMin(num, middle, right);
    //go left
    }else{
        return findMin(num, left, middle);
    }
}

worst case it will O(n)

 */