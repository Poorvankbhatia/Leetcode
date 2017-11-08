/*

Given a list of strings words representing an English Dictionary, find the longest word in words that can be built
 one character at a time by other words in words. If there is more than one possible answer, return the
 longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input:
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation:
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input:
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation:
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].

 */
package hashing.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by poorvank.b on 05/11/17.
 */
public class LongestWord {

    private class StringComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            if(s1.length()>s2.length()) {
                return 1;
            } else if(s2.length()>s1.length()) {
                return -1;
            } else {
                return s2.compareTo(s1);
            }
        }
    }

    public String longestWord(String[] words) {

        Arrays.sort(words,new StringComparator());

        HashSet<String> set = new HashSet<>();

        Collections.addAll(set, words);

        for (int i=words.length-1;i>=0;i--) {
            String w = words[i];
            int c=0;
            for (int j=1;j<=w.length();j++) {
                if(!set.contains(w.substring(0,j))) {
                    break;
                } else {
                    c++;
                }
            }
            if(c==w.length()) {
                return w;
            }
        }

        return "";
    }

    public static void main(String[] args) {
        String[] words =  new String[]{"w","wo","wor","worl", "world"};
        System.out.println(new LongestWord().longestWord(words));
    }

}
