/*

Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

 */

package string.hard;

/**
 * Created by poorvank.b on 19/03/17.
 */
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {

        if(s==null || s.length()==0) {
            return s;
        }

        String rev = new StringBuilder(s).reverse().toString();

        // use a special character to prevent overlap
        String biggerPalindrome = s+"+"+rev;

        int[] lps = computeLPS(biggerPalindrome);

        if(rev.length()>=lps[biggerPalindrome.length()-1]) {
            String extraChar = rev.substring(0,rev.length()-lps[biggerPalindrome.length()-1]);
            return extraChar+s;
        } else {
            return s;
        }

    }

    private int[] computeLPS(String s) {

        int[] lps = new int[s.length()];
        lps[0] = 0;
        int i=1,j=0;

        while (i<s.length()) {

            if(s.charAt(i)==s.charAt(j)) {
                lps[i] = j+1;
                i++;
                j++;
            } else {
                if(j!=0) {
                    j = lps[j-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }

        }

        return lps;

    }

    public static void main(String[] args) {

        String s = "aaba";
        System.out.print(new ShortestPalindrome().shortestPalindrome(s));

    }

}

/*

So given a string, we can make it a palindrome by appending reverse of string to it.
But it will contain some extra characters those characters can be removed.

Those are the characters common to suffix of reverse and prefix of orgnal string.
For example:

aaba - > rev - > abaa

aa can be removed. Hence we are left with abaaba

See  this : https://www.youtube.com/watch?v=c4akpqTwE5g

https://www.youtube.com/watch?v=tWDUjkMv6Lc

 */