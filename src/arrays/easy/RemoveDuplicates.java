/*

Given a sorted array, remove the duplicates in place
such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
It doesn't matter what you leave beyond the new length.

 */
package arrays.easy;

/**
 * Created by poorvank on 07/01/17.
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }
        int n = nums.length;
        int i=0,j=1,count=1;
        while (j<n) {

            if(nums[i]!=nums[j]) {
                count++;
                i++;
                nums[i] = nums[j];
            }
            j++;

        }

        return count;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2};
        System.out.print(new RemoveDuplicates().removeDuplicates(arr));
    }

}
