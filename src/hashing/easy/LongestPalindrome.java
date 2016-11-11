/*

Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that
can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

 */
package hashing.easy;

import java.util.HashMap;

/**
 * Created by poorvank.b on 08/11/16.
 */
public class LongestPalindrome {

    public int longestPalindrome(String s) {

        HashMap<Character,Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            if(map.containsKey(c)) {
                map.put(c,map.get(c)+1);
            } else {
                map.put(c,1);
            }
        }

        int maxOdd = 0;
        char maxOddChar = Character.MIN_VALUE;
        int palindromeLength = 0;

        for (Character c : map.keySet()) {
            if(map.get(c)%2==0) {
                palindromeLength += map.get(c);
            } else {
                if(maxOdd<map.get(c)) {
                    maxOdd = map.get(c);
                    maxOddChar = c;
                }
            }
        }

        palindromeLength += maxOdd;
        map.remove(maxOddChar);

        for (Character c : map.keySet()) {
            if(map.get(c)%2!=0) {
                palindromeLength += map.get(c)-1;
            }
        }


        return palindromeLength;

    }

    public static void main(String[] args) {
        System.out.print(new LongestPalindrome().longestPalindrome("bb"));
    }

}
