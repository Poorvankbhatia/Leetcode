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

    private class TrieNode {
        private int val;
        private TrieNode[] children = new TrieNode[26];
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        insert(key,0,root,val);
    }

    private TrieNode insert(String key,int d,TrieNode root,int sum) {
        if(d==key.length()) {
            root.val = sum;
            return root;
        }
        char c = key.charAt(d);
        if(root.children[c-'a']==null) {
            root.children[c-'a'] = new TrieNode();
        }
        insert(key,d+1,root.children[c-'a'],sum);
        return root;
    }

    public int sum(String prefix) {
        TrieNode node = get(prefix,prefix.length(),0,root);
        if(node==null) {
            return 0;
        } else {
            return collect(node);
        }
    }

    private int collect(TrieNode root) {
        int val=root.val;
        for(char c='a';c<='z';c++) {
            if(root.children[c-'a']!=null) {
                val+=collect(root.children[c-'a']);
            }
        }
        return val;
    }

    private TrieNode get(String key,int length,int d,TrieNode root) {
        if(d==length) {
            return root;
        }
        char c = key.charAt(d);
        if(root.children[c-'a']!=null) {
            return get(key,length,d+1,root.children[c-'a']);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("aa",2);
        mapSum.insert("aaa",3);
        System.out.println(mapSum.sum("aaa"));
    }

}
