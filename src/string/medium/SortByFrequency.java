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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by poorvank on 02/11/16.
 */


public class SortByFrequency {

    private class ValueComparator implements Comparator<Character> {

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        public ValueComparator(HashMap<Character, Integer> map){
            this.map.putAll(map);
        }

        public int compare(Character s1, Character s2) {
            if(map.get(s1) >= map.get(s2)){
                return -1;
            }else{
                return 1;
            }
        }

    }

    public String frequencySort(String s) {

        HashMap<Character,Integer> frequency = new HashMap<>();

        for (Character c : s.toCharArray()) {
            if(!frequency.containsKey(c)) {
                frequency.put(c,1);
            } else {
                int newFreq = frequency.get(c)+1;
                frequency.put(c,newFreq);
            }
        }

        TreeMap<Character,Integer> treeMap = new TreeMap<>(new ValueComparator(frequency));
        treeMap.putAll(frequency);

        StringBuilder sb = new StringBuilder();

        for (Map.Entry entry : treeMap.entrySet()) {

            Character key = (Character)entry.getKey();
            Integer value = (Integer) entry.getValue();

            for (int i=0;i<value;i++) {
                sb.append(key);
            }

        }

        return sb.toString();

    }

}
