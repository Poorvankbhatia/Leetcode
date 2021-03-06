/*

Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"],
["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 26/11/17.
 */
public class SentenceSimilarity2 {

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {

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
            if(!dfs(map,key,words2[i],new HashSet<String>())) {
                return false;
            }
        }

        return true;

    }

    private boolean dfs(Map<String,Set<String>> map,String w1,String w2,Set<String> set) {

        if(map.containsKey(w1) && map.get(w1)!=null && !map.get(w1).isEmpty()) {
            set.add(w1);
            for (String key : map.get(w1)) {
                if(!set.contains(key)) {
                    if(key.equals(w2) || dfs(map,key,w2,set)) {
                        return true;
                    }
                }
            }
        }

        return false;

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

    public static void main(String[] args) {
        String[] s1 = {"an","extraordinary","meal"};
        String[] s2 = {"one","good","dinner"};
        String[][] pairs = {{"great","good"},{"extraordinary","good"},{"well","good"},{"wonderful","good"},{"excellent","good"},{"fine","good"},{"nice","good"},{"any","one"},{"some","one"},{"unique","one"},{"the","one"},{"an","one"},{"single","one"},{"a","one"},{"truck","car"},{"wagon","car"},{"automobile","car"},{"auto","car"},{"vehicle","car"},{"entertain","have"},{"drink","have"},{"eat","have"},{"take","have"},{"fruits","meal"},{"brunch","meal"},{"breakfast","meal"},{"food","meal"},{"dinner","meal"},{"super","meal"},{"lunch","meal"},{"possess","own"},{"keep","own"},{"have","own"},{"extremely","very"},{"actually","very"},{"really","very"},{"super","very"}};
        System.out.println(new SentenceSimilarity2().areSentencesSimilarTwo(s1,s2,pairs));
    }

}
