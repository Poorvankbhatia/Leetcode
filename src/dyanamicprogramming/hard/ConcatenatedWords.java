/*

Given a list of words, please write a program that returns all concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".

 */

package dyanamicprogramming.hard;


import java.util.*;

/**
 * Created by poorvank on 18/12/16.
 */
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Set<String> usedWords = new HashSet<>();
        for(String word : words) {
            if(isConcatenated(word,usedWords)) {
                result.add(word);
            }
            usedWords.add(word);
        }
        return result;
    }

    private boolean isConcatenated(String word,Set<String> usedWords) {
        if(usedWords.size()==0) {
            return false;
        }
        boolean[] dp = new boolean[word.length()+1];
        dp[0]=true;
        for(int i=1;i<=word.length();i++) {
            for (int j=0;j<i;j++) {
                if(dp[j]) {
                    String sub = word.substring(j,i);
                    if(usedWords.contains(sub)) {
                        dp[i]=true;
                        break;
                    }
                }
            }
        }
        return dp[word.length()];
    }

    public static void main(String[] args) {
        String[] words = new String[]{"cat", "cats", "catsdogcats", "catxdogcatsrat", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcat", "ratcatdog", "ratcatdogcat"};
        System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(words));
    }

}


/*

SIMILAR TO WORD BREAK

Faster Sol:

public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(int i = 0; i < words.length; i++) {
            if(words[i].length() != 0 && helper(set, words[i])) {
                res.add(words[i]);

            }
            set.add(words[i]);
        }
        return res;
    }

    public boolean helper(Set<String> set, String word) {
        if(word == null || word.length() == 0) {
            return true;
        }
        if(set.contains(word)) return true;
        int len = word.length();
        for(int i = 1; i <= len; i++) {
            String temp = word.substring(0, i);
            if(set.contains(temp)) {
               if(helper(set, word.substring(i))) {
                   set.add(word);
                   return true;
               }
            }
        }
        return false;
    }


    Time is O(N * L^3) where L is the word length if we count the time to compute hashcode.

 */