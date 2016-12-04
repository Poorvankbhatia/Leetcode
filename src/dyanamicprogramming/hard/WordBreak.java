/*

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

 */

package dyanamicprogramming.hard;

import java.util.*;

/**
 * Created by poorvank on 28/08/16.
 */
@SuppressWarnings("unchecked")
public class WordBreak {

    public List<String> wordBreak(String s, Set<String> wordDict) {

        ArrayList<String>[] dp = new ArrayList[s.length()+1];
        processDictionary(wordDict,dp,s);
        List<String> result = new ArrayList<>();
        if(dp[s.length()]==null) {
            return result;
        }

        wordBreakUtil(dp,s.length(),result,new ArrayList<>());
        return result;
    }

    private void wordBreakUtil(ArrayList<String>[] dp,int length,List<String> result,List<String> list) {

        if(length<0) {
            return;
        }

        if(length==0) {

            StringBuilder sb = new StringBuilder();
            for (int i=list.size()-1;i>=0;i--) {
                sb.append(list.get(i));
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            result.add(sb.toString());
            System.out.println(sb.reverse().toString());
            return;

        }

        for (String word : dp[length]) {

            list.add(word);
            wordBreakUtil(dp, length - word.length(), result, list);
            list.remove(list.size()-1);

        }


    }


    private void processDictionary(Set<String> wordDict, ArrayList<String>[] dp,String s) {

        boolean[] validate = new boolean[s.length()+1];
        validate[0] = true;

        for (int i=0;i<s.length();i++) {

            if(!validate[i]) {
                continue;
            }

            for (String word: wordDict) {

                int len = word.length();

                if(i+len>s.length()) {
                    continue;
                }

                String sub = s.substring(i,i+len);
                if(sub.equals(word))  {
                    if(dp[i+len]==null) {
                        dp[i+len] = new ArrayList<>();
                    }
                    dp[i+len].add(word);
                    validate[i+len] = true;
                }
            }
        }


    }

    public static void main(String[] args) {
        String str =  "aaaaaaa";
        Set<String> set = new HashSet<>(Arrays.asList("aaaa","aa","a"));
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak(str,set));
    }

}
