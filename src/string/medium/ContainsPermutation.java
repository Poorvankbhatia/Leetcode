/*

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words,
one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False


 */
package string.medium;

/**
 * Created by poorvank on 30/04/17.
 */
public class ContainsPermutation {

    private int max = 26;

    private boolean compare(int[] patternCount,int[] textCount) {
        for (int i=0;i<max;i++) {
            if(patternCount[i]!=textCount[i]) {
                return false;
            }
        }

        return true;
    }

    public boolean checkInclusion(String p, String s) {



        if(p.length()>s.length()) {
            return false;
        }

        int[] patternCount = new int[max];
        int[] textCount = new int[max];

        for (int i=0;i<p.length();i++) {
            patternCount[p.charAt(i)-'a']++;
            textCount[s.charAt(i)-'a']++;
        }

        for (int i=p.length();i<s.length();i++) {

            if(compare(patternCount,textCount)) {
                return true;
            }

            textCount[s.charAt(i-p.length())-'a']--;
            textCount[s.charAt(i)-'a']++;

        }

        return compare(patternCount, textCount);


    }

    public static void main(String[] args) {
        String s = "eidlbkaooo";
        String p = "ab";

        System.out.println(new ContainsPermutation().checkInclusion(p,s));
    }


}
