/*

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.
For example, Given the following words in dictionary,
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".
There are few points not clear :

f-->t

each word itself has no order, ie abc can not deduce the order of a, b ,c
if any chars has not found and there is no dependence what then ?

 */
package graph.hard;

import java.util.*;

/**
 * Created by poorvank.b on 17/07/17.
 */
public class AlienDictionary {

    public String alienOrder(String[] words) {
        if(words==null || words.length==0) {
            return "";
        }
        Map<Character,List<Character>> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                set.add(c);
            }
        }
        int charCount = set.size();
        int[] inDegree = new int[26];
        for (int i=0;i<words.length-1;i++) {
            String first = words[i];
            String second = words[i+1];
            for (int j=0;j<Math.min(first.length(),second.length());j++) {
                if(first.charAt(j)!=second.charAt(j)) {
                    if(!map.containsKey(first.charAt(j))) {
                        map.put(first.charAt(j),new ArrayList<>());
                    }
                    map.get(first.charAt(j)).add(second.charAt(j));
                    inDegree[second.charAt(j)-'a']++;
                    break;
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (Character c : set) {
            if(inDegree[c-'a']==0) {
                queue.offer(c);
            }
            if(!map.containsKey(c)) {
                map.put((c),new ArrayList<>());
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character current = queue.poll();
            sb.append(current);
            if(map.containsKey(current)) {
                for (char next : map.get(current)) {
                    if(--inDegree[next-'a']==0) {
                        queue.offer(next);
                    }
                }
                charCount--;
            }
        }
        if(charCount==0) {
            return sb.toString();
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        String[] a = {"wrt",
                "wrf",
                "er",
                "ett",
                "rftt"};
        System.out.print(new AlienDictionary().alienOrder(a));
    }

}
/*

The idea is to create a graph of characters and then find topological sorting of the created graph. Following are the detailed steps.

1) Create a graph g with number of vertices equal to the size of alphabet in the given alien language. For example,
if the alphabet size is 5, then there can be 5 characters in words. Initially there are no edges in graph.

2) Do following for every pair of adjacent words in given sorted array.
…..a) Let the current pair of words be word1 and word2. One by one compare characters of both words and find the first mismatching characters.
…..b) Create an edge in g from mismatching character of word1 to that of word2.

3) Print topological sorting of the above created graph.


The first step to create a graph takes O(n + alhpa) time where n is number of given words and alpha is number of characters in given alphabet.
The second step is also topological sorting. Note that there would be alpha vertices and at-most (n-1) edges in the graph.
The time complexity of topological sorting is O(V+E) which is O(n + aplha) here. So overall time complexity is O(n + aplha) + O(n + aplha) which is O(n + aplha).


 */