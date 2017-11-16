/*

Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:
Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank.b on 12/11/17.
 */
public class MinimumWindowSubSequence {

    public String minWindow(String S, String T) {
        int min = -1, index = -1;
        char[] tArr = T.toCharArray();
        char[] sArr = S.toCharArray();
        for (int i = 0; i < S.length(); i++) {
            if (sArr[i] != tArr[0]) {
                continue;
            }
            int len = check(tArr, sArr, i);
            if (len <= 0) break;
            if (min == -1 || len < min) {
                index = i;
                min = len;
            }
        }
        if (min == -1) {
            return "";
        }
        return S.substring(index, index + min);
    }

    public int check(char[] Tc, char[] Sc, int start) {
        int i = start, j = 0;
        while (i < Sc.length && j < Tc.length) {
            if (Sc[i] == Tc[j]) {
                j++;
            }
            i++;
        }
        if (j == Tc.length) {
            return i - start;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "abcdebdde";
        String t = "bde";
        System.out.println(new MinimumWindowSubSequence().minWindow(s,t));
    }

}

/*

Brute Force complexity = (ST)

DP Method:

At time j, for each position e in S (e for end), let's remember the largest index cur[e] = s (for start)
 so that S[s: e+1] has T[:j] as a subsequence, and -1 (or None) otherwise if it isn't possible.

To update our knowledge as j += 1, if S[i] == T[j], then new[e] is last, the largest s we
have seen so far (representing that T[:j] was found). We can prove this is just the most recent valid index we have seen.

we use dp[j] and dp[~j] to keep track of the last two rows of our dynamic programming.

class Solution {
    public String minWindow(String S, String T) {
        int[][] dp = new int[2][S.length()];

        for (int i = 0; i < S.length(); ++i)
            dp[0][i] = S.charAt(i) == T.charAt(0) ? i : -1;

        for (int j = 1; j < T.length(); ++j) {
            int last = -1;
            Arrays.fill(dp[j & 1], -1);
            for (int i = 0; i < S.length(); ++i) {
                if (last >= 0 && S.charAt(i) == T.charAt(j))
                    dp[j & 1][i] = last;
                if (dp[~j & 1][i] >= 0)
                    last = dp[~j & 1][i];
            }
        }

        int start = 0, end = S.length();
        for (int e = 0; e < S.length(); ++e) {
            int s = dp[~T.length() & 1][e];
            if (s >= 0 && e - s < end - start) {
                start = s;
                end = e;
            }
        }
        return end < S.length() ? S.substring(start, end+1) : "";
    }
}

 */