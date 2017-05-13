/*


Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly
twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

 */
package bits.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 07/05/17.
 */
public class SingleNumber2 {

    public int[] singleNumber(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int xor = nums[0];

        for (int i=1;i<nums.length;i++) {
            xor ^= nums[i];
        }

        int rightMostSetBit = xor & ~(xor-1);

        System.out.println(rightMostSetBit);

        int x=0,y=0;

        for (Integer num : nums) {
            if((num & rightMostSetBit)>0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }

        return new int[]{x,y};

    }

    public static void main(String[] args) {
        int[] ar = new int[]{1, 2, 1, 3, 2, 5};
        System.out.println(Arrays.toString(new SingleNumber2().singleNumber(ar)));
    }

}

/*

Let x and y be the non-repeating elements we are looking for and arr[] be the input array. First calculate the XOR of all the array elements.

     xor = arr[0]^arr[1]^arr[2].....arr[n-1]
All the bits that are set in xor will be set in one non-repeating element (x or y) and not in other. So if we take any set bit of xor and
divide the elements of the array in two sets – one set of elements with same bit set and other set with same bit not set. By doing so,
we will get x in one set and y in another set. Now if we do XOR of all the elements in first set, we will get first non-repeating element,
and by doing same in other set we will get the second non-repeating element.

Let us see an example.
   arr[] = {2, 4, 7, 9, 2, 4}
1) Get the XOR of all the elements.
     xor = 2^4^7^9^2^4 = 14 (1110)
2) Get a number which has only one set bit of the xor.
   Since we can easily get the rightmost set bit, let us use it.
     set_bit_no = xor & ~(xor-1) = (1110) & ~(1101) = 0010
   Now set_bit_no will have only set as rightmost set bit of xor.
3) Now divide the elements in two sets and do xor of
   elements in each set, and we get the non-repeating
   elements 7 and 9. Please see implementation for this
   step.

 */