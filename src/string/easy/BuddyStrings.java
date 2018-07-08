/*

Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.



Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false


Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.

 */
package string.easy;

/**
 * Created by poorvank.b on 08/07/18.
 */
public class BuddyStrings {

    public boolean buddyStrings(String A, String B) {

        if(A.length()!=B.length()) {
            return false;
        }

        int len = A.length();

        int[] count=new int[26];
        int diff=0;
        boolean switchSameChars = false;
        int first=-1,second=-1;
        for (int i=0;i<len;i++) {
            if(++count[A.charAt(i)-'a']>=2)  {
                switchSameChars=true;
            }
            if(A.charAt(i)!=B.charAt(i)) {
                diff++;
                if(first==-1) {
                    first=i;
                } else {
                    second=i;
                }
            }
        }

        return (diff == 0 && switchSameChars) || (diff == 2 && A.charAt(first) == B.charAt(second) && A.charAt(second) == B.charAt(first));
    }

}
