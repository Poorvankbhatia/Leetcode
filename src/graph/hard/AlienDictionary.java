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

                    if(!map.containsKey(second.charAt(j))) {
                        map.put(second.charAt(j),new ArrayList<>());
                    }
                    map.get(second.charAt(j)).add(first.charAt(j));
                    inDegree[first.charAt(j)-'a']++;
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

        Stack<Character> stack = new Stack<>();

        while (!queue.isEmpty()) {
            Character current = queue.poll();
            if(map.containsKey(current)) {
                for (char next : map.get(current)) {
                    if(--inDegree[next-'a']==0) {
                        queue.offer(next);
                    }
                }
                charCount--;
                stack.push(current);
            }
        }


        if(charCount==0) {
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
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
