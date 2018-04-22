/*

Given a list of words, we may encode it by writing a reference string S and a list of indexes A.

For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].

Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.

What is the length of the shortest reference string S possible that encodes the given words?

Example:

Input: words = ["time", "me", "bell"]
Output: 10
Explanation: S = "time#bell#" and indexes = [0, 2, 5].
Note:

1 <= words.length <= 2000.
1 <= words[i].length <= 7.
Each word has only lowercase letters.

 */
package trie.medium;

import trie.Trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 22/04/18.
 */
public class ShortestEncodingWords {

    public int minimumLengthEncoding(String[] words) {

        Trie<Integer> trie = new Trie<>();
        Map<String,String> map = new HashMap<>();

        for (int i=0;i<words.length;i++) {
           //To avoid same inputs
           if(!map.containsKey(words[i])) {
               StringBuilder sb = new StringBuilder(words[i]);
               sb.reverse();
               map.put(words[i],sb.toString());
               trie.put(sb.toString(),i);
           }
        }

        int length = 0;

        for (String key : map.keySet()) {

            List<String> keysWithWordAsPrefix = trie.keysWithPrefix(map.get(key));

            if(keysWithWordAsPrefix.size()==1 && keysWithWordAsPrefix.get(0).equals(map.get(key))) {
                length+=key.length()+1;
            }

        }

        return length;

    }

    public static void main(String[] args) {
        String[] words = new String[]{"time", "time", "time"};
        System.out.println(new ShortestEncodingWords().minimumLengthEncoding(words));
    }

}

/*

Another method :

  public int minimumLengthEncoding(String[] words) {
        Set<String> s = new HashSet<>(Arrays.asList(words));
        for (String w : words)
            for (int i = 1; i < w.length(); ++i)
                s.remove(w.substring(i));
        int res = 0;
        for (String w : s) res += w.length() + 1;
        return res;
    }

    Time complexity will be O(NK^2).

 */