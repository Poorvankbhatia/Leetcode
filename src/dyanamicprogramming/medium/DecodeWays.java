/*

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 01/01/17.
 */
public class DecodeWays {


    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;
        for(int i=2;i<=s.length();i++) {
            if(s.charAt(i-1)>'0' && s.charAt(i-1)<='9') {
                dp[i]+=dp[i-1];
            }
            if(s.charAt(i-2)=='1' || (s.charAt(i-1)<='6' && s.charAt(i-2)=='2')) {
                dp[i]+=dp[i-2];
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("100"));
    }

}

/*

Another method :

public int numDecodings(String s) {
        if(s==null || s.length()==0) {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n+1];

        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;

        for(int i=1;i<n;i++) {

            dp[i+1]=s.charAt(i)!='0'?dp[i]:0;
            if(s.charAt(i-1)=='1' || (s.charAt(i-1)=='2' && s.charAt(i)<='6')) {
                dp[i+1]=dp[i+1]+dp[i-1];
            }

        }

        return dp[n];

    }


  public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        // Empty string can be decoded in one way.
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            // If the last digit is non-zero, recur for remaining (n-1) digits and add the result to total count.
            if(first >= 1 && first <= 9) {
                dp[i] += dp[i-1];
            }
            //If the last two digits form a valid character (or smaller than 27), recur for remaining (n-2)
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
 */