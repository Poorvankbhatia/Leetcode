/*

Given an array of strings words, find the longest string in words such that every prefix of it is also in words.

For example, let words = ["a", "app", "ap"]. The string "app" has prefixes "ap" and "a", all of which are in words.
Return the string described above. If there is more than one string with the same length, return the lexicographically
smallest one, and if no string exists, return "".



Example 1:

Input: words = ["k","ki","kir","kira", "kiran"]
Output: "kiran"
Explanation: "kiran" has prefixes "kira", "kir", "ki", and "k", and all of them appear in words.
Example 2:

Input: words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation: Both "apple" and "apply" have all their prefixes in words.
However, "apple" is lexicographically smaller, so we return that.
Example 3:

Input: words = ["abc", "bc", "ab", "qwe"]
Output: ""


Constraints:

1 <= words.length <= 105
1 <= words[i].length <= 105
1 <= sum(words[i].length) <= 105

 */
package trie.medium;

import trie.Trie;

import java.util.Arrays;

public class LongestWordWithAllPrefix {

    public String longestWord(String[] words) {
        Arrays.sort(words,(a,b)->(b.length()!=a.length())?b.length()-a.length():a.compareTo(b));
        Trie<Boolean> trie = new Trie<>();
        for (String word : words) {
            trie.put(word,true);
        }
        for (String word : words) {
            if(trie.allPrefixKeys(word).size()==word.length()) {
                return word;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new LongestWordWithAllPrefix().longestWord(new String[]{"abc", "bc", "ab", "qwe"}));
    }

}
