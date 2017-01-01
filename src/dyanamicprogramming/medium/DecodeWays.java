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

        if(s.length()==0) {
            return 0;
        }
        if(s.length()==1) {
            if(s.charAt(0)>'0') {
                return 1;
            } else {
                return 0;
            }
        }

        int size = s.length();
        int[] dp = new int[size+1];

        //dp[i] indicates number of possible decodings for the ith character of the string
        dp[0] = s.charAt(0)=='0'?0:1;
        dp[1] = dp[0];

        for (int i=2;i<=size;i++) {

            dp[i] = 0;

            if(s.charAt(i-1)>'0') {
                dp[i] = dp[i-1];
            }

            if(/*for handling cases like 100*/(s.charAt(i-2)<'2' && s.charAt(i-2)>'0') || (s.charAt(i-2)=='2' && s.charAt(i-1)<'7')) {
                dp[i] += dp[i-2];
            }

        }

        return dp[size];

    }

    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("122"));
    }

}
