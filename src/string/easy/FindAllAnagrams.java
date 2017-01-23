/*

Find All Anagrams in a String 
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

 */

package string.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 07/11/16.
 */
public class FindAllAnagrams {

    private int max = 26;

    private boolean compare(int[] patternCount,int[] textCount) {
        for (int i=0;i<max;i++) {
            if(patternCount[i]!=textCount[i]) {
                return false;
            }
        }

        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> list = new ArrayList<>();

        if(p.length()>s.length()) {
            return list;
        }

        int[] patternCount = new int[max];
        int[] textCount = new int[max];

        for (int i=0;i<p.length();i++) {
            patternCount[p.charAt(i)-'a']++;
            textCount[s.charAt(i)-'a']++;
        }

        for (int i=p.length();i<s.length();i++) {

            if(compare(patternCount,textCount)) {
                list.add(i-p.length());
            }

            textCount[s.charAt(i-p.length())-'a']--;
            textCount[s.charAt(i)-'a']++;

        }

        if(compare(patternCount,textCount)) {
            list.add(s.length()-p.length());
        }


        return list;
    }

    public static void main(String[] args) {
        System.out.println(new FindAllAnagrams().findAnagrams("abcba","ab"));
    }

}


//Refer Rabin Karp
