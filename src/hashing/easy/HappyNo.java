/*

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until
the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which
this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

 */
package hashing.easy;

import java.util.HashSet;

/**
 * Created by poorvank.b on 27/11/16.
 */
public class HappyNo {

    public boolean isHappy(int n) {

        HashSet<Integer> alreadySeen = new HashSet<>();

        while (n!=1) {

            int sum = 0;
            int k = n;
            while (k!=0) {
                int digit = k%10;
                sum += (digit*digit);
                k = k/10;
            }

            n=sum;
            if(alreadySeen.contains(n)) {
                return false;
            } else {
                alreadySeen.add(n);
            }

        }

        return true;

    }

    public static void main(String[] args) {
        System.out.print(new HappyNo().isHappy(19));
    }

}
