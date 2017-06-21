/*

Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

 */
package arrays.easy;

import java.util.Arrays;

/**
 * Created by poorvank on 08/06/17.
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {

        if(digits.length==0) {
            return new int[]{1};
        }

        int length = digits.length;

        int carry = 1;

        for (int i=length-1;i>=0;i--) {
            if(carry==0) {
                return digits;
            }
            digits[i]+=carry;
            carry = digits[i]/10;
            digits[i]%=10;
        }

        if(carry!=0) {
            int[] result = new int[length+1];
            result[0]=1;
            System.arraycopy(digits, 1, result, 1, length - 1);
            return result;
        }

        return digits;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{8};
        System.out.println(Arrays.toString(new PlusOne().plusOne(arr)));
    }

}
