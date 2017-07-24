/*

Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the
two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]


 */
package trie.hard;

import trie.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 16/10/16.
 */
public class PalindromePairs {

    private Trie<Integer> trie;

    public List<List<Integer>> palindromePairs(String[] words) {

        trie = new Trie<>();
        StringBuilder sb;
        for (int i=0;i<words.length;i++) {
            sb = new StringBuilder(words[i]).reverse();
            trie.put(sb.toString(),i);
        }

        List<List<Integer>> result = new ArrayList<>();

        /*

        For every word we simply find all the words(Keys) whose prefix is that word &
        all the words(Keys) which are the current word prefixes

         */

        for (int i=0;i<words.length;i++) {

            String word = words[i];
            //Keys whose prefix is this word
            List<String> keyWithWordPrefix = trie.keysWithPrefix(word);
            //Keys which are prefix of this word
            List<String> prefixKeysOfWord = trie.allPrefixKeys(word);

            for (String key : keyWithWordPrefix) {
                int keyVal = trie.get(key);
                List<Integer> list = new ArrayList<>();
                if(!word.equals(key)) {
                    boolean isPal = isPalindrome(key.substring(word.length()));
                    if(isPal) {
                        list.add(i);
                        list.add(keyVal);
                    }
                } else {
                    // for cases when we have single character strings
                    if(keyVal!=i) {
                        list.add(i);
                        list.add(keyVal);
                    }
                }
                if(list.size()!=0) {
                    result.add(list);
                }
            }

            for (String prefixKey : prefixKeysOfWord) {
                List<Integer> list = new ArrayList<>();
                if(!word.equals(prefixKey)) {
                    boolean isPal = isPalindrome(word.substring(prefixKey.length()));
                    if(isPal) {
                        list.add(i);
                        list.add(trie.get(prefixKey));
                    }
                }
                if(list.size()!=0) {
                    result.add(list);
                }
            }


        }

        return result;


    }


    private boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return s.equals(sb.reverse().toString());
    }


    public static void main(String[] args) {
        String[] arr = new String[]{"abcd", "dcba", "lls", "s", "sssll","ll","cba"};
        System.out.println(new PalindromePairs().palindromePairs(arr));
    }

}
