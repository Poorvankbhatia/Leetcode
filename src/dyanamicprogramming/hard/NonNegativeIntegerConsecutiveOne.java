/*

Given a positive integer n, find the number of non-negative integers less than or equal to n, whose binary representations do NOT contain consecutive ones.

Example 1:
Input: 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
Note: 1 <= n <= 10^9

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank.b on 15/01/19.
 */
public class NonNegativeIntegerConsecutiveOne {

    public int findIntegers(int num) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int n = sb.length();

        int a[] = new int[n];
        int b[] = new int[n];
        a[0] = b[0] = 1;
        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + b[i - 1];
            b[i] = a[i - 1];
        }

        int result = a[n - 1] + b[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (sb.charAt(i) == '1' && sb.charAt(i + 1) == '1') break;
            if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '0') result -= b[i];
        }

        return result;
    }

}
/*

let one[i] be the number of non-consecutive-1 solutions with the i-th significant bit being 1;
let zero[i] be the number of non-consecutive-1 solutions with the i-th significant bit being 0;
the most tricky part is how to count the solutions smaller than num.
We first calculate number of all n-bits solutions: res = one[n - 1] + zero[n - 1].
then we subtract the solutions which is larger than num, we iterate from the MSB to LSB of num:

if bit[i] == 1
if bit[i + 1] == 0, there's no solutions in res that is larger than num, we go further into smaller bit and subtract.
if bit[i + 1] == 1, we know in those res solutions it won't have any consecutive ones. when bit[i + 1] == 1, in one[i + 1],
the i-th bit in valid solutions must be 0, which are all smaller than 'num', we don't need to check smaller bits and subtract, so we break form the loop.
if bit[i] == 0
if bit[i + 1] == 1, there's no solutions in res that is larger than num, we go further into smaller bit and subtract.
if bit[i + 1] == 0, we know zero[i + 1] includes solutions of i-th == 0 (00***) and i-th bit == 1 (01***), we know the i-th
bit of num is 0, so we need to subtract all the 01*** solutions because it is larger than num. Therefore, one[i] is subtracted from res.

 */
