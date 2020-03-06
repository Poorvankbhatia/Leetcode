    /*

Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]

 */
package binarysearch.medium;

/**
 * Created by poorvank on 13/10/16.
 */
public class Pow {

    public double myPow(double x, int n) {

        double temp;
        if( n == 0)
            return 1;
        temp = myPow(x, n/2);
        if (n%2 == 0)
            return temp*temp;
        else
        {
            if(n > 0)
                return x*temp*temp;
            else
                return (temp*temp)/x; // x^-5  =  x^-2 *x^-2 *x^-1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Pow().myPow(2,-3));
    }

}


/*

https://leetcode.com/problems/powx-n/discuss/19544/5-different-choices-when-talk-with-interviewers

 */