/*
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */
package trie.medium;

public class PrefixTree {

    private static final int R = 26;
    private Node root;
    private int size;

    private static class Node {
        private Object value;
        private Node[] childArray = new Node[R];
    }

    /** Initialize your data structure here. */
    public PrefixTree() {
        root = new Node();
        size = 0;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root = put(root,word,0,0);
    }

    private Node put(Node x,String key,int d,Integer item) {
        if(x==null) {
            x = new Node();
        }
        if(d==key.length()) {
            if(x.value==null) {
                size++;
            }
            x.value = (item);
            return x;
        }
        char c = key.charAt(d);
        x.childArray[c-'a'] = put(x.childArray[c-'a'],key,d+1,item);
        return x;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String key) {
        Node x = get(root,key,0);
        if(x!=null) {
            return x.value != null;
        }
        return false;
    }


    private Node get(Node x,String key,int d) {
        if(x==null) {
            return null;
        }
        if(d==key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.childArray[c-'a'],key,d+1);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String query) {
        Node x = get(root,query,0);
        return startsWithUtil(x);
    }

    private boolean startsWithUtil(Node x) {
        if(x==null) {
            return false;
        }
        if(x.value!=null) {
            return true;
        }
        for (int c='a';c<='z';c++) {
            if(startsWithUtil(x.childArray[c-'a'])) {
                return true;
            }
        }

        return false;
    }

}
