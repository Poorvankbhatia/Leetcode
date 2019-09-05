/*

The k-digit number N is an Armstrong number if and only if the k-th power of each digit sums to N.

Given a positive integer N, return true if and only if it is an Armstrong number.



Example 1:

Input: 153
Output: true
Explanation:
153 is a 3-digit number, and 153 = 1^3 + 5^3 + 3^3.
Example 2:

Input: 123
Output: false
Explanation:
123 is a 3-digit number, and 123 != 1^3 + 2^3 + 3^3 = 36.


Note:

1 <= N <= 10^8

 */
package math.easy;

import java.util.ArrayList;
import java.util.List;

public class ArmstrongNumber {

    public boolean isArmstrong(int N) {

        if (N == 0 || N == 1) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        int n = N;
        while (N != 0) {
            list.add(0, N % 10);
            N /= 10;
        }


        int sum = 0;
        for (Integer k : list) {
            sum += Math.pow(k, list.size());
        }
        return sum == n;


    }

    public static void main(String[] args) {
        int N = 153;
        System.out.println(new ArmstrongNumber().isArmstrong(N));
    }

}

