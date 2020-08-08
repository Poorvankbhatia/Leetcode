/*

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

 */
package string.medium;

import java.util.*;

/**
 * Created by poorvank on 02/11/16.
 */


public class SortByFrequency {

    public String frequencySort(String s) {
        int[] count = new int[256];
        int max = 0;
        for(char c: s.toCharArray()) {
            count[c]++;
            max = Math.max(count[c],max);
        }
        List<Character>[] arr = new List[max+1]; // Bucket of occurrences.
        for(int i=0;i<256;i++) {
            if(count[i]!=0) {
                if(arr[count[i]]==null) {
                    arr[count[i]]=new ArrayList<>();
                }
                arr[count[i]].add((char)i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=max;i>=1;i--) {
            List<Character> list = arr[i];
            if(list!=null) {
                for(char c :list) {
                    sb.append(String.valueOf(c).repeat(i));
                }
            }
        }
        return sb.toString();
    }

}

/*

TreeMap Sol:

public String frequencySort(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        TreeMap<Integer,List<Character>> treeMap = new TreeMap<>((a, b)->(b-a));
        for(Map.Entry<Character,Integer> entry : map.entrySet()) {
            if(!treeMap.containsKey(entry.getValue())) {
                treeMap.put(entry.getValue(),new ArrayList<>());
            }
            treeMap.get(entry.getValue()).add(entry.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer,List<Character>> entry : treeMap.entrySet()) {
            for (char c : entry.getValue()) {
                for (int k =0;k<entry.getKey();k++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

 */