/*

Given a string S, return the number of substrings that have only one distinct letter.



Example 1:

Input: S = "aaaba"
Output: 8
Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
"aaa" occurs 1 time.
"aa" occurs 2 times.
"a" occurs 4 times.
"b" occurs 1 time.
So the answer is 1 + 2 + 4 + 1 = 8.
Example 2:

Input: S = "aaaaaaaaaa"
Output: 55


Constraints:

1 <= S.length <= 1000
S[i] consists of only lowercase English letters.


 */
package string.easy;

public class CountSubStringWithOnlyOneDistinctChar {

    public int countLetters(String S) {
        int[] count = new int[S.length()];
        count[0]=1;
        for(int i=1;i<S.length();i++) {
            if(S.charAt(i)==S.charAt(i-1)) {
                count[i]=count[i-1]+1;
            } else {
                count[i]=1;
            }
        }

        int sum=0;
        for(int i=0;i<S.length();i++) {
            sum+=count[i];
        }

        return sum;
    }

}
