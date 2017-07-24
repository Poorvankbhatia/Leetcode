
package trie.medium;

/**
 * Created by poorvank.b on 15/10/16.
 */

class TrieNode {
    public boolean value;
    public TrieNode[] childArray = new TrieNode[26];
    public TrieNode() {
        this.value = false;
    }
}

public class Trie {

    private TrieNode root;
    private static final int R=26;

    public Trie() {
        root = new TrieNode();
    }

    public Boolean search(String word) {
        TrieNode x = get(word, root, 0);
        return x != null && x.value;
    }

    private TrieNode get(String key,TrieNode x,int d) {
        if(x==null) {
            return null;
        }
        if(d==key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(key,x.childArray[c-'a'],d+1);
    }

    public void insert(String word) {
        root = put(word,root,0);
    }


    private TrieNode put(String key,TrieNode x,int d) {
        if(x==null) {
            x = new TrieNode();
        }
        if(d==key.length()) {
            x.value=true;
            return x;
        }

        char c = key.charAt(d);
        x.childArray[c-'a']= put(key,x.childArray[c-'a'],d+1);
        return x;

    }

    public boolean startsWith(String prefix) {

        TrieNode x = get(prefix,root,0);
        return startsWithUtil(x);

    }


    public void delete(String key) {
        root = delete(key,0,root);
    }

    private TrieNode delete(String key,int d,TrieNode x) {
        if(x==null) {
            return null;
        }
        if(x.value) {
            x.value = false;
        } else {
            char c = key.charAt(d);
            x.childArray[c-'a'] = delete(key,d+1,x.childArray[c-'a']);
        }

        if(x.value) {
            return x;
        }

        for (char c='a';c<='z';c++) {
            if(x.childArray[c-'a']!=null) {
                return x;
            }
        }

        return null;
    }


    private boolean startsWithUtil(TrieNode x) {
        if(x==null) {
            return false;
        }
        if(x.value) {
            return true;
        }

        for (char c='a';c<='z';c++) {
            if(startsWithUtil(x.childArray[c-'a'])) {
                return true;
            }
        }

        return false;
    }

}
