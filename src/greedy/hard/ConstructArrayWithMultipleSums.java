/*
Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :

let x be the sum of all elements currently in your array.
choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
You may repeat this procedure as many times as needed.
Return True if it is possible to construct the target array from A otherwise return False.



Example 1:

Input: target = [9,3,5]
Output: true
Explanation: Start with [1, 1, 1]
[1, 1, 1], sum = 3 choose index 1
[1, 3, 1], sum = 5 choose index 2
[1, 3, 5], sum = 9 choose index 0
[9, 3, 5] Done
Example 2:

Input: target = [1,1,1,2]
Output: false
Explanation: Impossible to create target array from [1,1,1,1].
Example 3:

Input: target = [8,5]
Output: true


Constraints:

N == target.length
1 <= target.length <= 5 * 10^4
1 <= target[i] <= 10^9
 */
package greedy.hard;

import java.util.PriorityQueue;
import java.util.Queue;

public class ConstructArrayWithMultipleSums {

    public boolean isPossible(int[] A) {
        if(A.length==1 && A[0]!=1) {
            return false;
        }
        long sum = 0;
        Queue<Long> pq = new PriorityQueue<Long>((a, b) ->{return (int) (b - a);});
        for (int a : A) {
            pq.offer((long)a);
            sum += a;
        }
        while(!pq.isEmpty()) {
            long i = pq.poll();
            sum -= i;
            if(i == 1 // all 1
                    || sum == 1) { // [1, i], will always be true;
                return true;
            }

            if (i < sum) {
                return false;
            }

            i %= sum; // find the positive num.

            //i = a positive num + sum;
            if (i == 0) {
                return false;
            }

            sum += i;
            pq.offer(i);
        }

        return true;
    }

}

/*

Similar to reachingPoints.

1, all 1 and [1, i] will always be true;
2, next phase will any i in array switched to i + (sum - i) * n, n >= 1;

Let's say [mx, a1,a2,..,an]
other=a1+a2+...+an
sm=other+mx
if mx-other>other, there must be multiple(mx//other) times operations, and previous value of mx is mx%other
for instance, [10,3], mx=10, other=3, prev=1(10%3)
[1,3] => [4,3] => [7,3] => [10,3]
you keep adding other to the element whose index is mx's, 3(10//3) times, and then 1 becomes 10

 */
