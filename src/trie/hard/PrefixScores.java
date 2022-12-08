/*

You are given an array words of size n consisting of non-empty strings.

We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].

For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of
both "ab" and "abc".
Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].

Note that a string is considered as a prefix of itself.



Example 1:

Input: words = ["abc","ab","bc","b"]
Output: [5,4,3,2]
Explanation: The answer for each string is the following:
- "abc" has 3 prefixes: "a", "ab", and "abc".
- There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
The total is answer[0] = 2 + 2 + 1 = 5.
- "ab" has 2 prefixes: "a" and "ab".
- There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
The total is answer[1] = 2 + 2 = 4.
- "bc" has 2 prefixes: "b" and "bc".
- There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
The total is answer[2] = 2 + 1 = 3.
- "b" has 1 prefix: "b".
- There are 2 strings with the prefix "b".
The total is answer[3] = 2.
Example 2:

Input: words = ["abcd"]
Output: [4]
Explanation:
"abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists of lowercase English letters.

 */
package trie.hard;
import java.util.Arrays;

public class PrefixScores {


    private static class Node {
        private final Node[] children = new Node[26];
        private int freq;
    }

    private static class Trie {
        Node root;
        public Trie() {
            this.root = new Node();
        }
        public void put(String word) {
            put(word,0,root);
        }
        private Node put(String word,int d,Node x) {
            if(x==null) {
                x = new Node();
                x.freq = 1;
            } else if (x!=root) {
                x.freq++;
            }
            if(d==word.length()) {
                return x;
            }
            char c = word.charAt(d);
            x.children[c- 'a'] = put(word,d+1,x.children[c-'a']);
            return x;
        }
        public int get(String word) {
            return get(word,root,0);
        }
        private int get(String word,Node x,int d) {
            if(d==word.length()) return x.freq;
            char c = word.charAt(d);
            int sum = x.freq;
            return sum+get(word,x.children[c-'a'],d+1);
        }
    }

    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (String word: words) {
            trie.put(word);
        }
        int[] ans = new int[words.length];
        int i=0;
        for (String word : words) {
            ans[i++]=trie.get(word);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PrefixScores().sumPrefixScores(new String[]{"abc","ab","bc","b"})));
    }

}

/*

Time complexity: O(total characters in all words)



for array : ["abc","ab","bc","b"]
add nodes to trie with frequency

       root
     a(2) b(2)
   b(2)    c(1)
  c(1)


 */