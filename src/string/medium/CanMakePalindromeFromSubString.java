/*

Given a string s, we make queries on substrings of s.

For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right],
and then choose up to k of them to replace with any lowercase English letter.

If the substring is possible to be a palindrome string after the operations above, the result of the query is true.
Otherwise, the result is false.

Return an array answer[], where answer[i] is the result of the i-th query queries[i].

Note that: Each letter is counted individually for replacement so if for example s[left..right] = "aaa", and k = 2,
we can only replace two of the letters.  (Also, note that the initial string s is never modified by any query.)



Example :

Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
Output: [true,false,false,true,true]
Explanation:
queries[0] : substring = "d", is palidrome.
queries[1] : substring = "bc", is not palidrome.
queries[2] : substring = "abcd", is not palidrome after replacing only 1 character.
queries[3] : substring = "abcd", could be changed to "abba" which is palidrome. Also this can be changed to
"baab" first rearrange it "bacd" then replace "cd" with "ab".
queries[4] : substring = "abcda", could be changed to "abcba" which is palidrome.


Constraints:

1 <= s.length, queries.length <= 10^5
0 <= queries[i][0] <= queries[i][1] < s.length
0 <= queries[i][2] <= s.length
s only contains lowercase English letters.


 */
package string.medium;

import java.util.ArrayList;
import java.util.List;

public class CanMakePalindromeFromSubString {


    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {

        int[][] frequencyCount = new int[s.length()+1][26];
        for (int i = 0; i < s.length(); i++) {
            frequencyCount[i + 1] = frequencyCount[i].clone();
            frequencyCount[i + 1][s.charAt(i) - 'a']++;
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] q : queries) {
            int oddCount = 0;
            for (int i = 0; i < 26; ++i) {
                    if((frequencyCount[q[1]+1][i]-frequencyCount[q[0]][i])%2!=0) {
                        oddCount++;
                    }
            }
            // Minimum no of replace  =  total characters with odd count/2.
            result.add(oddCount / 2 <= q[2]);
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "abcda";
        int[][] queries = new int[][]{
                {0,4,1}
        };
        System.out.println(new CanMakePalindromeFromSubString().canMakePaliQueries(s,queries));
    }

}

/*

Bit method:

public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        int[] odds = new int[s.length() + 1];
        // odds[i]: within range [0...i) of s, the jth bit of odd[i] indicates even/odd of the count of (char)(j + 'a').
        for (int i = 0; i < s.length(); ++i)
            odds[i + 1] = odds[i] ^ 1 << s.charAt(i) - 'a';
        for (int[] q : queries)
            ans.add(Integer.bitCount(odds[q[1] + 1] ^ odds[q[0]]) / 2 <= q[2]);
            // odds[q[1] + 1] ^ odds[q[0]] indicates the count of (char)(i + 'a') in substring(q[0], q[1] + 1) is even/odd.
        return ans;
    }

 */