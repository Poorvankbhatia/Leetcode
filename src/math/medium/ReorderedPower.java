/*

Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.



Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true


 */
package math.medium;

import java.util.Arrays;

/**
 * Created by poorvank.b on 21/07/18.
 */
public class ReorderedPower {

    public boolean reorderedPowerOf2(int N) {
        char[] a1 = String.valueOf(N).toCharArray();
        Arrays.sort(a1);
        String s1 = new String(a1);

        for (int i = 0; i < 31; i++) {
            char[] a2 = String.valueOf((int)(1 << i)).toCharArray();
            Arrays.sort(a2);
            String s2 = new String(a2);
            if (s1.equals(s2)) return true;
        }

        return false;
    }

}

/*

First, we convert the input number (N) into a string and sort the string. Next, we get the digits that form the power of 2
(by using 1 << i and vary i), convert them into a string, and then sort them. As we convert the powers of 2
(and there are only 31 that are <= 10^9), for each power of 2, we compare if the string is equal to that of string
based on N. If the two strings are equal, then we return true.

 */