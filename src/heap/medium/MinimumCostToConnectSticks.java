/*

You have some sticks with positive integer lengths.

You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.



Example 1:

Input: sticks = [2,4,3]
Output: 14
Example 2:

Input: sticks = [1,8,3,5]
Output: 30


Constraints:

1 <= sticks.length <= 10^4
1 <= sticks[i] <= 10^4


 */
package heap.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {

    public int connectSticks(int[] sticks) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int stick : sticks) {
            priorityQueue.add(stick);
        }

        int val = 0;
        while (priorityQueue.size()>1) {
            Integer x = priorityQueue.poll();
            Integer y = priorityQueue.poll();
            int sum = (x+y);
            val += sum;
            priorityQueue.add(sum);
        }

        return val;

    }

}
