/*

Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of
only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation
cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]


Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.

 */

package trie.hard;

import trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank.b on 15/03/17.
 */
public class WordAbbreviation {

    public List<String> wordsAbbreviation(List<String> dict) {

        HashMap<String,List<Integer>> map = new HashMap<>();

        List<String> result = new ArrayList<>(dict.size());

        for (int i=0;i<dict.size();i++) {
            result.add("");
        }

        for (int i=0;i<dict.size();i++) {
            String s = dict.get(i);
            if(s.length()>3) {
                String abbreviation = s.charAt(0)+""+(s.length()-2)+s.charAt(s.length()-1);
                List<Integer> list;
                if(!map.containsKey(abbreviation)) {
                    list = new ArrayList<>();
                } else {
                    list = map.get(abbreviation);
                }
                list.add(i);
                map.put(abbreviation,list);
            } else {
                result.set(i,s);
            }
        }

        for (String key : map.keySet()) {

            List<Integer> list = map.get(key);

            if(list.size()==1) {
                result.set(list.get(0),key);
            }
            else {
                Trie<Integer> trie = new Trie<>();
                for (Integer index : list) {
                    String s = dict.get(index);
                    trie.putWithFrequency(s,1);
                }
                for (Integer index : list) {
                    String abbreviation = trie.getAbbreviatedString(dict.get(index),1);
                    result.set(index,abbreviation);
                }
            }

        }

        return result;

    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("like");
        list.add("god");
        list.add("internal");
        list.add("me");
        list.add("internet");
        list.add("interval");
        list.add("intension");
        list.add("face");
        list.add("intrusion");

        System.out.println(new WordAbbreviation().wordsAbbreviation(list));

    }

}

/*

Club all the words with same abbreviation and then use to trie to differentiate.
Same concept as shortest common prefix

The time complexity will be O(nL) for building trie, O(nL) to resolve conflicts, O(n) to group words.
 So the time complexity will be O(n(2L + 1). n is the number of words, and L is the average length of each words.

 */