/*

In English, we have a concept called root, which can be followed by some other words to form another longer word -
let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root
forming it. If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

 */
package trie.medium;

import trie.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 26/07/17.
 */
public class ReplaceWords {

    public String replaceWords(List<String> dict, String sentence) {

        Trie<String> trie = new Trie<>();
        for (String root : dict) {
            trie.put(root,root);
        }

        StringBuilder result = new StringBuilder();

        for (String successor : sentence.split(" ")) {
            List<String> list = trie.allPrefixKeys(successor);
            if(list!=null && list.size()!=0) {
                // Implementation of allPrefixKeys always gives prefix keys ordered by length starting from smallest.
                result.append(list.get(0)).append(" ");
            } else {
                result.append(successor).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        dict.add("a");
        dict.add("aa");
        dict.add("aaa");
        dict.add("aaaa");
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa";
        System.out.print(new ReplaceWords().replaceWords(dict,sentence));
    }

}

/*

Add all dictionary words to Trie. Once done loop over all the sentence words and for every word get all the keys which are prefixes of this word.


 */