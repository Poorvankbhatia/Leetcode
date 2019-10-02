/*

A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible
length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").

 */
package trie.hard;

import java.util.ArrayList;
import java.util.List;

public class MinimumUniqueWordAbbreviation {

    class Node{ // Trie Node
        Node[] nodes;
        boolean isWord;
        Node(){
            nodes = new Node[26];
            isWord = false;
        }
        void add(String str){ // add a word to Trie
            if (str.length() == 0) isWord=true; // end of a word
            else {
                int idx = str.charAt(0)-'a'; // insert a new node
                if (nodes[idx] == null) nodes[idx] = new Node();
                nodes[idx].add(str.substring(1));
            }
        }
        boolean isAbbr(String abbr, int num){
            if ( num > 0){ // number of '*'
                for (Node node : nodes){
                    if (node != null && node.isAbbr(abbr, num-1)) return true;
                }
                return false; // not exist in the dictionary
            } else {
                if (abbr.length()==0) return isWord; // at the end of the addr
                int idx=0; // get the number of '*' at the start of the abbr
                while (idx < abbr.length() && abbr.charAt(idx) >='0' && abbr.charAt(idx) <='9' ) {
                    num = (num*10) + (abbr.charAt(idx++)-'0');
                }
                if (num>0) return isAbbr(abbr.substring(idx),num); // start with number
                else { // start with non-number
                    if (nodes[abbr.charAt(0)-'a'] != null )
                        return nodes[abbr.charAt(0)-'a'].isAbbr(abbr.substring(1), 0);
                    else return false; // not exist in the dictionary
                }
            }
        }
    }

    private void getAbbs(char[] cc, int s, int len, StringBuilder sb, List<String> abbs){ //DFS with backtracking
        boolean preNum = (sb.length() > 0 ) && (sb.charAt(sb.length()-1) >= '0') && (sb.charAt(sb.length()-1) <= '9');
        if (len == 1)  {
            if ( s  < cc.length) {
                if (s==cc.length-1) abbs.add(sb.toString() + cc[s]); // add one char
                if (! preNum ) abbs.add(sb.toString() + (cc.length-s) ); // add a number
            }
        } else if (len > 1 ) {
            int last = sb.length();
            for (int i=s+1; i < cc.length; i++ ){
                if (! preNum) { // add a number
                    sb.append(i-s);
                    getAbbs(cc, i, len-1, sb, abbs);
                    sb.delete(last, sb.length());
                }
                if (i==s+1) { // add one char
                    sb.append(cc[s]);
                    getAbbs(cc, i, len-1, sb, abbs);
                    sb.delete(last, sb.length());
                }
            }
        }
    }

    public String minAbbreviation(String target, String[] dictionary) {
        List<String> dict = new ArrayList<>();
        int len = target.length();
        for (String str : dictionary) if (str.length() == len ) dict.add(str);
        if (dict.isEmpty()) return ""+len;
        Node root = new Node();
        for (String str : dict) root.add(str);
        char[] cc = target.toCharArray();
        String ret = null;

        int min = 1, max = len;
        while (max >= min) {
            int mid = min+( (max-min)/2 );
            List<String> abbs = new ArrayList<>();
            getAbbs(cc, 0, mid, new StringBuilder(), abbs);
            boolean conflict = true;
            for (String abbr: abbs){
                if ( ! root.isAbbr(abbr,0) ) {
                    conflict = false;
                    ret = abbr;
                    break;
                }
            }
            if (conflict) {
                min = mid+1;
            } else {
                max = mid-1;
            }
        }
        return ret;
    }

}

/*


Use Trie to build a dictionary with a function to check abbreviation.
Use DFS with backtracking to generate the abbreviations of a given length.
Use binary search to find the smallest possible length.

Depth-first search (DFS) + pruning

Reuse topic Valid Word Abbreviation word abbreviation detection methodvalidWordAbbreviation

First remove the words in the dictionary that are different in length from the target.

DFS starts with the empty string '', increasing the letters in the target one by one; and trying to cross some letters and insert numbers.

Traversing the words in the dictionary, detecting conflicts, recursive if there are no conflicts, and updating the optimal solution.

Pruning strategy:

The variable length is used to record the optimal word abbreviation length at the current time. If the length of the DFS branch is greater than length, it can be pruned.


The same idea as the above solution can be used to implement the conflict detection of word abbreviations.

Convert the word word in the dictionary dictionary to a number by the following function:

Def toNumber(self, target, word):
    Ans = 0
    For x in range(self.size):
        Ans <<= 1
        Ans += target[x] == word[x]
    Return ans
For example, if target='apple', word='amble', then the binary number of word is 10011.

Apple
Amble
1 0 0 1 1
The abbreviation abbr is converted to a binary number by the following principle:

The letter in the abbreviation is replaced by binary 1, and the number of digits covered by the number is replaced by binary 0.

For example, the abbreviation m2ip6n of the word manipulation can be converted to 100110000001

Transaction
m 2 ip 6 n
1 0 0 1 1 0 0 0 0 0 0 1
Check whether the word abbreviated abbr and the dictionary dicitonary conflict with each other through the following functions:

Def checkNumber(self, number):
    For w in self.wlist:
        If number & w == number:
            Return False
    Return True
 */
