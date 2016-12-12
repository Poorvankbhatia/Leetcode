/*

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 10/12/16.
 */
public class UglyNumber {

    public int nthUglyNumber(int n) {

        int[] ugly = new int[n];

        int f2=0,f3=0,f5=0;
        ugly[0] = 1;
        int i=1;
        while (i<n) {

            int m2 = 2*ugly[f2];
            int m3 = 3*ugly[f3];
            int m5 = 5*ugly[f5];

            ugly[i] = Math.min(Math.min(m2,m3),m5);

            /*

            Generating ugly numbers from the nos which are already generated

             */
            if(m2==ugly[i]) {
                f2++;
            }

            if(m3==ugly[i]) {
                f3++;
            }

            if(m5==ugly[i]) {
                f5++;
            }

            i++;

        }

        return ugly[n-1];

    }

    public static void main(String[] args) {
        System.out.println(new UglyNumber().nthUglyNumber(6));
    }

}
