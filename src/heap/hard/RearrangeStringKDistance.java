/*

Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example:

str = "aabbcc", k = 3

Result: "abcabc"

The same letters are at least distance 3 from each other.

 */
package heap.hard;

import java.util.*;

/**
 * Created by poorvank on 24/06/17.
 */
public class RearrangeStringKDistance {

    public String rearrangeString(String str, int k) {

        if(k==0) {
            return str;
        }

        HashMap<Character,Integer> characterCount = new HashMap<>();
        for (char c : str.toCharArray()) {
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

            int step = k;
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

            if(step>0) {
                return "";
            }

        }

        return result.toString();

    }

    public static void main(String[] args) {
        System.out.println(new RearrangeStringKDistance().rearrangeString("aabbcc",3));
    }

}
