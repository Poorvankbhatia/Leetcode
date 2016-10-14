/*

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it
would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

 */
package binarysearch.medium;

/**
 * Created by poorvank on 12/10/16.
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {

        return posBinarySearch(nums,target,0,nums.length-1);

    }

    private int posBinarySearch(int[] nums,int target,int start,int end) {

        if(end==start) {
            if(target>nums[end]) {
                return end+1;
            }
            return start;
        }else if(target<nums[start]) {
            return start;
        }else if(target>nums[end]) {
            return end+1;
        }


        int mid = start + (end - start) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return posBinarySearch(nums, target, start, mid - 1);
        } else {
            return posBinarySearch(nums, target, mid + 1, end);
        }




    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,3,5,6};
        System.out.println(new SearchInsert().searchInsert(nums,0));

    }

}
