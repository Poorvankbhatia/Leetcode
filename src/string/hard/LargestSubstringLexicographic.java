/*

Given a string s, return the last substring of s in lexicographical order.



Example 1:

Input: "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
Example 2:

Input: "leetcode"
Output: "tcode"


Note:

1 <= s.length <= 4 * 10^5
s contains only lowercase English letters.

 */
package string.hard;

public class LargestSubstringLexicographic {
    public String lastSubstring(String s) {
        int n = s.length();
        //k is the len when we have two candidates
        //i is the first candidate start position, j is the second one (can not be candidate)
        int i = 0, j= 1, k = 0;
        while (i < n && j < n && k < n) {
            if (i + k >= n || j + k >= n) {
                break;
            }
            // they have same start point, then increase the length
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            } else {
                // now two candidates are different, then which one is larger
                if (s.charAt(i + k) < s.charAt(j + k)) {
                    i = Math.max(i + k + 1, j + 1); // j becomes the candidate, i need move forward
                } else {
                    j = Math.max(j + k + 1, i + 1); // i becomes the candidate
                }
                // if (i == j) {
                //     j++; // potational i, j stay at the same position, j move forward(i also can move)
                // }
                k = 0; // r
            }
        }
        int l = Math.min(i, j);
        return s.substring(l);
    }

    public static void main(String[] args) {
        System.out.println(new LargestSubstringLexicographic().lastSubstring("leetcode"));
    }

}

/*
Edit : why it is O(n) and why we can move for i = i + k + 1

bbb…bbbbbba for this case, if we just update i to i + 1,time complexity will downgrade to o(n^2).
Let me try to prove why it safely move from i to i + k + 1. For example, for "cabcabx" the matching pattern is 'cab',
at 'x' two candidates are mismatching. since 'x' > 'c', wen need move i, that's true we can just move to i + 1.
However, as long as 'cab' is matching pattern. both 'a' and 'b' have been visited by j. so we can safely move to i + k + 1.
In other words, now j becomes i, i becomes to j. why do you want to go back a visited index? the idea is a little similar with KMP.
 */