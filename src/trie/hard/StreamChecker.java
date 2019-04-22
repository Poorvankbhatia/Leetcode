/*

Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest,
including this letter just queried) spell one of the words in the given list.


Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist


Note:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.


 */
package trie.hard;

import java.util.*;

public class StreamChecker {

    class TrieNode {
        boolean isWord;
        TrieNode[] next = new TrieNode[26];
    }

    TrieNode root = new TrieNode();
    private Deque<Character> queue;
    private int size=0;


    public StreamChecker(String[] words) {
        queue = new LinkedList<>();
        createTrie(words);
    }

    public boolean query(char letter) {
        queue.addFirst(letter);
        if(queue.size()>size) {
            queue.removeLast();
        }
        TrieNode node = root;
        for (char c : queue) {
            if(node!=null) {
                node = node.next[c - 'a'];
                if (node != null && node.isWord) {
                    return true;
                }
            }
        }
        return false;
    }

    private void createTrie(String[] words) {
        for (String s : words) {
            size = Math.max(s.length(),size);
            TrieNode node = root;
            int len = s.length();
            for (int i = len - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.isWord = true;
        }
    }

}
