/*
Given a non-negative integer num, return true if num can be expressed as the sum of any non-negative integer and its reverse, or false otherwise.



Example 1:

Input: num = 443
Output: true
Explanation: 172 + 271 = 443 so we return true.
Example 2:

Input: num = 63
Output: false
Explanation: 63 cannot be expressed as the sum of a non-negative integer and its reverse so we return false.
Example 3:

Input: num = 181
Output: true
Explanation: 140 + 041 = 181 so we return true. Note that when a number is reversed, there may be leading zeros.


Constraints:

0 <= num <= 105
 */

package math.medium;

public class NumberSumAndReverse {

    public boolean sumOfNumberAndReverse(int num) {

        int mid = num/2;
        for (int i=mid;i<=num;i++) {
            if(i+reverse(i)==num) {
                return true;
            }
        }
        return false;

    }

    private int reverse(int x) {
        int rev = 0;
        while (x>0) {
            rev = (rev*10) + (x%10);
            x = x/10;
        }
        return rev;
    }

}
