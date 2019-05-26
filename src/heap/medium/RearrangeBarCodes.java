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

    public int[] rearrangeBarcodes(int[] barcodes) {

        HashMap<Integer,Integer> count = new HashMap<>();
        for (int c : barcodes) {
            if(!count.containsKey(c)) {
                count.put(c,0);
            }
            count.put(c,count.get(c)+1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> priorityQueue = new PriorityQueue<>(
                (a,b) -> !Objects.equals(a.getValue(), b.getValue()) ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

        priorityQueue.addAll(count.entrySet());

        int[] result = new int[barcodes.length];

        Queue<Map.Entry<Integer,Integer>> queue = new LinkedList<>();

        int i=0;
        while (!priorityQueue.isEmpty()) {

            Map.Entry<Integer, Integer> entry = priorityQueue.poll();
            entry.setValue(entry.getValue() - 1);
            queue.offer(entry);
            result[i]=entry.getKey();
            i++;

            if(queue.size()<2) {
                continue;
            }

            Map.Entry<Integer, Integer> front = queue.poll();

            if(front!=null && front.getValue()>0) {
                priorityQueue.offer(front);
            }

        }

        return result;


    }

}
