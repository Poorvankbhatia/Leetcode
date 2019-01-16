/*

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"

 */

package dyanamicprogramming.medium;

/**
 * Created by poorvank on 06/12/16.
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        int n = s.length();
        if(n==0) {
            return "";
        }
        boolean[][] table = new boolean[n][n];

        int maxLength = 1;

        //All sub strings of length 1 are palindromes
        for (int i=0;i<n;i++) {
            table[i][i] = true;
        }

        int start=0;
        for (int i=0;i<n-1;i++) {
            if(s.charAt(i)==s.charAt(i+1)) {
                table[i][i+1] = true;
                start = i;
                maxLength=2;
            }
        }


        for (int gap=3;gap<=n;gap++) {

            //If n==4 so i will go up to 1
            for (int i=0;i<=n-gap;i++) {

                int j = i+gap-1;
                /*
                    If characters at the start and end are same then we just need to verify if the middle ones are alo the same
                 */
                if(s.charAt(i)==s.charAt(j) && table[i+1][j-1]) {
                    table[i][j] = true;
                    if(gap>maxLength) {
                        start = i;
                        maxLength=gap;
                    }
                }

            }

        }

        return s.substring(start,start+maxLength);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babab"));
    }

}


/*

Recursive relation:
s.charAt(i)==s.charAt(j) check if s(i+1..j-1)+1

So we calculate palindrome length from smallest substring.

Similar to longest palindromic subsequence:
We maintain a boolean table[n][n] that is filled in bottom up manner.
The value of table[i][j] is true, if the substring is palindrome, otherwise false.
To calculate table[i][j], we first check the value of table[i+1][j-1], if the value is true and str[i] is same as str[j],
then we make table[i][j] true. Otherwise, the value of table[i][j] is made false.

O(n) manacher's solution:

class Solution {
    public String longestPalindrome(String s) {
        if(s==null || s.length()==0) {
            return "";
        }
        String newString = preprocessString(s);
        //System.out.println(newString);
        int[] count = new int[newString.length()];
        int center=0;
        int right=0;
        int maxLen=0;
        int centerIndex=0;
        for(int i=1;i<newString.length()-1;i++) {

            int mirror = (2*center)-i;

            if(i<right) {
                count[i]=Math.min(right-i,count[mirror]);
            }

            while(newString.charAt(i-(count[i]+1))==newString.charAt(i+(count[i]+1))) {
                count[i]++;

            }

            if(i+count[i]>right) {
                center=i;
                right=i+count[i];
            }

            if(count[i]>maxLen) {
                maxLen = count[i];
                centerIndex = i;
            }

        }

       int pos = (centerIndex - 1 - maxLen) / 2;
       return s.substring(pos, pos + maxLen);
  }

  private String preprocessString(String s) {
        if(s==null || s.length()==0) {
            return new String("^$");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("^");
        for(int i=0;i<s.length();i++) {
            sb.append("#").append(s.substring(i,i+1));
        }
        sb.append("#$");
        return sb.toString();
    }


}


Even with the extra while loop inside, it is guaranteed in the worst case the algorithm completes in 2*n steps.

Think of how the i and right edge (R) relates. In the loop each time, you look if this index is a candidate to re-position the palindrome’s center.
If it is, you increment the existing R one at a time. See? R could only be incremented at most N steps. Once you incremented a total of N steps,
it couldn’t be incremented any more. It’s not like you will increment R all the time in the while loop. This is called amortized O(1).

only one step took N/2 steps, so you cannot assume the time taken is N/2 * N, for your case, should be N + ( 1 + 1 + 1…+ N/2), 1+1+1+… = N/2,
so totally N+( N/2 + N/2) = 2N, so clearly O(N) for this alg.


https://www.youtube.com/watch?v=nbTSfrEfo6M

 */