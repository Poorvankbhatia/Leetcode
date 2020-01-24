/*

Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:

Input:
["abat","baba","atan","atal"]

Output:
[
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

 */
package trie.hard;

import trie.Trie;

import java.util.*;

/**
 * Created by poorvank.b on 12/06/18.
 */
public class WordSquares {

    Trie<String> trie;
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        trie = new Trie<>();
        for (String word : words) {
            trie.put(word,word);
        }
        int length = words[0].length();
        List<String> list = new ArrayList<>();
        for (String word : words) {
            list.add(word);
            dfs(length,result,list);
            list.remove(list.size()-1);
        }
        return result;
    }

    private void  dfs(int length,List<List<String>> result,List<String> list) {
        if(list.size()==length) {
            result.add(new ArrayList<>(list));
            return;
        }
        int index = list.size();
        StringBuilder newPrefix = new StringBuilder();
        for (String w : list) {
            newPrefix.append(w.charAt(index));
        }
        List<String> nextWords = trie.keysWithPrefix(newPrefix.toString());
        if(nextWords.isEmpty()) {
            return;
        }
        for (String nextWord : nextWords) {
            list.add(nextWord);
            dfs(length, result, list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        String[] input = new String[]{"a"};
        System.out.println(new WordSquares().wordSquares(input));
    }

}


/*

A better approach is to check the validity of the word square while we build it.
Example: ["area","lead","wall","lady","ball"]
We know that the sequence contains 4 words because the length of each word is 4.
Every word can be the first word of the sequence, let's take "wall" for example.
Which word could be the second word? Must be a word start with "a" (therefore "area"), because it has to match the second letter of word "wall".
Which word could be the third word? Must be a word start with "le" (therefore "lead"), because it has to match the third letter of word "wall"
and the third letter of word "area".
What about the last word? Must be a word start with "lad" (therefore "lady"). For the same reason above.


class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String w : words) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null)
                        cur.children[idx] = new TrieNode();
                    cur.children[idx].startWith.add(w);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    return ans;

                cur = cur.children[idx];
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }
}


The only difference between the trie here and the normal trie is that we hold one more list of all
the words which have the prefix (from the root char to the current node char).

 */