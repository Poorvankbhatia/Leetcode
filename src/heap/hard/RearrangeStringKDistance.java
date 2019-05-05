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

        if(k==0 || k>str.length() || k==1) {
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

        Queue<Map.Entry<Character,Integer>> queue = new LinkedList<>();

        while (!priorityQueue.isEmpty()) {

            Map.Entry<Character, Integer> entry = priorityQueue.poll();
            entry.setValue(entry.getValue() - 1);
            queue.offer(entry);
            result.append(entry.getKey());


            if(queue.size()<k) {
                continue;
            }

            Map.Entry<Character, Integer> front = queue.poll();

            if(front!=null && front.getValue()>0) {
                priorityQueue.offer(front);
            }

        }

        return result.length() == str.length() ? result.toString() : "";

    }

    public static void main(String[] args) {
        System.out.println(new RearrangeStringKDistance().rearrangeString("aabac",3));
    }

}

/*

O(nlogn)

The greedy algorithm is that in each step, select the char with highest remaining count if possible (if it is not in the waiting queue).
PQ is used to achieve the greedy. A regular queue waitQueue is used to "freeze" previous appeared char in the period of k.

In each iteration, we need to add current char to the waitQueue and also release the char at front of the queue, put back to maxHeap.
The "impossible" case happens when the maxHeap is empty but there is still some char in the waitQueue.


O(n)

This is a greedy problem.
Every time we want to find the best candidate: which is the character with the largest remaining count. Thus we will be having two arrays.
One count array to store the remaining count of every character. Another array to keep track of the most left position that one character
can appear.
We will iterated through these two array to find the best candidate for every position. Since the array is fixed size, it will take
constant time to do this.
After we find the candidate, we update two arrays.

public class Solution {
    public String rearrangeString(String str, int k) {
        int length = str.length();
        int[] count = new int[26];
        int[] valid = new int[26];
        for(int i=0;i<length;i++){
            count[str.charAt(i)-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int index = 0;index<length;index++){
            int candidatePos = findValidMax(count, valid, index);
            if( candidatePos == -1) return "";
            count[candidatePos]--;
            valid[candidatePos] = index+k;
            sb.append((char)('a'+candidatePos));
        }
        return sb.toString();
    }

   private int findValidMax(int[] count, int[] valid, int index){
       int max = Integer.MIN_VALUE;
       int candidatePos = -1;
       for(int i=0;i<count.length;i++){
           if(count[i]>0 && count[i]>max && index>=valid[i]){
               max = count[i];
               candidatePos = i;
           }
       }
       return candidatePos;
   }
}




 */