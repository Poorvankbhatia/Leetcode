/*

In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].

Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.



Example 1:

Input: [1,1,1,2,2,2]
Output: [2,1,2,1,2,1]
Example 2:

Input: [1,1,1,1,2,2,3,3]
Output: [1,3,1,3,2,1,2,1]


Note:

1 <= barcodes.length <= 10000
1 <= barcodes[i] <= 10000

 */
package heap.medium;

import java.util.*;

public class RearrangeBarCodes {

    Map<Integer,Integer> map;
    public int[] rearrangeBarcodes(int[] barcodes) {
        map = new HashMap<>();
        for(int barcode : barcodes) {
            map.put(barcode,map.getOrDefault(barcode,0)+1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        pq.addAll(map.entrySet());
        int[] result = new int[barcodes.length];
        int i=0;
        Queue<Map.Entry<Integer,Integer>> queue = new LinkedList<>();
        while(pq.size()>0) {
            Map.Entry<Integer,Integer> entry = pq.poll();
            result[i++]=entry.getKey();
            entry.setValue(entry.getValue()-1);
            queue.add(entry);
            if(queue.size()<2) {
                continue;
            }
            Map.Entry<Integer,Integer> front = queue.poll();
            if(front!=null && front.getValue()>0) {
                pq.add(front);
            }
        }
        return result;
    }

}

/*

O(n) Sol:
//10011
public int[] rearrangeBarcodes(int[] barcodes) {
        int maxFreqCode = 0, i = 0, n = barcodes.length;
        int[] freq = new int[10001];
        int[] ans = new int[n];
        for (int c : barcodes) { // count the frequency of each code.
            if (++freq[c] > freq[maxFreqCode]) { // update the code of max frequency.
                maxFreqCode = c;
            }
        }
        for (int j = 0; j < 10001; j++) {
            int code = (j == 0)?maxFreqCode : j; // fill in most frequent code first.
            while (freq[code]-- > 0) { // fill codes of positive frequencies.
                ans[i] = code;
                i = i + 2 < n ? i + 2 : 1; // fill even indices first, if depleted, use odd ones.
            }
        }
        return ans;
    }

 */