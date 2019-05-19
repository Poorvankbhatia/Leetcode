/*

Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.



Example 1:

Input: "abbaca"
Output: "ca"
Explanation:
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is
the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

 */
package string.easy;

import java.util.Arrays;

public class RemoveDuplicates {
    public String removeDuplicates(String str) {

        if(str == null) {
            return null;
        }
        return String.copyValueOf(removeDuplicate(str.toCharArray()));
    }

    public char[] removeDuplicate(char[] chars) {
        if(chars.length < 1) return new char[0];
        else if(chars.length == 1) return chars;

        for(int i=0; i<chars.length-1; i++) {
            if(chars[i] == chars[i+1]) {
                char[] before = Arrays.copyOfRange(chars, 0, i);
                char[] after = Arrays.copyOfRange(chars, i+2, chars.length);
                char[] joined = new char[before.length + after.length];
                System.arraycopy(before, 0, joined, 0, before.length);
                System.arraycopy(after, 0, joined, before.length, after.length);
                chars = removeDuplicate(joined);
                break;
            }
        }
        return chars;
    }
}
