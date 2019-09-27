/*

Given a string S, return the number of substrings of length K with no repeated characters.



Example 1:

Input: S = "havefunonleetcode", K = 5
Output: 6
Explanation:
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
Example 2:

Input: S = "home", K = 5
Output: 0
Explanation:
Notice K can be larger than the length of S. In this case is not possible to find any substring.


Note:

1 <= S.length <= 10^4
All characters of S are lowercase English letters.
1 <= K <= 10^4

 */
package string.medium;

import java.util.HashSet;
import java.util.Set;

public class KLengthSubStrings {

    public int numKLenSubstrNoRepeats(String S, int K) {
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int start = 0;

        for (int end = 0; end < S.length(); end++) {
            while (set.contains(S.charAt(end))) {
                set.remove(S.charAt(start++));
            }
            set.add(S.charAt(end));

            if (end - start + 1 == K) {
                ans++;
                set.remove(S.charAt(start++));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "havefunonl";
        System.out.println(new KLengthSubStrings().numKLenSubstrNoRepeats(s,5));
    }

}


/*

Without using Set :

public int numKLenSubstrNoRepeats(String S, int K) {
        int[] cnt = new int[26];
        int ans = 0;
        for (int hi = 0, lo = 0; hi < S.length(); ++hi) {
            if (++cnt[S.charAt(hi) - 'a'] == 1) {
                if (hi - lo + 1 == K) {
                    ++ans;
                    --cnt[S.charAt(lo++) - 'a'];
                }
            }else {
                do {
                    --cnt[S.charAt(lo) - 'a'];
                } while (S.charAt(hi) != S.charAt(lo++));
            }
        }
        return ans;
    }


 Use cnt to count the chars in sliding window [lo, hi], then loop through the input String S;
Whenever the count of a char increses from 0 to 1, check if the size of the window reaches K;
if yes, found a window met requirement, increase the result by 1, then decrease the low bound lo by 1,
decrease the corresponding cnt element accordingly; if the count of char is not 1, then it must be 2,
then we have to increase the low bound lo, till the window excludes the aforementioned char
( that is, the count of that char decrease from 2 to 1);

 */