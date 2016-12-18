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

    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());

        for (String word : words) {
            if (possibleFormation(word, preWords)) {
                result.add(word);
            }
            preWords.add(word);
        }

        return result;
    }

    private static boolean possibleFormation(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

    public static void main(String[] args) {
        String[] words = new String[]{"reattempt","ed","attempt","reattempted","re"};

        System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(words));
    }

}


/*

SIMILAR TO WORD BREAK

 */