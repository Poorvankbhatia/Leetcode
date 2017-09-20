/*
Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character
 into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across
 multiple test cases. Please see here for more details.
 */
package design.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by poorvank on 10/09/17.
 */
public class MagicDictionary {


    Map<String,Set<String>> map;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        map = new HashMap<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            Set<String> set = new HashSet<>();
            char[] arr = word.toCharArray();
            for (int i=0;i<arr.length;i++) {
                for (char c='a';c<'z';c++) {
                    char temp = arr[i];
                    if(arr[i]!=c) {
                        arr[i]=c;
                    } else {
                        continue;
                    }
                    String newWord = new String(arr);
                    set.add(newWord);
                    arr[i]=temp;
                }
            }
            map.put(word,set);
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for(String key : map.keySet()) {
            if(map.get(key).contains(word)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        MagicDictionary dict = new MagicDictionary();
        dict.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(dict.search("leetcoded"));
    }

}
