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
        int i = 0, j = 1, offset = 0, len = s.length();
        while (i + offset < len && j + offset < len) {
            char c = s.charAt(i + offset), d = s.charAt(j + offset);
            if (c == d) {
                ++offset;
            }else {
                if (c < d)  { // chars in [i, ..., i + offset] <= charAt(i) == charAt(j)
                    i += offset + 1;
                }
                else { // c > d, chars in [j, ..., j + offset] <= charAt(i) == charAt(j)
                    j += offset + 1;
                }
                if (i == j) {// avoid duplicate start indices.
                    ++i;
                }
                offset = 0; // reset offset to 0.
            }
        }
        return s.substring(Math.min(i, j));
    }

    public static void main(String[] args) {
        System.out.println(new LargestSubstringLexicographic().lastSubstring("leetcode"));
    }

}
