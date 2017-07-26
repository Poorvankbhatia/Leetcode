package trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 25/06/16.
 */


public class Trie<Item> {

    private static final int R = 26;
    private Node root;
    private int size;

    private static class Node {
        private Object value;
        private Node[] childArray = new Node[R];
    }

    public Trie() {
        root = new Node();
        size = 0;
    }

    public Item get(String key) {
        Node x = get(root,key,0);
        if(x!=null) {
            if(x.value!=null) {
                return (Item)x.value;
            }
        }
        return null;
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

    public boolean contains(String key) {
        return get(key)!=null;
    }


    /*

     Method used for Word Abbreviation problem

     */

    public String getAbbreviatedString(String key,Item item) {
        StringBuilder sb = new StringBuilder();
        getEntirePrefixWithValue(root,key,item,sb,0);
        int length = sb.length();
        if(key.length()-length>2) {
            sb.append(key.length()-length-1).append(key.charAt(key.length()-1));
            return sb.toString();
        } else {
            return key;
        }
    }

    private void getEntirePrefixWithValue(Node x,String key,Item item,StringBuilder sb,int d) {

        if(x==null) {
            return;
        } else {
            if(x.value==item) {
                return;
            }
        }
        char c = key.charAt(d);
        sb.append(c);
        getEntirePrefixWithValue(x.childArray[c-'a'],key,item,sb,d+1);

    }

     /*

     Method used for Word Abbreviation problem

     */

    public void putWithFrequency(String key) {
        root = putWithFrequency(root, key, 0);
    }

    private Node putWithFrequency(Node x,String key,int d) {
        boolean wasPresent = true;
        if(x==null) {
            wasPresent = false;
            x=new Node();
            x.value = 1;
        } else if(x!=root) {
            Integer value = (Integer) x.value;
            value++;
            x.value = value;
        }
        if(d==key.length()) {
            if(!wasPresent) {
                size++;
            }
            return x;
        }
        char c = key.charAt(d);
        x.childArray[c-'a'] = putWithFrequency(x.childArray[c-'a'],key,d+1);
        return x;

    }

    public void put(String key,Item item) {
        if (item==null) {
            delete(key);
        }
        else {
            root = put(root,key,0,item);
        }
    }

    private Node put(Node x,String key,int d,Item item) {
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

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize()==0;
    }

    public List<String> keys() {
        return keysWithPrefix("");
    }

    public List<String> keysWithPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        Node x = get(root,prefix,0);
        collect(x,new StringBuilder(prefix),results);
        return results;
    }

    private void collect(Node x,StringBuilder prefix,List<String> results) {
        if(x==null) {
            return;
        }
        if(x.value!=null) {
            results.add(prefix.toString());
        }
        for (char c='a';c<='z';c++) {
            prefix.append(c);
            collect(x.childArray[c-'a'],prefix,results);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    /*

        Method used for Word Search 2 problem.

     */

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

    /*

        List Keys which are a prefix of this query/word/key.

     */

    public List<String> allPrefixKeys(String query) {
        List<String> list = new ArrayList<>();
        collectAllPrefixKeys(root,query,new StringBuilder(),list,0);
        return list;
    }

    private void collectAllPrefixKeys(Node x,String query,StringBuilder sb,List<String> list,int d) {

        if(x==null) {
            return;
        }
        if(x.value!=null)  {
            list.add(sb.toString());
        }

        if(d == query.length()) {
            return;
        }

        char c = query.charAt(d);
        sb.append(c);
        collectAllPrefixKeys(x.childArray[c-'a'],query,sb,list,d+1);

    }

    public String longestPrefixOf(String key) {
        int length = longestPrefixOf(root,key,0,-1);
        if(length==-1) {
            return null;
        }
        return key.substring(0,length);
    }

    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    private int longestPrefixOf(Node x,String query,int d,int length) {
        if(x==null) {
            return length;
        }
        if(x.value!=null) {
            length = d;
        }
        if(d==query.length()){
            return length;
        }
        char c = query.charAt(d);
        return longestPrefixOf(x.childArray[c-'a'],query,d+1,length);
    }


    public void delete(String key) {
        root = delete(root,key,0);
    }

    private Node delete(Node x,String key,int d) {
        if(x==null) {
            return null;
        }
        if(d==key.length()) {
            if(x.value!=null) {
                size--;
            }
            x.value = null;
        } else {
            char c = key.charAt(d);
            x.childArray[c-'a'] = delete(x.childArray[c-'a'],key,d+1);
        }

        //Remove subtree rooted at x , iff entire subtree is empty
        if(x.value!=null) {
            return x;
        }
        for (int c='a';c<='z';c++) {
            if(x.childArray[c-'a']!=null) {
                return x;
            }
        }

        return null;

    }


}

