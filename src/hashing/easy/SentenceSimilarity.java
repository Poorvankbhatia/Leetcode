/*

Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"],
 ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and
 "good" are not necessarily similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though
 there are no specified similar word pairs.

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20]

 */
package hashing.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by poorvank.b on 26/11/17.
 */
public class SentenceSimilarity {

    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {

        if(words1.length!=words2.length) {
            return false;
        }

        HashMap<String,Set<String>> map = new HashMap<>();

        for (String[] pair : pairs) {
            put(pair[0],pair[1],map);
            put(pair[1],pair[0],map);
        }

        for (int i=0;i<words1.length;i++) {
            String key = words1[i];
            if(words1[i].equals(words2[i])) {
                continue;
            }
            if(map.containsKey(key)) {
                if(!map.get(key).contains(words2[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;

    }

    private void put(String w1,String w2,Map<String,Set<String>> map) {
        if(map.containsKey(w1)) {
            map.get(w1).add(w2);
        } else {
            HashSet<String> set = new HashSet<>();
            set.add(w2);
            map.put(w1,set);
        }
    }

}
