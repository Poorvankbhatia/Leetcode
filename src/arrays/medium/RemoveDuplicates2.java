/*

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 It doesn't matter what you leave beyond the new length.

 */
package arrays.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 07/01/17.
 */
public class RemoveDuplicates2 {

    public int removeDuplicates(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }
        //Number of allowed duplicates
        int k = 2;
        int n = nums.length;
        int i=0,j=1,count=1;
        while (j<n) {


            if(nums[i]!=nums[j] || (nums[i]==nums[j]) && (i<k-1 || (i>0 && nums[i]!=nums[i-(k-1)]))) {
                count++;
                i++;
                nums[i] = nums[j];
            }
            j++;

        }

        return count;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,1,2,2,3};
        System.out.println(new RemoveDuplicates2().removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

}
