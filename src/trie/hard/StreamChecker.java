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

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    public TrieNode root;
    LinkedList<Character> queue = new LinkedList<>();
    private int size = 0;

    public StreamChecker(String[] words) {
        root = new TrieNode();
        for (String w : words) {
            size = Math.max(w.length(), size);
            // reverse insertion.
            insert(w, root, w.length() - 1);
        }
    }

    private void insert(String word, TrieNode root, int d) {
        char c = word.charAt(d);
        if(root.children[c-'a']==null) {
            root.children[c - 'a'] = new TrieNode();
        }
        if (d > 0) {
            insert(word, root.children[c - 'a'], d-1);
        } else {
            root.children[c - 'a'].isWord = true;
        }
    }

    public boolean query(char letter) {
        queue.addFirst(letter);
        if (queue.size() > size) {
            queue.pollLast();
        }
        TrieNode node = root;
        for (char c : queue) {
            if (node != null) {
                node = node.children[c - 'a'];
                if (node != null && node.isWord) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd"});
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('c'));
        System.out.println(streamChecker.query('d'));
    }

}

