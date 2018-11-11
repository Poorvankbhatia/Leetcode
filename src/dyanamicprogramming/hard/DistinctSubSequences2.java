/*

Given a string S, count the number of distinct, non-empty subsequences of S .

Since the result may be large, return the answer modulo 10^9 + 7.



Example 1:

Input: "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
Example 2:

Input: "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".
Example 3:

Input: "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".




Note:

S contains only lowercase letters.
1 <= S.length <= 2000


 */
package dyanamicprogramming.hard;

import java.util.Arrays;

/**
 * Created by poorvank.b on 11/11/18.
 */
public class DistinctSubSequences2 {

    public int distinctSubseqII(String S) {
        int n = S.length(), M = (int)1e9 + 7, result = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (S.charAt(j) != S.charAt(i)) {
                    dp[i] += dp[j];
                    dp[i] %= M;
                }
            }
            result += dp[i];
            result %= M;
        }
        return result;
    }

}

/*

dp[i] represents the count of unique subsequence ends with S[i].
dp[i] is initialized to 1 for S[0 ... i]
For each dp[i], we define j from 0 to i - 1, we have:

if s[j] != s[i], dp[i] += dp[j]
if s[j] == s[i], do nothing to avoid duplicates.
Then result = sum(dp[0], ... dp[n - 1])
Time complexity: O(n^2)


Furthermore, we can use a sum to represent sum(dp[0], ..., dp[i - 1]).
And also a count array, in which count[S.charAt(i) - 'a'] represents the count of presented subsequence ends with S.charAt(i).
Then dp[i] = sum - count[S.charAt(i) - 'a'].
Time complexity: O(n)

class Solution {
    public int distinctSubseqII(String S) {
        int n = S.length(), M = (int)1e9 + 7, result = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] count = new int[26];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int index = S.charAt(i) - 'a';
            dp[i] += sum - count[index];
            dp[i] = (dp[i] + M) % M;
            sum += dp[i];
            sum %= M;
            count[index] += dp[i];
            count[index] %= M;
            result += dp[i];
            result %= M;
        }
        return result;
    }
}
Optimize to O(1) space:

class Solution {
    public int distinctSubseqII(String S) {
        int n = S.length(), M = (int)1e9 + 7, result = 0;
        int[] count = new int[26];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int index = S.charAt(i) - 'a';
            int cur = (1 + sum - count[index] + M) % M;
            sum = (sum + cur) % M;
            count[index] = (count[index] + cur) % M;
            result = (result + cur) % M;
        }
        return result;
    }
}

 */