/*

Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".



 */
package dyanamicprogramming.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank on 31/12/16.
 */
public class WordBreak {

    public boolean wordBreak(String str,Set<String> dictionary) {

        int size = str.length();
        boolean[] validWord = new boolean[size+1];

        validWord[0] = true;

        for (int i=0;i<=size;i++) {
            for (int j=0;j<i;j++) {
                if(!validWord[j]) {
                    continue;
                }
                if(dictionary.contains(str.substring(j,i))) {
                    validWord[i] = true;
                    break;
                }
            }
        }



        return validWord[size];
    }

    public static void main(String[] args) {
        String str =  "cars";
        Set<String> set = new HashSet<>(Arrays.asList("car","ca","rs"));
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak(str,set));
    }

}
