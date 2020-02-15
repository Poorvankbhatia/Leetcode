/*
Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.

Return the minimum number of steps to make t an anagram of s.

An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.



Example 1:

Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
Example 2:

Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
Example 3:

Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams.
Example 4:

Input: s = "xxyyzz", t = "xxyyzz"
Output: 0
Example 5:

Input: s = "friend", t = "family"
Output: 4


Constraints:

1 <= s.length <= 50000
s.length == t.length
s and t contain lower-case English letters only.
 */
package string.medium;

public class MakeStringAnagrams {

    public int minSteps(String s, String t) {

        int n = s.length();
        int[] count = new int[26];
        for (int i=0;i<n;i++) {
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        int sum=0;
        for (int i=0;i<26;i++) {
            if(count[i]>0) {
                sum+=count[i];
            }
        }
        return sum;
    }

}

/*

Another one:

public int minSteps(String s, String t) {
        int[] sCount = new int[26];
        int[] tCount = new int[26];

        for (char c : s.toCharArray()) {
            sCount[c-'a']++;
        }

        for (char c : t.toCharArray()) {
            tCount[c-'a']++;
        }

        int sum = 0;

        for (int i=0;i<26;i++) {
            sum+=Math.abs(tCount[i]-sCount[i]);
        }

        return sum/2;
    }

 */