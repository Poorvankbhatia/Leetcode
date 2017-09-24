/*
Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer).
The string represents the key and the integer represents the value. If the key already existed,
then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the
sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5
 */
package trie.medium;

import trie.Trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 17/09/17.
 */
public class MapSum {

    Trie<Integer> trie;
    Map<String,Integer> map;
    public MapSum() {
        trie = new Trie<>();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        if(!map.containsKey(key)) {
            trie.put(key,val);
        }
        map.put(key,val);
    }

    public int sum(String prefix) {
        int sum =0;

        List<String> keys = trie.keysWithPrefix(prefix);
        for (String key : keys) {
            sum += map.get(key);
        }

        return sum;
    }

}
