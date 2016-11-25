/*

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

 */

package arrays.easy;

/**
 * Created by poorvank on 25/11/16.
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {

        if(k==0 || k==nums.length) {
            return;
        }

        if(k>nums.length) {
            k = k%nums.length;
        }

        k = nums.length -k;

        reverseArray(0,k-1,nums);
        reverseArray(k,nums.length-1,nums);
        reverseArray(0,nums.length-1,nums);

    }

    private void reverseArray(int start,int end,int[] nums) {
        while (start <= end) {
            int t = nums[start];
            nums[start] = nums[end];
            nums[end] = t;
            start++;
            end--;
        }

    }

}

/* Right rotate an array by K steps meaning Left rotate an array by len-k steps

   LR => arr[i]=arr[i+1];
   RR => arr[i]=arr[i-1];

 */
