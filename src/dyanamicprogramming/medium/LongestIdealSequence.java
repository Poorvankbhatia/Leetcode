/*
You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal
if the following conditions are satisfied:

t is a subsequence of the string s.
The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
Return the length of the longest ideal string.

A subsequence is a string that can be derived from another string by deleting some or no characters without
changing the order of the remaining characters.

Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of
'a' and 'z' is 25, not 1.



Example 1:

Input: s = "acfgbd", k = 2
Output: 4
Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
Example 2:

Input: s = "abcd", k = 3
Output: 4
Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.


Constraints:

1 <= s.length <= 105
0 <= k <= 25
s consists of lowercase English letters.
 */
package dyanamicprogramming.medium;

public class LongestIdealSequence {

    public int longestIdealString(String s, int k) {
        // int array contains longest ideal seq length for every character.
        int[] charIdealLength = new int[26];
        int length = s.length();
        // mark idel length for last char as 1.
        charIdealLength[s.charAt(s.length() - 1)-'a'] = 1;
        int max = 1;
        // loop from 2nd last character.
        for (int i = length - 2; i >= 0; i--) {
            char a = s.charAt(i);
            // check if another smae char is present after this char.
            max = addIdealLength(max, charIdealLength, a, a);
            // check if any char from 1 to k distance is present i map.
            for (int j = 1; j <= k; j++) {
                if ((s.charAt(i) - 'a') + j < 26) {
                    char b = (char) (s.charAt(i) + j);
                    max = addIdealLength(max, charIdealLength, a, b);
                }
                if ((s.charAt(i) - 'a') - j >= 0) {
                    char b = (char) (s.charAt(i) - j);
                    max = addIdealLength(max, charIdealLength, a, b);
                }
            }
            if (charIdealLength[a-'a']==0) {
                charIdealLength[a-'a']=1;
            }
        }
        return max;
    }

    private int addIdealLength(int max, int[] map, char a, char b) {
        if (map[a-'a'] < map[b-'a'] + 1) {
            map[a-'a'] = map[b-'a'] + 1;
            max = Math.max(max, map[a-'a']);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestIdealSequence().longestIdealString("pvjcci", 4));
    }

}

/*

Better sol

class Solution {
    public int longestIdealString(String s, int k) {
        int DP[] = new int[26], ans = 1;

        for (int ch = 0, n = s.length(); ch < n; ch++) {
            int i = s.charAt(ch) - 'a';
            DP[i] = DP[i] + 1;

            for (int j = Math.max(0, i - k); j <= Math.min(25, i + k); j++)
                if (j != i)
                    DP[i] = Math.max(DP[i], DP[j] + 1);

            ans = Math.max(ans, DP[i]);
        }

        return ans;
    }
}

 */