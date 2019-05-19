/*

Given a list of words, each word consists of English lowercase letters.

Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1
to make it equal to word2.  For example, "abc" is a predecessor of "abac".

A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor
of word_2, word_2 is a predecessor of word_3, and so on.

Return the longest possible length of a word chain with words chosen from the given list of words.



Example 1:

Input: ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: one of the longest word chain is "a","ba","bda","bdca".


Note:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of English lowercase letters.

 */
package string.medium;

import java.util.*;

public class LongestStringChain {

    public int longestStrChain(String[] words) {
        Map<String,Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int max = Integer.MIN_VALUE;
        for(String s : words) {
            if(s.length()>1) {
                int len = util(s,map,set);
                max = Math.max(max,len);
            } else {
                map.put(s,1);
            }
        }
        return max;
    }

    private int util(String S,Map<String,Integer> map,Set<String> set) {

        if(map.containsKey(S)) {
            return map.get(S);
        }
        int max=0;
        for(int i=0;i<S.length();i++) {
            String next = S.substring(0,i)+S.substring(i+1);
            if(set.contains(next)) {
                max = Math.max(max,util(next,map,set));
            }
        }
        map.put(S,max+1);
        return max+1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestStringChain().longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
    }

}


/*
In each step, remove one letter from this word only if doing so produces another word in the array.
 */