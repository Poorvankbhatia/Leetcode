/*

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].

 */
package heap.medium;

import java.util.*;

/**
 * Created by poorvank.b on 21/01/18.
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        HashMap<Character,Integer> characterCount = new HashMap<>();
        for (char c : S.toCharArray()) {
            if(!characterCount.containsKey(c)) {
                characterCount.put(c,0);
            }
            characterCount.put(c,characterCount.get(c)+1);
        }

        PriorityQueue<Map.Entry<Character,Integer>> priorityQueue = new PriorityQueue<>(
                (a,b) -> !Objects.equals(a.getValue(), b.getValue()) ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

        priorityQueue.addAll(characterCount.entrySet());

        StringBuilder result = new StringBuilder();

        while (!priorityQueue.isEmpty()) {

            int step=2;
            List<Map.Entry<Character,Integer>> list = new ArrayList<>();
            while (step>0 && !priorityQueue.isEmpty()) {
                Map.Entry<Character,Integer> entry = priorityQueue.poll();
                entry.setValue(entry.getValue()-1);
                list.add(entry);
                result.append(entry.getKey());
                step--;
            }

            for (Map.Entry<Character,Integer> entry : list) {
                if(entry.getValue()!=0) {
                    priorityQueue.add(entry);
                }
            }

            if(result.length()>1 && result.charAt(result.length()-1)==result.charAt(result.length()-2)) {
                return "";
            }

        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReorganizeString().reorganizeString("aablbn"));
    }

}
