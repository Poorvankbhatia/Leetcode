/*
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.



Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination:
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"



Constraints:

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
 */
package bfsdfs.medium;

import bfsdfs.UnionFind;

import java.util.*;

public class SmallestStringWithSwaps {

    Map<Integer,Integer> parentMap;
    Map<Integer,Integer> sizeMap;
    Map<Integer, PriorityQueue<Character>> map;
    int count;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if(pairs==null || pairs.size()==0) {
            return s;
        }
        parentMap = new HashMap<>();
        sizeMap = new HashMap<>();
        map = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            sizeMap.put(i,1);
            parentMap.put(i,i);
        }
        count = s.length();
        for(List<Integer> list : pairs) {
            if(parent(list.get(0))!=parent(list.get(1))) {
                union(list.get(0),list.get(1));
            }
        }
        for (Map.Entry<Integer,Integer> entry : parentMap.entrySet()) {
            if(!map.containsKey(parent(entry.getValue()))) {
                map.put(parent(entry.getValue()),new PriorityQueue<>());
            }
            map.get(parent(entry.getValue())).add(s.charAt(entry.getKey()));
        }

        StringBuilder result = new StringBuilder();
        for (int i=0;i<s.length();i++) {
            result.append(map.get(parent(i)).poll());
        }
        return result.toString();
    }

    private int parent(int i) {
        while(i!=parentMap.get(i)) {
            parentMap.put(parentMap.get(i),parentMap.get(parentMap.get(i)));
            i=parentMap.get(i);
        }
        return i;
    }

    private void union(int i,int j) {
        int parentI = parent(i);
        int parentJ = parent(j);
        if(parentI==parentJ) {
            return;
        }
        if(sizeMap.get(parentI)>sizeMap.get(parentJ)) {
            parentMap.put(parentJ,parentI);
            sizeMap.put(parentI,sizeMap.get(parentI)+sizeMap.get(parentJ));
        } else {
            parentMap.put(parentI,parentJ);
            sizeMap.put(parentJ,sizeMap.get(parentJ)+sizeMap.get(parentI));
        }
        count--;
    }

    public static void main(String[] args) {
        String s = "udyyek";
        List<List<Integer>> l = new ArrayList<>();
        l.add(Arrays.asList(3,3));
        l.add(Arrays.asList(3,0));
        l.add(Arrays.asList(5,1));
        l.add(Arrays.asList(3,1));
        l.add(Arrays.asList(3,4));
        l.add(Arrays.asList(3,5));
        System.out.println(new SmallestStringWithSwaps().smallestStringWithSwaps(s,l)); // deykuy
    }

}

/*

If we union the swap pairs, we end up with a set of disjoint indexes representing separate graphs.
For every character index, find the graph the character belongs to & find the smallest permutation for every graph.

-> Extract separate graphs and map the string’s characters to the appropriate vertices.
-> Sort the characters in descending order within each graph.
-> Populate the output by taking the characters from the graph.
 */
