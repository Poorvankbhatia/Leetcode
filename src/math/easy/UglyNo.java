/*

Write a program to check whether a given number is an ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is
not ugly since it includes another prime factor 7.

Note that 1 is typically treated as an ugly number.

 */
package math.easy;

/**
 * Created by poorvank.b on 11/03/17.
 */
public class UglyNo {

    public boolean isUgly(int num) {
        if(num==1) {
            return true;
        }

        if(num<=0) {
            return false;
        }

        while (num%5==0 && num>0) {
            num = num/5;
        }

        while (num%2==0 && num>0) {
            num = num/2;
        }

        while (num%3==0 && num>0) {
            num = num/3;
        }

        return num==1;
    }

}
