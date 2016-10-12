/*

Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False


 */
package binarysearch.medium;

/**
 * Created by poorvank on 11/10/16.
 */
public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {

        if(num==1 || num==0) {
            return true;
        }

        int end,start = 1;
        int startSquare = 1;
        int prevStart = 1;

        while (startSquare<num && startSquare>0) {
            prevStart = start;

            if(startSquare<num) {
                start = start*2;
            }
            startSquare = start*start;
            if(startSquare==num) {
                return true;
            }
        }

        end = start;
        start = prevStart;


        while (end-1>start) {

            int mid = start + (end-start)/2;
            int midSquare = (mid*mid);
            if(midSquare==num) {
                return true;
                // In case the number square causes stackoverflow
            } else if(midSquare>num || midSquare<0) {
                end = mid;
            } else {
                start = mid;
            }

        }


        return false;

    }

    public static void main(String[] args) {
        System.out.println(new ValidPerfectSquare().isPerfectSquare(996004));
    }

}
