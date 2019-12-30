/*

SEE THE DP METHOD FOR BETTER IMPLEMENTATION

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

 */

package backtracking.hard;

import java.util.*;

/**
 * Created by poorvank on 28/08/16.
 */
public class WordBreak {

    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<>();
        wordBreakUtil(s,wordDict,result,new StringBuilder(),0,"");
        return result;
    }

    private void wordBreakUtil(String s,Set<String> wordDict,List<String> result,
                               StringBuilder sb,int startIndex,String word) {

        sb.append(word);
        if(startIndex==s.length()) {
            result.add(sb.toString().trim());
            return;
        }
        for (String w: wordDict) {
            int len = w.length();
            if((startIndex+len)>s.length()) {
                continue;
            }
            String sub = s.substring(startIndex,startIndex+len);
            if(sub.equals(w)) {
                wordBreakUtil(s,wordDict,result,sb,startIndex+len,(w+" "));
                sb.setLength(sb.length()-(w.length()+1));
            }
        }
    }

    public static void main(String[] args) {
        String str =  "samsungandmango";
        Set<String> set = new HashSet<>(Arrays.asList("mobile", "samsung", "sam", "sung", "man", "mango",
                "icecream", "and", "go", "i", "like", "ice", "cream"));
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak(str,set));

    }

}
