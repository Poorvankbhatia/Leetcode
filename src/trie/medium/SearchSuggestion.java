/*

Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names
from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord.
If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed.



Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
Example 4:

Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]


Constraints:

1 <= products.length <= 1000
There are no repeated elements in products.
1 <= Σ products[i].length <= 2 * 10^4
All characters of products[i] are lower-case English letters.
1 <= searchWord.length <= 1000
All characters of searchWord are lower-case English letters.

 */
package trie.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SearchSuggestion {

    class Node {
        private Node[] children;
        private TreeSet<String> list = new TreeSet<>();

        Node(String s) {
            children = new Node[26];
            if(s!=null) {
                list.add(s);
            }
        }
    }

    Node root;
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new Node(null);
        for(String p : products) {
            addToTrie(p,0,root);
        }
        List<List<String>> results = new ArrayList<>();
        for(int i=1;i<=searchWord.length();i++) {
            List<String> list = new ArrayList<>();
            collect(searchWord.substring(0,i),root,0,list);
            results.add(list.size()>3?list.subList(0,3):list);
        }
        return results;
    }

    private void collect(String word,Node root,int d,List<String> list) {
        char c = word.charAt(d);
        if(d==word.length()-1) {
            if(root.children[c-'a']!=null) {
                list.addAll(root.children[c-'a'].list);
                return;
            }
        }
        if(root.children[c-'a']!=null) {
            collect(word,root.children[c-'a'],d+1,list);
        }
    }

    private void addToTrie(String word,int d,Node root) {
        if(d==word.length()) {
            return;
        }
        char c = word.charAt(d);
        if(root.children[c-'a']==null) {
            root.children[c-'a'] = new Node(word);
        } else {
            root.children[c-'a'].list.add(word);
        }
        addToTrie(word,d+1,root.children[c-'a']);
    }

    public static void main(String[] args) {
        System.out.println(new SearchSuggestion().suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"},"mouse"));
    }

}
