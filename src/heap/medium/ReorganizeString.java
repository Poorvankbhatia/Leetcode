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

    Map<Character,Integer> map;
    public String reorganizeString(String S) {
        map = new HashMap<>();
        for(char c : S.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        pq.addAll(map.entrySet());
        Queue<Map.Entry<Character,Integer>> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        while(!pq.isEmpty()) {
            Map.Entry<Character,Integer> entry = pq.poll();
            entry.setValue(entry.getValue()-1);
            result.append(entry.getKey());
            queue.offer(entry);
            if(queue.size()<2) {
                continue;
            }
            Map.Entry<Character,Integer> front = queue.poll();
            if(front!=null && front.getValue()>0) {
                pq.add(front);
            }
        }
        return result.length()==S.length()?result.toString():"";
    }

    public static void main(String[] args) {
        System.out.println(new ReorganizeString().reorganizeString("aablbn"));
    }

}

/*

O(n) solution:

public String reorganizeString(String S) {
        int[] freq = new int[26];
        int n = S.length();
        char maxOccurring = 'a';
        for(char c : S.toCharArray()) {
            if(++freq[c-'a']>freq[maxOccurring-'a']) {
                maxOccurring = c;
            }
        }
        if((freq[maxOccurring-'a']>(n+1)/2)) {
            return "";
        }
        int k =0;
        char[] arr = new char[S.length()];
        while (freq[maxOccurring-'a']-->0) {
            arr[k] = maxOccurring;
            k=(k+2<S.length())?k+2:1;
        }
        for(int i=0;i<26;i++) {
            char start = (char) (i+'a');
            while(freq[start-'a']-->0) {
                arr[k] = start;
                k=(k+2<S.length())?k+2:1;
            }
        }
        return new String(arr);
    }

 */