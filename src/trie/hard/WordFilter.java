/*

Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix).
It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:
Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
Note:
words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.

 */

package trie.hard;

import trie.Trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordFilter {

    private Trie<Integer> trie;

    Map<String,Integer> map;

    public WordFilter(String[] words) {
        trie = new Trie<>();
        map=new HashMap<>();;
        for (int i=0;i<words.length;i++) {
            trie.put(words[i],i);
            map.put(words[i],i);
        }
    }

    public int f(String prefix, String suffix) {
        if(prefix==null || suffix==null) {
            return -1;
        }
        List<String> allWordsWithPrefix = trie.keysWithPrefix(prefix);
        int max=-1;

        for (String word : allWordsWithPrefix) {
            if(word.endsWith(suffix)) {
                max = Math.max(max,map.get(word));
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"hwxekxrbst","xtmkmgencg","odsdjhjjkk","wxaxuswqxc","rmurhkmuze","kgphhwokcm",
                "lvoapqeppp","pcpsdhfcsh","alztysttkq","nhfttbpzwf"};
        System.out.println(new WordFilter(arr).f("alztysttkq","tkq"));
        System.out.println(new WordFilter(arr).f("al","ysttkq"));
        System.out.println(new WordFilter(arr).f("xtmkmgencg","mkmgencg"));
        System.out.println(new WordFilter(arr).f("nhfttbpzwf","fttbpzwf"));
        System.out.println(new WordFilter(arr).f("kgph","hhwokcm"));
        System.out.println(new WordFilter(arr).f("alztystt",""));
    }

}

/*

Time Complexity: O(NK+QK) where N is the number of words, K is the maximum length of a word, and Q is the number of queries.
https://leetcode.com/articles/prefix-and-suffix-search/

 */