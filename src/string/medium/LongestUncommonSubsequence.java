/*

Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined
as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining
elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon
subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3
Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].

 */
package string.medium;

/**
 * Created by poorvank.b on 02/04/17.
 */
public class LongestUncommonSubsequence {

    public int findLUSlength(String[] strs) {

        if(strs==null || strs.length==0) {
            return -1;
        }

        if(strs.length==1) {
            return strs[0].length();
        }

        int n = strs.length;

        int maxlen = -1;
        for (int i = 0; i < n; i++) {
            boolean unique = true;
            for (int j = 0; j < n; j++) {
                if (i != j && isSubSequence(strs[i].toCharArray(), strs[j].toCharArray())) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                maxlen = Math.max(maxlen,strs[i].length());
            }
        }

        return maxlen;

    }

    // Check if str1 is a subsequence of str 2
    private boolean isSubSequence(char str1[], char str2[]) {
        int j = 0;
        int m = str1.length;
        int n = str2.length;
        for (int i=0; i<n&&j<m; i++)
            if (str1[j] == str2[i])
                j++;
        return (j==m);
    }

    public static void main(String[] args) {

        String[] arr = new String[]{"aabbcc", "aabbcc","cb","abc"};
        System.out.print(new LongestUncommonSubsequence().findLUSlength(arr));

    }

}


/*

bool isSubSequence(char str1[], char str2[], int m, int n)
{
    // Base Cases
    if (m == 0) return true;
    if (n == 0) return false;

    // If last characters of two strings are matching
    if (str1[m-1] == str2[n-1])
        return isSubSequence(str1, str2, m-1, n-1);

    // If last characters are not matching
    return isSubSequence(str1, str2, m, n-1);
}

 */