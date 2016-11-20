/*

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */
package arrays.easy;

/**
 * Created by poorvank on 20/11/16.
 */
public class MoveZeros {

    public void moveZeroes(int[] nums) {

        int index=0;

        int  n = nums.length;

        for (int i=0;i<n;i++) {
            if(nums[i]!=0) {
                nums[index] = nums[i];
                index++;
            }
        }

        for (int i=index;i<n;i++) {
            nums[i] = 0;
        }

    }

}

/*

Space Complexity : O(1)O(1). Only constant space is used.

Time Complexity: O(n)O(n).

 */


/*

The total number of operations of the above approach is sub-optimal. For example, the array which has all (except last)
leading zeroes: [0, 0, 0, ..., 0, 1].How many write operations to the array? For the previous approach, it writes
0's n-1 times, which is not necessary. We could have instead written just once. How? ..... By only fixing the non-0 element,i.e., 1.

 A simple realization is if the current element is non-0, its' correct position can at best be it's current position or a position earlier.
 If it's the latter one, the current position will be eventually occupied by a non-0 ,or a 0, which lies at a index greater than 'cur' index.
  We fill the current position by 0 right away,so that unlike the previous solution, we don't need to come back here in next iteration.

  Another approach:

   public void moveZeroes(int[] nums) {
        int index=0;

        int  n = nums.length;

        for (int i=0;i<n;i++) {
            if(nums[i]!=0) {
                swap(index,i,nums);
                index++;
            }
        }
    }

    private void swap(int a,int b,int[] nums) {
        int temp = nums[a];
        nums[a]=nums[b];
        nums[b] = temp;
    }

 */