package heap.medium;

import java.util.PriorityQueue;

public class SuperUglyNo {

    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<int[]> queue=new PriorityQueue<>((a, b)->(a[0]-b[0]));
        for (int prime : primes) {
            queue.offer(new int[]{prime, prime, 0});
        }
        int[] result=new int[n+1];
        result[0]=1;
        int i=1;
        while (i<n){
            int[] entry=queue.poll();
            int num=entry[0], prime=entry[1], index=entry[2];
            // remove duplicate
            if (num!=result[i-1]){
                result[i]=num;
                i++;
            }
            queue.offer(new int[]{prime*result[index+1], prime, index+1});
        }
        return result[n-1];
    }

}

/*

It is actually like how we merge k sorted list:

ugly number                       k sorted list
    1                            2     7    13   19     1 * [2,7,13,19]
    |                            |     |    |    |
    2                            4     14   26   38     2 * [2,7,13,19]
    |                            |     |    |    |
    4                            8     28   52   76     4 * [2,7,13,19]
    |                            |     |    |    |
    7                            14    49   91   133    7 * [2,7,13,19]
    |                            |     |    |    |
    8                            16    56   ...   ...   8 * [2,7,13,19]
    |                            |     |    |     |
    .                            .     .     .    .
    .                            .     .     .    .
    .                            .     .     .    .
We can see that each prime number in primes[] form a sorted list, and now our job is to merge them and find the nth minimum.

Here we don't have the next pointer for each node to trace the next potential candidate. But as we can see in the graph,
we can make use of the ugly number we have produced so far!

Here, each entry has three parts: {num, prime, index}, num represents the value of the node, prime means which sorted list this node is in,
and index tells us how far we have gone in that list, it works like the next pointer in linkedlist, help us find the next node in that sorted list.

Time: O(nlogk)
Space: O(n+k)

 */