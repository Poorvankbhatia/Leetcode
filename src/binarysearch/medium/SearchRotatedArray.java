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

        int pivot = findPivot(nums,0,nums.length-1);

        int result = binarySearch(nums,nums.length-1,pivot,target);
        if(result==-1) {
            return binarySearch(nums,pivot-1,0,target);
        }

        return result;
    }

    private int findPivot(int[] nums,int low,int high) {

        if(nums[low]<=nums[high]) {
            return low;
        }

        int mid = low + (high-low)/2;

        if((mid==0 || nums[mid]<nums[mid-1]) && (mid==nums.length-1 || nums[mid]<nums[mid+1])) {
            return mid;
        } else if(nums[mid]>nums[high]) {
            return findPivot(nums,mid+1,high);
        }

        return findPivot(nums,low,mid-1);
    }

    private int binarySearch(int[] nums,int high,int low,int target) {

        if(high>=low) {

            int mid = (low) + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                return binarySearch(nums, mid - 1, low, target);
            }

            return binarySearch(nums, high, mid + 1, target);
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