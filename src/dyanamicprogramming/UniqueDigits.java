/*

Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:
Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, excluding [11,22,33,44,55,66,77,88,99])

 */

package dyanamicprogramming;

/**
 * Created by poorvank on 23/08/16.
 */
public class UniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {

        if(n>10) {
            n = 10;
        } else if(n==0) {
            return 1;
        } else if(n==1) {
            return 10;
        }
        int[] table = new int[n+1];
        table[1] = 10;
        table[2] = 91;

        for (int i=3;i<=n;i++) {
            table[i] = ((table[i-1]-table[i-2])*(10-(i-1))) + table[i-1];
        }

        return table[n];

    }

    public static void main(String[] args) {

        int n = 3;
        UniqueDigits ud = new UniqueDigits();
        System.out.println(ud.countNumbersWithUniqueDigits(n));
    }

}

/*


Total number of unique digits f(k) = 9 * 9(because first digit can't be zero but second can be)*(10-(k-1)
eg: f(4) = 9*9*8*7
           - - -    --> Calculated previously in f(3)
 */