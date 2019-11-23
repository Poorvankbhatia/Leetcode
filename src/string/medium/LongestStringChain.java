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
        Arrays.sort(words,Comparator.comparingInt(String::length));
        Map<String,Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        int max=0;
        for(String s : words) {
            int len = util(s,map,set);
            max = Math.max(max,len);
        }
        return max;
    }

    private int util(String t,Map<String,Integer> map,Set<String> set) {
        int max = 0;
        if(map.containsKey(t)) {
            return map.get(t);
        }
        for(int i=0;i<t.length();i++) {
            String x = t.substring(0,i)+t.substring(i+1);
            if(set.contains(x)) {
                max = Math.max(max,util(x,map,set));
            }
        }
        map.put(t,max+1);
        return max+1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestStringChain().longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
    }

}


/*
In each step, remove one letter from this word only if doing so produces another word in the array.

LIS Solution:

public int longestStrChain(String[] words) {
        Arrays.sort(words,Comparator.comparingInt(String::length));
        int i=1;
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        int max=0;
        while(i<n) {
            String a = words[i];
            for(int j=0;j<i;j++) {
                String b = words[j];
                if((a.length()-b.length()>1) || a.equals(b)) {
                    continue;
                }
                if(precedes(words[i],words[j]) && dp[i]<dp[j]+1) {
                    dp[i]=dp[j]+1;
                    System.out.println(words[i]+" "+words[j]+" "+dp[i]);
                }
            }
            max = Math.max(dp[i],max);
            i++;
        }
        return max;
    }

    private boolean precedes(String t,String s) {
        int i=0;
        while (i<s.length()) {
            int pos = t.indexOf(s.charAt(i));
            if(pos==-1){
                return false;
            } else {
                t = t.substring(pos+1);
                i++;
            }
        }
        return true;
    }

 */