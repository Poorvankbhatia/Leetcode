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
            List<String> keyWithWordAsPrefix = trie.keysWithPrefix(word);
            //Keys which are prefix of this word
            List<String> prefixKeysOfWord = trie.allPrefixKeys(word);

            for (String key : keyWithWordAsPrefix) {
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

/*

Time Complexity : O(k2*n)

There were 2 major steps to the algorithm. Firstly, we needed to build the Trie. Secondly, we needed to look up each word in the Trie.

Inserting each word into the Trie takes O(k) time. As well as inserting the word, we also checked at each letter
whether or not the remaining part of the word was a palindrome. These checks had a cost of O(k), and with k of them,
gave a total cost of O(k^2). With n words to insert, the total cost of building the Trie was therefore O(k^2 n)
Checking for each word in the Trie had a similar cost.
Each time we encountered a node with a word ending index, we
needed to check whether or not the current word we were looking up had a
palindrome remaining. In the worst case, we'd have to do this k times at a cost of k for each time.
So like before, there is a cost of k^2

This is the same as for the hash table approach.

Space Complexity : O((k + n)^2)O((k+n)

 */