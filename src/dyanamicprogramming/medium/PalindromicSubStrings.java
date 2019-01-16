/*

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.


 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 26/07/17.
 */
public class PalindromicSubStrings {

    public int countSubstrings(String s) {

        if(null==s || s.length()==0) {
            return 0;
        }

        int result = 0;
        int n = s.length();
        boolean[][] dpTable = new boolean[n][n];

        for (int i=0;i<n;i++) {
            dpTable[i][i] =true;
            result++;
        }

        for (int i=0;i<n-1;i++) {
            if(s.charAt(i)==s.charAt(i+1)) {
                dpTable[i][i+1]=true;
                result++;
            }
        }

        for (int gap=3;gap<=n;gap++) {

            for (int i=0;i<=n-gap;i++) {

                int j = i+gap-1;

                if(s.charAt(i)==s.charAt(j) && dpTable[i+1][j-1]) {
                    result++;
                    dpTable[i][j] = true;
                }

            }

        }

        return result;

    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(new PalindromicSubStrings().countSubstrings(s));
    }

}

/*

Same concept as Longest Palindromic Substring

Using manacher's algorithm:

public int countSubstrings(String s) {
        if(s==null || s.length()==0) {
            return 0;
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

        }

       int sum=0;
        //Use count array to get sum。example,if p[i] = 5,then we can get three palindromes with length 1,3,5 respectively,that is to say (1 + 5) / 2.
        for(int i : count) sum += (1 + i) / 2;
        return sum;
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

 */
