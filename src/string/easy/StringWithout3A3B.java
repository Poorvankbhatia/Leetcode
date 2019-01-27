/*

Given two integers A and B, return any string S such that:

S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
The substring 'aaa' does not occur in S;
The substring 'bbb' does not occur in S.


Example 1:

Input: A = 1, B = 2
Output: "abb"
Explanation: "abb", "bab" and "bba" are all correct answers.
Example 2:

Input: A = 4, B = 1
Output: "aabaa"


Note:

0 <= A <= 100
0 <= B <= 100
It is guaranteed such an S exists for the given A and B.

 */
package string.easy;

/**
 * Created by poorvank.b on 27/01/19.
 */
public class StringWithout3A3B {

    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();

        while (A > 0 || B > 0) {
            if (A > B) {
                if (A-- > 0) sb.append('a');
                if (A-- > 0) sb.append('a');
                if (B-- > 0) sb.append('b');
            } else if (B > A) {
                if (B-- > 0) sb.append('b');
                if (B-- > 0) sb.append('b');
                if (A-- > 0) sb.append('a');
            } else {
                if (A-- > 0) sb.append('a');
                if (B-- > 0) sb.append('b');
            }
        }

        return sb.toString();
    }
}
