/*
Given a list of strings dict where all the strings are of the same length.

Return true if there are 2 strings that only differ by 1 character in the same index, otherwise return false.



Example 1:

Input: dict = ["abcd","acbd", "aacd"]
Output: true
Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.
Example 2:

Input: dict = ["ab","cd","yz"]
Output: false
Example 3:

Input: dict = ["abcd","cccc","abyd","abab"]
Output: true


Constraints:

The number of characters in dict <= 105
dict[i].length == dict[j].length
dict[i] should be unique.
dict[i] contains only lowercase English letters.


Follow up: Could you solve this problem in O(n * m) where n is the length of dict and m is the length of each string.
 */
package string.medium;

import java.util.HashMap;
import java.util.Map;

public class DifferByOneChar {

    public boolean differByOne(String[] dict) {
        Map<Long,String> map = new HashMap<>();
        long mod = (long) Math.pow(10, 11) + 7;

        int len = dict[0].length();
        long[] word2hash = new long[dict.length];
        for (int i = 0; i < dict.length; i++) {
            for (int j = 0; j < len; j++) {
                word2hash[i] += ((long) Math.pow(26,len-j-1)%mod* (dict[i].charAt(j) - 'a'))%mod;
            }
        }

        for (int j =0;j<len;j++) {
            map.clear();
            for (int i = 0; i < dict.length; i++) {
                // newHash can be negative hence adding +mod.
                long newHash = word2hash[i] - ((long) Math.pow(26,len-j-1)%mod* (dict[i].charAt(j) - 'a'))%mod + mod;
                if (map.containsKey(newHash) && diffByOne(dict[i],map.get(newHash))) {
                    return true;
                }
                map.put(newHash,dict[i]);
            }
        }

        return false;
    }

    //check for collision.
    private boolean diffByOne(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }

    public static void main(String[] args) {
        System.out.println(new DifferByOneChar().differByOne(new String[]{"abc","abd"}));
    }

}

/*
O(nm)
First, we compute a hash for each string i in [0, n) as
hash[i] = a[0] * 26 ^ (m - 1) + a[1] * 26 ^ (m - 2) + ... + a[m - 2] * 26 + a[m - 1],
where n is the number of strings, and m - the number of characters in a string.

Then, we go through each character position j in [0, m),
and compute a hash without that character: h = hash[i] - a[j] * 26 ^ (m - j - 1).
We track h in a hash set so we can detect if there is another string with the same hash.
Ideally, we should check for collisions as we are using mod to keep hashes within the integer range.
Since the solution below is accepted, I am keeping it simple here.

Note that in the second loop we are going right-to-left so it's easier to compute 26 ^ (m - j - 1).
Trie:

class Solution {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean end;
        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public boolean differByOne(String[] dict) {
        TrieNode root = buildTrie(dict);
        for (String w : dict) {
            if (search(root, w, 0, 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean search (TrieNode root, String w, int diff, int index) {
        if (diff > 1) {
            return false;
        }

        if (index >= w.length()) {
            return root.end && diff == 1;
        }

        char c = w.charAt(index);
        for (char key: root.children.keySet()) {
            if (key == c ) {
                if (search(root.children.get(key), w, diff, index+1)) {
                    return true;
                }
            } else {
                if (search(root.children.get(key), w, diff+1, index+1)) {
                    return true;
                }
            }
        }
        return false;

    }

    public TrieNode buildTrie(String[] dict) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            TrieNode node = root;
            for (Character c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.end = true;
        }
        return root;
    }
}

O(N * M * M)

if the current node is not the same as our target char and diff == 0 we can divert to a different path,
leading us to basically a new path. In the worst case, we may have to divert to a new path in every node
(but one path can be diverted only once), which means that there might be M different paths we have to check for
and iterating over M paths is O(M * M) Considering we do this for every word (there are N words).
Time complexity becomes O(N * M * M)
for visualization, different paths for a word length 5. every \ means we have to change the character at that node/index.

|     \      |        |       |       |
|      |      \       |       |       |
|      |       |       \      |       |
|      |       |        |      \      |
|      |       |        |       |      \

 */