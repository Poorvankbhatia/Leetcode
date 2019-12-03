/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.


 */
package heap.medium;

import heap.MinPriorityQueue;

/**
 * Created by poorvank on 08/09/16.
 */
public class KthSmallestElementSortedMat {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[n-1][n-1];
        while(lo<hi) {
            int mid = lo+(hi-lo)/2;
            if(count(mid,matrix)>=k) {
                hi=mid;
            } else {
                lo=mid+1;
            }
        }
        return lo;
    }

    private int count(int val,int[][] matrix) {
        int c=0;
        int n = matrix.length;
        int j=n-1;
        int i=0;
        while(j>=0 && i<n) {
            if(matrix[i][j]<=val) {
                c+=j+1;
                i++;
            } else {
                j--;
            }
        }
        return c;
    }

    public static void main(String[] args) {

        int[][] matrix =  new int[][]{{1,  5,  9},
                                      {10, 11, 13},
                                      {12, 13, 15}};

        int k =8;

        KthSmallestElementSortedMat ks = new KthSmallestElementSortedMat();
        System.out.println(ks.kthSmallest(matrix,k));
    }
}


/*

Complexity:  O(N∗log(max−min))

Basically, the method in the solution is a variation of binary search to "find the first occurrence of an element".

The count is the number of elements less or equal to mid, given the "matrix[i][j] > mid "in the while condition.
There are two scenarios:
Single Kth smallest element in matrix.
[1,2,3,5]
[2,3,5,7]
[3,5,8,9]
[5,8,9,13]
k = 11
Result = 7

This ensures count equals to k at a point, in which it includes the kth smallest element in the matrix. At the moment, binary search shrinks high boundary to mid,
instead of returning mid in the regular binary search. You can imagine it as "hi" is waiting at the right spot for "lo" to meet him. (Like dating!)

Multiple Kth smallest element in matrix.
[1,2,3,5]
[2,3,5,7]
[3,7,8,9]
[7,8,9,13]
k = 9
Result = 7

Count won't be equal to k, but "hi" is still guaranteed to stop at right spot. In the above example, the count is 11 when "mid" is 7. After "hi" shrinks to mid, it will not move until "lo" comes to him.

To sum up, "lo" is ensured to reach an authentic element in the matrix, because "hi" will approach and sit at the right spot anyway.



Heap Solution: O(min(K,N)+K∗logN)
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}

 */