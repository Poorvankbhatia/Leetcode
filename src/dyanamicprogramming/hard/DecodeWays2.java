package dyanamicprogramming.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 12/08/17.
 */
public class DecodeWays2 {

    int mod = (int) Math.pow(10,9)+7;

    public int numDecodings(String s) {

        if(s==null || s.length()==0) {
            return 0;
        }
        int n  = s.length();
        long[] dp = new long[n+1];

        dp[0] = 1;
        if(s.charAt(0)==0) {
            dp[1] = 0;
        } else if(s.charAt(0)=='*') {
            dp[1] = 9;
        } else {
            dp[1] = 1;
        }

        for (int i=2;i<=n;i++) {
            if (s.charAt(i-1) == '0') {
                if (s.charAt(i-2) == '1' || s.charAt(i -2) == '2') {
                    dp[i] = dp[i-2];
                }
                else if(s.charAt(i-2) == '*'){
                    dp[i] = 2*dp[i-2];
                }
                else {
                    return 0;
                }
            }
            else if(s.charAt(i-1) >= '1' && s.charAt(i-1) <= '9') {
                dp[i] = dp[i-1];
                if (s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6') ){
                    dp[i] = (dp[i] + dp[i-2]) % mod;
                }
                else if (s.charAt(i-2) == '*') {
                    if (s.charAt (i-1) <= '6') {
                        dp[i] = (dp[i] + dp[i-2] * 2) % mod;
                    }
                    else {
                        dp[i] = (dp[i] + dp[i-2]) % mod;
                    }
                }
            }
            else { //s.charAt(i-1) == '*'
                dp[i] = (9*dp[i-1]) % mod;
                if ( s.charAt(i-2) == '1' ){ // 11 - 19
                    dp[i] = ( dp[i] + 9 * dp[i-2] ) % mod;
                }
                else if (s.charAt(i-2) == '2') { // 21 - 26
                    dp[i] = ( dp[i] + 6 * dp[i-2] ) % mod;
                }
                else if (s.charAt(i - 2) == '*') {
                    dp[i] = ( dp[i] + 15 * dp[i-2] ) % mod;
                }
            }
        }

        return (int)dp[n];

    }

}

/*

ake "1*" as an example. It can be "11"-"19". You can read it into "A" and "A" to "A" and "I" or you can treat it as a two digit number as
 "K" to "S". That is the way you decode the code.
So if you treat the current character s.charAt(i) as a single character, the dp result of the index i should be the number of choices of
 current character times dp[i-1]. If you put the s.charAt(i) together with s.charAt(i-1) as a two digit number (if possible, something like "29"
  is not a possible pair), dp[i] should add the choices of the current pair times dp[i-2]. That is basic thinking of dp for this problem.
Then what should be done is to find what kind of pairs or single numbers are valid. Like a single "0" is not valid, and there is no answer
for something like "30".

 */